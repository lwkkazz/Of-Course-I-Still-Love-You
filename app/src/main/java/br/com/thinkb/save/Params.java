package br.com.thinkb.save;


import android.app.Activity;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Params {

    boolean isPaired = false;
    private boolean isLogin = false;

    private boolean ledState = false;

    final int MESSAGE_RECEIVED = 127;


    private static Params ourInstance = new Params();

    public static Params getInstance() {
        return ourInstance;
    }

    private Params() {
    }


    public boolean getLedState(){return ledState;}
    public void setLedState(boolean ledState){this.ledState = ledState;}

    public boolean getIsPaired(){return isPaired;}
    public void setIsPaired(boolean isPaired){this.isPaired = isPaired;}


    public boolean getIsLogin(){return isLogin;}
    public void setIsLogin(boolean isLogin){this.isLogin = isLogin;}


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
            Log.d("MESSAGE_DIGEST", "Falha ao recuperar algoritmo de encriptação.");
        }
        return "";
    }

    public static long hex2decimal(String s) {
        String digits = s;
        s = s.toUpperCase();
        long val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }

    public void goBack(Activity activity){
        activity.finish();
    }
}
