package br.com.thinkb.save;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hist_layout);

        BluetoothConnectionService.sendToTarget("E");

        TextView text = (TextView) findViewById(R.id.textView23);


        if(Params.getInstance().getLedState()){
            text.setText("Emergencia identificada!");
        }else{
            text.setText("Não há ocorrências!");
        }
    }

    public void onAcknowledge(View view){
        BluetoothConnectionService.sendToTarget("R");
        Thread thread = new Thread(){

            public void run(){
                try {
                    Thread.sleep(500);
                    BluetoothConnectionService.sendToTarget("E");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
