package br.com.thinkb.save;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.awt.font.TextAttribute;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        TextView text = (TextView) findViewById(R.id.menuEmergency);

        if(Params.getInstance().getLedState()){
            text.setText("Emergência!");
            text.setTextColor(getResources().getColor(R.color.red));
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), HistActivity.class);
                    startActivity(i);
                }
            });
        }else{
            text.setText("Nenhuma Emergência");
        }

    }


    public void medOnClick(View view){
        Intent i = new Intent(this, CreateAlarm.class);
        startActivity(i);
    }

    public void locOnClick(View view){

        Uri geoLocation = Uri.parse("geo:-23.625089, -46.692573?z=17");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void hisOnClick(View view){
        Intent i = new Intent(this, HistActivity.class);
        startActivity(i);
    }

    public void ediOnClick(View view){
        Intent i = new Intent(this, EditLogin.class);
        startActivity(i);
    }
}
