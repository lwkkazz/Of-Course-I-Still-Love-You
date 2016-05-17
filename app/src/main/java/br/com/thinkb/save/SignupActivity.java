package br.com.thinkb.save;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;


public class SignupActivity extends Activity {


    String[] data = new String[9];
    Bundle dataAcquired = new Bundle();

    EditText name;
    EditText surname;
    EditText dataNasc;
    EditText rgId;
    EditText cpfId;
    EditText address;
    EditText city;
    EditText zipcode;
    EditText phone;

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
        setContentView(R.layout.signup_layout);

        name       = (EditText) findViewById(R.id.editTextName);
        surname    = (EditText) findViewById(R.id.editTextSurname);
        dataNasc   = (EditText) findViewById(R.id.editTextDate);
        rgId       = (EditText) findViewById(R.id.editTextRgid);
        cpfId      = (EditText) findViewById(R.id.editTextCpfid);
        address    = (EditText) findViewById(R.id.editTextAddress);
        city       = (EditText) findViewById(R.id.editTextCity);
        zipcode    = (EditText) findViewById(R.id.editTextZipcode);
        phone      = (EditText) findViewById(R.id.editTextPhone);

        Toast.makeText(this, "Preencha todos os campos corretamente",Toast.LENGTH_SHORT).show();

    }


    public void getAllData(View view){

        if((!String.valueOf(name.getText()).equals("")   &&
           !String.valueOf(surname.getText()).equals("") &&
           !String.valueOf(dataNasc.getText()).equals("")&&
           !String.valueOf(rgId.getText()).equals("")    &&
           !String.valueOf(cpfId.getText()).equals("")   &&
           !String.valueOf(address.getText()).equals("") &&
           !String.valueOf(city.getText()).equals("") &&
           !String.valueOf(zipcode.getText()).equals("") &&
           !String.valueOf(phone.getText()).equals(""))){

            data[0] = String.valueOf(name.getText());
            Log.d("TAG","Data acquired: "+data[0]);

            data[1] = String.valueOf(surname.getText());
            Log.d("TAG","Data acquired: "+data[1]);

            data[2] = String.valueOf(dataNasc.getText());
            Log.d("TAG","Data acquired: "+data[2]);

            data[3] = String.valueOf(rgId.getText());
            Log.d("TAG","Data acquired: "+data[3]);

            data[4] = String.valueOf(cpfId.getText());
            Log.d("TAG","Data acquired: "+data[4]);

            data[5] = String.valueOf(address.getText());
            Log.d("TAG","Data acquired: "+data[5]);

            data[6] = String.valueOf(city.getText());
            Log.d("TAG","Data acquired: "+data[6]);

            data[7] = String.valueOf(zipcode.getText());
            Log.d("TAG","Data acquired: "+data[7]);

            data[8] = String.valueOf(phone.getText());
            Log.d("TAG", "Data acquired: " + data[8]);

            for(int i = 0; i < data.length ; i++){
                dataAcquired.putString("data0"+i,data[i]);
            }
            Intent i = new Intent(this, GetPasswordAndLogin.class);
            i.putExtras(dataAcquired);
            startActivity(i);
        }
        Toast.makeText(this, "O forumlário deve ser preenchido completamente.", Toast.LENGTH_SHORT).show();
    }

    public void goBack(View view){
        finish();
    }
}
