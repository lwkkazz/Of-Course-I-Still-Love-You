package br.com.thinkb.save;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GetPasswordAndLogin extends AppCompatActivity {

    LocalDatabaseTask dbTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_password_and_login_layout);

        dbTask = new LocalDatabaseTask(this);

    }


    public void onDoneRequest(View view) {
        EditText login = (EditText) findViewById(R.id.insertLogin);
        EditText password = (EditText) findViewById(R.id.insertPassword);


        boolean canStart = true;

        String getLogin = null;
        String getPass = null;


        if(!String.valueOf(login.getText()).equals("")) {
            getLogin = String.valueOf(login.getText()).toLowerCase();
        }else{
            canStart=false;
        }

        if(!String.valueOf(password.getText()).equals("")) {
            getPass = Params.md5(String.valueOf(password).toLowerCase());
        }else{
            canStart=false;
        }

        if(canStart) {
            Bundle data = getIntent().getExtras();
            data.putString("login", getLogin);
            data.putString("password", getPass);
            Intent i = new Intent(getApplicationContext(), MenuActivity.class);
            dbTask.insertValue(data);
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(),"Insira as informações pedidas",Toast.LENGTH_SHORT).show();
        }
    }
}
