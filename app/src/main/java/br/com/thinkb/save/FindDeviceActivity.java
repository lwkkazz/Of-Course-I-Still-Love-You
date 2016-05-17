package br.com.thinkb.save;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class FindDeviceActivity extends Activity {


    BluetoothAdapter mBlue = BluetoothAdapter.getDefaultAdapter();
    BluetoothDevice targetDevice;

    Params params = Params.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_device_layout);
        final ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);

        Button send = (Button) findViewById(R.id.foundButton);

        if(mBlue.isEnabled()){

            Set temp = mBlue.getBondedDevices();

            CopyOnWriteArrayList<BluetoothDevice> devices = new CopyOnWriteArrayList<>();

            devices.addAll(temp);

            boolean [] index = new boolean[devices.size()];

            TextView txt = (TextView) findViewById(R.id.device_name_found);

            for (int i = 0;i < devices.size();i++){
                if(!devices.get(i).getAddress().toLowerCase().equals("98:D3:31:80:51:21".toLowerCase())){
                    Log.d("TAG","Found : "+devices.get(i).getName());
                    devices.remove(i);
                    index[i] = true;
                }
            }

            if(devices.size()==1){
                txt.setText(devices.get(0).getName());
                bar.setVisibility(ProgressBar.INVISIBLE);
                params.setIsPaired(true);
                targetDevice = devices.get(0);
            }else{
                params.setIsPaired(false);
            }

            if(params.isPaired){
                send.setText(android.R.string.ok);
            }else {
                send.setText("Ir para pareamento...");
            }
            Log.d("TAG", "devices size: "+devices.size());

        }else{
            this.finish();
        }
    }

    public void buttonOnClick(View view){
        if(params.isPaired){
            Intent s = new Intent (this, BluetoothConnectionService.class);

            Bundle device = new Bundle();
            device.putParcelable("device", targetDevice);

            s.putExtras(device);

            startService(s);

            Intent i = new Intent(this, AccountPicker.class);
            Bundle data = new Bundle();
            data.putParcelable("device",targetDevice);
            i.putExtras(data);

            Log.d("FIND_DEVICE", "Connected to : " + targetDevice.getName() + "/" + targetDevice.getAddress());
            BluetoothConnectionService.sendToTarget("R");
            BluetoothConnectionService.sendToTarget("E");

            startActivity(i);
        }else {
            Intent i = new Intent(this, AccountPicker.class);
            startActivity(i);
        }
    }
}
