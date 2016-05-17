package br.com.thinkb.save;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class BluetoothConnectionService extends Service implements Constants{


    public static BluetoothAdapter mBlueAdapter = BluetoothAdapter.getDefaultAdapter();

    public static BluetoothDevice targetDevice = null;



    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Context ctx;
            //ctx = MainActivity.this;
            switch (msg.what) {

                case Constants.MESSAGE_RECEIVED:
                    byte[] temp = (byte[]) msg.obj;
                    String data = new String(temp, StandardCharsets.UTF_8);

                    if((data.charAt(0) == 'C')){

                        int notId = 404;

                        NotificationManager notify = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notify.notify(notId, new Notification.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.logo_save)
                                        //TODO
                                .setContentTitle("Queda Identificada")
                                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                                .setContentText("O dispositivo identificou uma queda!").build());

                    }
                    if((data.charAt(0) == 'M')){

                        int notId = 404;

                        NotificationManager notify = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notify.notify(notId, new Notification.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.logo_save)
                                        //TODO
                                .setContentTitle("Emergencia Reconhecida")
                                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                                .setContentText("O dispositivo identificou uma emergencia!").build());

                    }if((data.charAt(0)=='N')) {
                    Params change = Params.getInstance();
                    change.setLedState(true);
                }
                    if((data.charAt(0)=='F')) {
                        Params change = Params.getInstance();
                        change.setLedState(false);
                    }


                    //(data.charAt(0) == '')||
                    Log.d("HANDLER_HELPER", "msg received from bubbles = " + data);
                    break;
            }
        }
    };
    public BluetoothConnectionService() {

    }


    public static void sendToTarget(String msg){

        byte[] data = msg.getBytes();

        StreamThread.write(data);

    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("CONN_SERVICE", "Starting service");

        Bundle data;
        data = intent.getExtras();

        targetDevice = data.getParcelable("device");

        Thread clientThread = new Thread(new BluetoothClient(targetDevice, handler));
        clientThread.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }




    static class BluetoothClient extends Thread {

        private final UUID MY_UUID = UUID.fromString("d2f7929a-e951-4d14-ad02-1e0116bee5df");

        private BluetoothSocket socket;
        private final BluetoothDevice mmDevice;
        private final Handler handler;

        public BluetoothClient(BluetoothDevice device, Handler handler) {
            BluetoothSocket tmp = null;
            mmDevice = device;
            this.handler = handler;
            try {
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
            }
            socket = tmp;
        }

        public void run() {
            Log.d("SERVICE_CLIENT", "Stream thread has started with: " + socket.getRemoteDevice().getName());
            mBlueAdapter.cancelDiscovery();
            try {
                socket =(BluetoothSocket) mmDevice.getClass().getMethod("createRfcommSocket", new Class[]{int.class}).invoke(mmDevice, 1);
                socket.connect();

            } catch (IOException connectException) {
                Log.d("SERVICE_CLIENT", "Failed to connect to target device. Error Message: "+connectException.getMessage());
                try {
                    socket.close();
                } catch (IOException closeException) {
                    closeException.printStackTrace();
                }
                return;
            } catch (NoSuchMethodException e){
                Log.e("SERVICE_CLIENT", "Failed to find method");
            } catch (InvocationTargetException e){
                Log.e("SERVICE_CLIENT", "Invocation failed");
            } catch (IllegalAccessException e){
                Log.e("SERVICE_CLIENT", "Failed to access method or class");
            }
            Log.d("CONN_SERVICE", "Connected to: "+mmDevice.getName());
            Thread t = new Thread(new StreamThread(socket, handler));
            t.start();

            //TODO
        }

        public void cancel() {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    static class StreamThread extends Thread {

        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private static OutputStream mmOutStream = null;
        private final Handler handler;

        public StreamThread(BluetoothSocket socket, Handler handler) {

            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            this.handler = handler;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()

            Log.d("INSIDE COMM CHANNEL", "Starting comm link.");

            String temp = "R";
            byte[] data = temp.getBytes();

            //write(data);

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);
                    // Send the obtained bytes to the UI activity
                    //TODO
                    Message msg = handler.obtainMessage();
                    msg.obj = bytes;
                    handler.obtainMessage(Constants.MESSAGE_RECEIVED, bytes, -1, buffer)
                            .sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public static void write(byte[] bytes) {
            try {
                Log.v("WRITE_METHOD", "Has sending a signal to target.");
                if(!(mmOutStream == null))
                    mmOutStream.write(bytes);
            } catch (IOException e) {
                Log.e("WRITE_METHOD", "Failed to send bytes. Message"+e.getMessage());
            }
        }

        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
            }
        }
    }
}
