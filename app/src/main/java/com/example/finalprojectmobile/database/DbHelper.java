package com.example.finalprojectmobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.example.finalprojectmobile.photo.Photo;
import com.example.finalprojectmobile.user.User;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "finalProject.db";

    private static final String SQL_CREATE_ENTRIES_USER =
            "CREATE TABLE " + DbContract.DbEntry.TABLE_NAME_USER + " (" +
                    DbContract.DbEntry._ID + " INTEGER PRIMARY KEY," +
                    DbContract.DbEntry.COLUMN_NAME_USERNAME_USER + " TEXT," +
                    DbContract.DbEntry.COLUMN_NAME_PASSWORD_USER + " TEXT," +
                    DbContract.DbEntry.COLUMN_NAME_EMAIL_USER + " TEXT," +
                    DbContract.DbEntry.COLUMN_NAME_TYPE_USER + " TEXT," +
                    DbContract.DbEntry.COLUMN_NAME_ADMINISTRATOR_USER + " INTEGER)";

    private static final String SQL_CREATE_ENTRIES_PHOTO =
            "CREATE TABLE " + DbContract.DbEntry.TABLE_NAME_PHOTO + " (" +
                    DbContract.DbEntry._ID + " INTEGER PRIMARY KEY," +
                    DbContract.DbEntry.COLUMN_NAME_AUTHOR_PHOTO + " TEXT," +
                    DbContract.DbEntry.COLUMN_NAME_BYTE_PHOTO + " BLOB," +
                    DbContract.DbEntry.COLUMN_NAME_DESCRIPTION_PHOTO + " TEXT," +
                    DbContract.DbEntry.COLUMN_NAME_HASHTAGS_PHOTO + " TEXT," +
                    DbContract.DbEntry.COLUMN_NAME_TYPE_PHOTO + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_USER =
            "DROP TABLE IF EXISTS " + DbContract.DbEntry.TABLE_NAME_USER;

    private static final String SQL_DELETE_ENTRIES_PHOTO =
            "DROP TABLE IF EXISTS " + DbContract.DbEntry.TABLE_NAME_PHOTO;

    private static final String USER_SELECT_ALL = "SELECT * FROM " + DbContract.DbEntry.TABLE_NAME_USER;

    public static final String PHOTO_SELECT_ALL = "SELECT * FROM " + DbContract.DbEntry.TABLE_NAME_PHOTO;

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES_USER);
        db.execSQL(SQL_CREATE_ENTRIES_PHOTO);
    }

    public void onCreateUser(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES_USER);
    }

    public void onCreatePhoto(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES_PHOTO);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public void deleteUserTable(SQLiteDatabase db){
        db.execSQL(SQL_DELETE_ENTRIES_USER);
        onCreateUser(db);
    }

    public void deletePhotoTable(SQLiteDatabase db){
        db.execSQL(SQL_DELETE_ENTRIES_PHOTO);
        onCreatePhoto(db);
    }

    public void addUser(SQLiteDatabase db, User user){
        ContentValues values = new ContentValues();
        values.put(DbContract.DbEntry.COLUMN_NAME_USERNAME_USER, user.getUsername());
        values.put(DbContract.DbEntry.COLUMN_NAME_PASSWORD_USER, user.getPassword());
        values.put(DbContract.DbEntry.COLUMN_NAME_EMAIL_USER, user.getEmail());
        values.put(DbContract.DbEntry.COLUMN_NAME_TYPE_USER, user.getType());
        values.put(DbContract.DbEntry.COLUMN_NAME_ADMINISTRATOR_USER, user.getAdministrator());

        long newRowId = db.insert(DbContract.DbEntry.TABLE_NAME_USER, null, values);
    }

    public void addPhoto(SQLiteDatabase db, Photo photo){
        ContentValues values = new ContentValues();
        values.put(DbContract.DbEntry.COLUMN_NAME_AUTHOR_PHOTO, photo.getAuthor().getUsername());
        values.put(DbContract.DbEntry.COLUMN_NAME_BYTE_PHOTO, photo.getImage());
        values.put(DbContract.DbEntry.COLUMN_NAME_DESCRIPTION_PHOTO, photo.getDescription());
        String hashtagsStr = "";
        String[] hashtags = photo.getHashtags();
        for(int i = 0; i < hashtags.length; i++){
            hashtagsStr += "#" + hashtags[i];
        }
        values.put(DbContract.DbEntry.COLUMN_NAME_HASHTAGS_PHOTO, hashtagsStr);
        values.put(DbContract.DbEntry.COLUMN_NAME_TYPE_PHOTO, photo.getType());

        long newRowId = db.insert(DbContract.DbEntry.TABLE_NAME_PHOTO, null, values);
    }

    public void deleteUser(SQLiteDatabase db, String username){
        String query = "DELETE FROM " + DbContract.DbEntry.TABLE_NAME_USER + " WHERE username = '" + username + "'";
        db.execSQL(query);
    }

    public void deletePhoto(SQLiteDatabase db, String uri){
        String query = "DELETE FROM " + DbContract.DbEntry.TABLE_NAME_PHOTO + " WHERE uri = '" + uri + "'";
        db.execSQL(query);
    }

    public String displayUsers(SQLiteDatabase db){
        List<User> users = new ArrayList<User>();
        Cursor cursor = db.rawQuery(USER_SELECT_ALL, null);
        String displayUsers = "Here are the registered users:\n\n(id. username | password\nemail | " +
                "registration | status)\n\n";
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            String isAdministrator = "User";
            if(cursor.getInt(5) == 1){
                isAdministrator = "Administrator";
            }
            displayUsers += cursor.getInt(0) + ". " + cursor.getString(1) + " | " +
                cursor.getString(2) + "\n" + cursor.getString(3) + " | " + cursor.getString(4) +
                    " | " + isAdministrator + "\n\n";
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
            displayUsernames += cursor.getString(1) + "\n\n";
            cursor.moveToNext();
        }
        return displayUsernames;
    }

    public int numberPhotos(SQLiteDatabase db){
        Cursor cursor = db.rawQuery(PHOTO_SELECT_ALL, null);
        return cursor.getCount();
    }

    public void deleteTable(SQLiteDatabase db){
        db.execSQL(SQL_DELETE_ENTRIES_USER);
        db.execSQL(SQL_DELETE_ENTRIES_PHOTO);
    }

    public User findUser(SQLiteDatabase db, String username){
        User user = User.getInstance();
        user.setUsername(username);
        String USER_FIND_USERNAME = "SELECT * FROM " + DbContract.DbEntry.TABLE_NAME_USER +
                " WHERE USERNAME = '" + username + "'";
        Cursor cursor = db.rawQuery(USER_FIND_USERNAME, null);
        if(cursor.moveToFirst()){
            user.setPassword(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            String type = cursor.getString(4);
            if(type.equals("Free")){
                user.setType("free");
            } else if(type.equals("Premium ($4.99/month)")){
                user.setType("premium");
            } else {
                user.setType("gold");
            }
            user.setAdministrator(cursor.getInt((5)));
        } else {
            User notFound = User.getInstance();
            notFound.setUsername("USER NOT FOUND");
            return notFound;
        }
        return user;
    }

    public void changeType(SQLiteDatabase db, User user){
        String query = "UPDATE " + DbContract.DbEntry.TABLE_NAME_USER + " SET type = '" +
        user.getType() + "' WHERE username = '" + user.getUsername() + "'";
        db.execSQL(query);
    }
}
