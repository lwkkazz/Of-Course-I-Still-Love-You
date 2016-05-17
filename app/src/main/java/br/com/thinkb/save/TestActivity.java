package br.com.thinkb.save;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


    }

    public void onSendToTargetButton(View view){


        TextView text = (TextView) findViewById(R.id.textView22);
        EditText edit = (EditText) findViewById(R.id.editText6);

        String getText = String.valueOf(edit.getText());

        BluetoothConnectionService.sendToTarget(getText);
    }
}
