package br.com.thinkb.save;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    LocalDatabaseTask dbTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));


        dbTask = new LocalDatabaseTask(getApplicationContext());

        setContentView(R.layout.login_activity);
    }

    public void onStartSign(View view){

        EditText login  = (EditText) findViewById(R.id.editText);
        EditText pass   = (EditText) findViewById(R.id.editText2);


        String getLogin = String.valueOf(login.getText());
        String getPass  = String.valueOf(pass.getText());

        Bundle data = new Bundle();


        data.putString("login", getLogin);
        data.putString("pass", getPass);


        if((!getLogin.equals(""))&&(!getPass.equals(""))) {
            if (dbTask.getLogin(data)) {
                Toast.makeText(this, "Logado com sucesso!", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(this, MenuActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(this, "Login ou senha errado.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Complete as informações requisitadas.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onSignUp(View view){

        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);

    }
}
