package br.com.thinkb.save;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class SosActivityController extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sos_layout);


    }


    public void onCallSamu(View view) {
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel: 193"));
        try {
            startActivity(i);
        }catch (Exception e){
            Log.d("SOS_ACTIVITY","Log: "+e.getMessage());
            Toast.makeText(this, "Não foi possivel realizar a chamada.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onCallPol(View view){
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel: 190"));
        try {
            startActivity(i);
        }catch (Exception e){
            Toast.makeText(this, "Não foi possivel realizar a chamada.", Toast.LENGTH_SHORT).show();
        }
    }
    public void goBack(View view){
        finish();
    }
}
