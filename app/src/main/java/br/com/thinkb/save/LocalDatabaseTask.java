package br.com.thinkb.save;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class LocalDatabaseTask extends SQLiteOpenHelper {

    private static final String DB_USER_DATA = "TABLE_USER_DATA";
    //private static final String DB_MED_DATA = "TABLE_MED_DATA";
    //private static final String DB_DRUGS = "TABLE_DRUGS";

    private static final int DB_VERS = 1;

    private static final String KEY_ROWID = "_id";



    private static final String KEY_USER_LOGIN = "login";
    private static final String KEY_USER_PASSWORD = "password";


    private static final String KEY_BAND_ID         = "band_id";
    private static final String KEY_USER_NAME       = "name";
    private static final String KEY_USER_SURNAME    = "surname";
    private static final String KEY_USER_DATE       = "date";
    private static final String KEY_USER_GEN_ID     = "general_id";
    private static final String KEY_USER_CPF_ID     = "cpf_id";
    private static final String KEY_USER_ADDRESS    = "address";
    private static final String KEY_USER_CITY       = "city";
    private static final String KEY_USER_ZIPCODE    = "zipcode";
    private static final String KEY_USER_PHONE      = "phone";

/*
    private static final String KEY_MED_PRIV_CARE   = "priv_care";
    private static final String KEY_MED_ID_CARD     = "id_card";
    private static final String KEY_MED_HOSPITAL    = "hospital";
    private static final String KEY_MED_BLOOD_TYPE  = "blood_type";
    private static final String KEY_MED_BLOOD_PRESS = "blood_press";

    private static final String KEY_DRUG_ID         = "drug_id";
    private static final String KEY_DRUGS_NAME      = "drug_name";
    private static final String KEY_DRUGS_TIME      = "time";
    private static final String KEY_DRUGS_DOSE      = "dose";
    private static final String KEY_DRUGS_UNITS     = "units";
    private static final String KEY_DRUGS_ANNOTS    = "annots";
*/







    public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_BAND_ID, KEY_USER_NAME, KEY_USER_SURNAME,
            KEY_USER_DATE, KEY_USER_GEN_ID, KEY_USER_CPF_ID, KEY_USER_ADDRESS, KEY_USER_CITY,
            KEY_USER_ZIPCODE, KEY_USER_PHONE, KEY_USER_LOGIN, KEY_USER_PASSWORD};


    public LocalDatabaseTask(Context ctx){
        super(ctx, DB_USER_DATA, null, DB_VERS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable1 ="CREATE TABLE "+ DB_USER_DATA +"("+
                                    KEY_ROWID           +" BIGINT NOT NULL, "+
                                    //KEY_BAND_ID         +" VARCHAR(17) NOT NULL, "+
                                    KEY_USER_NAME       +" VARCHAR(40) NOT NULL, "+
                                    KEY_USER_SURNAME    +" VARCHAR(40) NOT NULL, "+
                                    KEY_USER_DATE       +" VARCHAR(40) NOT NULL, " +
                KEY_USER_GEN_ID +" VARCHAR(40) NOT NULL, " +
                KEY_USER_CPF_ID +" VARCHAR(40) NOT NULL, " +
                KEY_USER_ADDRESS +" VARCHAR(40) NOT NULL, " +
                KEY_USER_CITY +" VARCHAR(40) NOT NULL, " +
                KEY_USER_ZIPCODE +" VARCHAR(40) NOT NULL, " +
                KEY_USER_PHONE +" VARCHAR(40) NOT NULL, " +
                KEY_USER_LOGIN +" VARCHAR(16) NOT NULL, " +
                KEY_USER_PASSWORD +" VARCHAR(32) NOT NULL," +
                                    "CONSTRAINT pk_Band PRIMARY KEY("+KEY_ROWID+"));\n";

        db.execSQL(createTable1);

        Log.d("TAG_DBTASK", "Sucesso!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS " + DB_USER_DATA;

        db.execSQL(dropTable);

        onCreate(db);
    }







    public void insertValue(Bundle data){


        SQLiteDatabase dbWriter = getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        String name     = data.getString("data00");
        String surname  = data.getString("data01");
        String date     = data.getString("data02");
        String genId    = data.getString("data03");
        String cpfId    = data.getString("data04");
        String address  = data.getString("data05");
        String city     = data.getString("data06");
        String zipcode  = data.getString("data07");
        String phone    = data.getString("data08");

        String login    = data.getString("login");
        String password = data.getString("password");

        long masterId;


        String temp = password + Params.md5(login);

        masterId = hex2decimal(temp);



        contentValues.put(KEY_ROWID, masterId);
        contentValues.put(KEY_USER_NAME,name);
        contentValues.put(KEY_USER_SURNAME,surname);
        contentValues.put(KEY_USER_DATE,date);
        contentValues.put(KEY_USER_GEN_ID,genId);
        contentValues.put(KEY_USER_CPF_ID,cpfId);
        contentValues.put(KEY_USER_ADDRESS,address);
        contentValues.put(KEY_USER_CITY,city);
        contentValues.put(KEY_USER_ZIPCODE, zipcode);
        contentValues.put(KEY_USER_PHONE, phone);



        Log.d("LOCAL_DATABASE", "pass: "+password);
        contentValues.put(KEY_USER_LOGIN, login);
        contentValues.put(KEY_USER_PASSWORD, password);

        dbWriter.insert(DB_USER_DATA,null,contentValues);

        dbWriter.close();
    }





    public void updateValue(Bundle data){


        SQLiteDatabase dbWriter = getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        String name     = data.getString("data00");
        String surname  = data.getString("data01");
        String date     = data.getString("data02");
        String genId    = data.getString("data03");
        String cpfId    = data.getString("data04");
        String address  = data.getString("data05");
        String city     = data.getString("data06");
        String zipcode  = data.getString("data07");
        String phone    = data.getString("data08");

        String login    = data.getString("login");
        String password = data.getString("password");

        long masterId;


        String temp = password + Params.md5(login);

        masterId = hex2decimal(temp);



        contentValues.put(KEY_ROWID, masterId);
        contentValues.put(KEY_USER_NAME,name);
        contentValues.put(KEY_USER_SURNAME,surname);
        contentValues.put(KEY_USER_DATE,date);
        contentValues.put(KEY_USER_GEN_ID,genId);
        contentValues.put(KEY_USER_CPF_ID,cpfId);
        contentValues.put(KEY_USER_ADDRESS,address);
        contentValues.put(KEY_USER_CITY,city);
        contentValues.put(KEY_USER_ZIPCODE, zipcode);
        contentValues.put(KEY_USER_PHONE, phone);



        Log.d("LOCAL_DATABASE", "pass: "+password);
        contentValues.put(KEY_USER_LOGIN, login);
        contentValues.put(KEY_USER_PASSWORD, password);



        dbWriter.update(DB_USER_DATA, contentValues, KEY_USER_LOGIN+" = "+data.getString("login"), null);

        dbWriter.close();
    }

    public boolean getLogin(Bundle data){

        String[] columns = new String[]{KEY_USER_LOGIN, KEY_USER_PASSWORD};

        String login    = data.getString("login");
        String temp     = data.getString("pass");

        //  getPass = md5(String.valueOf(password));
        String pass = Params.md5(temp.toLowerCase());

        SQLiteDatabase db = getReadableDatabase();

        String where = KEY_USER_LOGIN + " = "+"\""+login+"\"";
        where.concat(" AND "+ KEY_USER_PASSWORD+" = "+"\""+pass+"\"");


        Cursor cursor  = db.query(DB_USER_DATA, columns, where, null, "", "", "");

        if(cursor == null){
            return  false;
        }else{
            cursor.moveToFirst();
            cursor.getColumnCount();
            //String storedPass = cursor.getString(cursor.getColumnIndex(KEY_USER_PASSWORD));
            //Log.d("LOCAL_DATABASE", "Retrieved the following pass: "+storedPass+" with this pass being inserted: "+pass+". Proceding to conn.");
            return true;
        }
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

    public Cursor listAll(){

        SQLiteDatabase dbReader = getReadableDatabase();
        Cursor cursor   =   dbReader.query(DB_USER_DATA, ALL_KEYS, null, null, null, null, null);

        if(cursor.getCount() == 0) {

            Log.d("DB_TASKS", "Query returned no results");

            cursor = null;
        }

        if(cursor != null) {
            cursor.moveToFirst();
        }else
            return null;

        return cursor;
    }
}

