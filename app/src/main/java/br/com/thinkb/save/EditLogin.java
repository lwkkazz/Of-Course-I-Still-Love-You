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


public class EditLogin extends Activity {


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
        setContentView(R.layout.edit_login_layout);

        name       = (EditText) findViewById(R.id.changeTextName);
        surname    = (EditText) findViewById(R.id.changeTextSurname);
        dataNasc   = (EditText) findViewById(R.id.changeTextDate);
        rgId       = (EditText) findViewById(R.id.changeTextRgid);
        cpfId      = (EditText) findViewById(R.id.changeTextCpfid);
        address    = (EditText) findViewById(R.id.changeTextAddress);
        city       = (EditText) findViewById(R.id.changeTextCity);
        zipcode    = (EditText) findViewById(R.id.changeTextZipcode);
        phone      = (EditText) findViewById(R.id.changeTextPhone);

        Toast.makeText(this, "Preencha todos os campos corretamente",Toast.LENGTH_SHORT).show();

    }


    public void changeAllData(View view){

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

            //TODO

            LocalDatabaseTask dbTask = new LocalDatabaseTask(this);

            dataAcquired.putString("user", "bruna");

            dbTask.updateValue(dataAcquired);

            Toast.makeText(this, "Cadastro atualizado com sucesso!", Toast.LENGTH_SHORT).show();


            Thread thread = new Thread(){

                public void run(){
                    try {
                        Thread.sleep(200);
                        finish();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            };
            thread.start();




        }
        Toast.makeText(this, "O forumlário deve ser preenchido completamente.", Toast.LENGTH_SHORT).show();
    }

    public void goBack(View view){
        finish();
    }
}
