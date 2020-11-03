package com.example.finalprojectmobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.finalprojectmobile.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "instagramApp.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " (" +
                    UserContract.UserEntry._ID + " INTEGER PRIMARY KEY," +
                    UserContract.UserEntry.COLUMN_NAME_USERNAME + " TEXT," +
                    UserContract.UserEntry.COLUMN_NAME_PASSWORD + " TEXT," +
                    UserContract.UserEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    UserContract.UserEntry.COLUMN_NAME_TYPE + " TEXT," +
                    UserContract.UserEntry.COLUMN_NAME_ADMINISTRATOR + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME;

    private static final String USER_SELECT_ALL = "SELECT * FROM " + UserContract.UserEntry.TABLE_NAME;

    public UserDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addUser(SQLiteDatabase db, User user){
        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.COLUMN_NAME_USERNAME, user.getUsername());
        values.put(UserContract.UserEntry.COLUMN_NAME_PASSWORD, user.getPassword());
        values.put(UserContract.UserEntry.COLUMN_NAME_EMAIL, user.getEmail());
        values.put(UserContract.UserEntry.COLUMN_NAME_TYPE, user.getType());
        values.put(UserContract.UserEntry.COLUMN_NAME_ADMINISTRATOR, user.getAdministrator());

        long newRowId = db.insert(UserContract.UserEntry.TABLE_NAME, null, values);
    }

    public String displayUsers(SQLiteDatabase db){
        List<User> users = new ArrayList<User>();
        Cursor cursor = db.rawQuery(USER_SELECT_ALL, null);
        String displayUsers = "Here are the registered users:\n(id | username | password | email | " +
                "type of registration | user or administrator)\n";
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            String isAdministrator = "User";
            if(cursor.getInt(5) == 1){
                isAdministrator = "Administrator";
            }
            displayUsers += cursor.getInt(0) + " " + cursor.getString(1) + " | " +
                cursor.getString(2) + " | " + cursor.getString(3) + " | " + cursor.getString(4) +
                    " | " + isAdministrator + "\n";
            cursor.moveToNext();
        }
        return displayUsers;
    }

    public String displayUsernames(SQLiteDatabase db){
        List<String> usernames = new ArrayList<>();
        Cursor cursor = db.rawQuery(USER_SELECT_ALL, null);
        String displayUsernames = "";
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            displayUsernames += cursor.getString(1) + "\n";
            cursor.moveToNext();
        }
        return displayUsernames;
    }

    public void deleteTable(SQLiteDatabase db){
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    public User findUser(SQLiteDatabase db, String username){
        User user = User.getInstance();
        user.setUsername(username);
        String USER_FIND_USERNAME = "SELECT * FROM " + UserContract.UserEntry.TABLE_NAME +
                " WHERE USERNAME = '" + username + "'";
        Cursor cursor = db.rawQuery(USER_FIND_USERNAME, null);
        if(cursor.moveToFirst()){
            user.setPassword(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            String type = cursor.getString(4);
            if(type == "FREE"){
                user.setType(2131230831);
            } else if(type == "PREMIUM"){
                user.setType(2131230882);
            } else {
                user.setType(0);
            }
            user.setAdministrator(cursor.getInt((5)));
        } else {
            User notFound = User.getInstance();
            notFound.setUsername("USER NOT FOUND");
            return notFound;
        }
        return user;
    }
}
