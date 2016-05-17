package br.com.thinkb.save;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StartUp extends Activity {

    protected void onCreate(Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);

        SharedPreferences sharedSetup = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);


        Log.d("TAG", "INICIO");

        Thread thread = new Thread(){

            public void run(){
                try {
                    Thread.sleep(3000);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
