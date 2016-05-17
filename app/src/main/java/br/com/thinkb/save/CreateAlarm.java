package br.com.thinkb.save;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class CreateAlarm extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_alarm_layout);


    }


    public void createDrugTime(View view){

        EditText name   = (EditText) findViewById(R.id.drug_name);
        EditText obs    = (EditText) findViewById(R.id.drug_obs);
        EditText hour   = (EditText) findViewById(R.id.drug_hour);
        EditText minute = (EditText) findViewById(R.id.drug_minute);

        String getName;
        String getHour;
        String getMinute;


        boolean canCreate = true;


        if(String.valueOf(name.getText()).equals("")){
            canCreate=false;
        }
        if(String.valueOf(obs.getText()).equals("")){
            canCreate=false;
        }
        if(String.valueOf(hour.getText()).equals("")){
            canCreate=false;
        }
        if(String.valueOf(minute.getText()).equals("")){
            getMinute = "00";
        }

        int temp = Integer.parseInt(String.valueOf(hour.getText()));
        if((temp<0)||(temp>=24)){
            canCreate = false;
            Toast.makeText(this, "Insira um horário válido", Toast.LENGTH_SHORT);
        }

        temp = Integer.parseInt(String.valueOf(hour.getText()));
        if((temp<0)||(temp>=60)){
            canCreate = false;
            Toast.makeText(this, "Insira um valor para minutos válido", Toast.LENGTH_SHORT).show();
        }

        getName     = String.valueOf(name.getText());
        //getObs      = String.valueOf(obs.getText());
        getHour     = String.valueOf(hour.getText());
        getMinute   = String.valueOf(minute.getText());

        ArrayList days;
        days = new ArrayList();

        days.add(Calendar.SUNDAY);
        days.add(Calendar.MONDAY);
        days.add(Calendar.TUESDAY);
        days.add(Calendar.WEDNESDAY);
        days.add(Calendar.THURSDAY);
        days.add(Calendar.FRIDAY);
        days.add(Calendar.SATURDAY);

        if(canCreate){
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                    .putExtra(AlarmClock.EXTRA_MESSAGE, getName)
                    .putExtra(AlarmClock.EXTRA_HOUR, getHour)
                    .putExtra(AlarmClock.EXTRA_MINUTES, getMinute)
                    .putExtra(AlarmClock.EXTRA_DAYS, days)
                    .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Não foi possivel criar um novo alarme",Toast.LENGTH_SHORT).show();
        }
    }

    public void goBack(View view){
        finish();
    }
}
