package com.example.finalprojectmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler {
    myDbHelper myhelper;
    public void myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String username, String password, String email, String type)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.USERNAME, username);
        contentValues.put(myDbHelper.PASSWORD, password);
        contentValues.put(myDbHelper.EMAIL, email);
        contentValues.put(myDbHelper.TYPE, type);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.USERNAME,myDbHelper.PASSWORD};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String username = cursor.getString(cursor.getColumnIndex(myDbHelper.USERNAME));
            String  password = cursor.getString(cursor.getColumnIndex(myDbHelper.PASSWORD));
            String  email = cursor.getString(cursor.getColumnIndex(myDbHelper.EMAIL));
            String  type = cursor.getString(cursor.getColumnIndex(myDbHelper.TYPE));
            buffer.append(cid+ "   " + username + "   " + password + "   " + email + "   " + type + " \n");
        }
        return buffer.toString();
    }

    public  int delete(String username)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={username};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.USERNAME+" = ?",whereArgs);
        return  count;
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "instagramApp.db";    // Database Name
        private static final String TABLE_NAME = "Users";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID ="_id";     // Column I (Primary Key)
        private static final String USERNAME = "Username";    //Column II
        private static final String PASSWORD = "Password";    // Column III
        private static final String EMAIL = "Email";    // Column IV
        private static final String TYPE = "Type";    // Column V
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+USERNAME+" VARCHAR(255) ,"+ PASSWORD+" VARCHAR(225) ,"+EMAIL+" VARCHAR(255) ,"+TYPE+" VARCHAR(255)) ;";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {

            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {

            }
        }
    }
}
