package br.com.thinkb.save;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {


    BluetoothAdapter mBlue = BluetoothAdapter.getDefaultAdapter();


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP){
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        setContentView(R.layout.activity_main);




        Button bt = (Button) findViewById(R.id.button);


        bt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(getApplicationContext(), FindDeviceActivity.class);
                startActivity(i);

                return true;
            }
        });
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(i, BluetoothAdapter.STATE_ON);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(!mBlue.isEnabled()) {
            if (resultCode != BluetoothAdapter.STATE_ON) {

                Log.d("MAIN", "result code: " + resultCode);
                AlertDialog.Builder popup = new AlertDialog.Builder(this);

                popup.setTitle(R.string.menu_warning);
                popup.setMessage(R.string.menu_warning_msg);
                popup.setCancelable(true);
                popup.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(i, BluetoothAdapter.STATE_ON);
                        dialog.cancel();
                    }
                });

                popup.create();
                popup.show();

            }
        }
    }

    public void onStub(View view){

        AlertDialog.Builder popup = new AlertDialog.Builder(this);

        popup.setTitle(R.string.menu_help);
        popup.setMessage(R.string.menu_help_msg);
        popup.setCancelable(true);
        popup.setPositiveButton("Ok!", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        popup.create();
        popup.show();

    }


    public void onConnectClick(View view){
        Intent i = new Intent(this, FindDeviceActivity.class);
        startActivity(i);
    }
}
