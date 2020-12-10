package com.example.finalprojectmobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.finalprojectmobile.photo.BMP;
import com.example.finalprojectmobile.photo.JPEG;
import com.example.finalprojectmobile.photo.PNG;
import com.example.finalprojectmobile.photo.Photo;
import com.example.finalprojectmobile.photo.PhotoFactory;
import com.example.finalprojectmobile.user.User;

import java.util.ArrayList;
import java.util.List;

public class PhotoDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "instagramApp.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PhotoContract.PhotoEntry.TABLE_NAME + " (" +
                    PhotoContract.PhotoEntry._ID + " INTEGER PRIMARY KEY," +
                    PhotoContract.PhotoEntry.COLUMN_NAME_AUTHOR + " TEXT," +
                    PhotoContract.PhotoEntry.COLUMN_NAME_URI + " TEXT," +
                    PhotoContract.PhotoEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    PhotoContract.PhotoEntry.COLUMN_NAME_HASHTAGS + " TEXT," +
                    PhotoContract.PhotoEntry.COLUMN_NAME_TYPE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PhotoContract.PhotoEntry.TABLE_NAME;

    private static final String PHOTO_SELECT_ALL = "SELECT * FROM " + PhotoContract.PhotoEntry.TABLE_NAME;

    public PhotoDbHelper(Context context){
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

    public void addPhoto(SQLiteDatabase db, Photo photo){
        ContentValues values = new ContentValues();
        values.put(PhotoContract.PhotoEntry.COLUMN_NAME_AUTHOR, photo.getAuthor().getUsername());
        values.put(PhotoContract.PhotoEntry.COLUMN_NAME_URI, photo.getUri());
        values.put(PhotoContract.PhotoEntry.COLUMN_NAME_DESCRIPTION, photo.getDescription());
        values.put(PhotoContract.PhotoEntry.COLUMN_NAME_HASHTAGS, photo.getHashtags().toString());
        values.put(PhotoContract.PhotoEntry.COLUMN_NAME_TYPE, photo.getType());

        long newRowId = db.insert(PhotoContract.PhotoEntry.TABLE_NAME, null, values);
    }

    public void deletePhoto(SQLiteDatabase db, String uri){
        String query = "DELETE FROM " + PhotoContract.PhotoEntry.TABLE_NAME + " WHERE uri = '" + uri + "'";
        db.execSQL(query);
    }

    public String[] displayHomePage(SQLiteDatabase db){
        Cursor cursor = db.rawQuery(PHOTO_SELECT_ALL, null);
        cursor.moveToFirst();
        String[] authorUris = new String[20];
        if(cursor.getCount() > 10){
            for(int i = 0; i < 10; i++){
                authorUris[i] = cursor.getString(1);
                authorUris[i+1] = cursor.getString(2);
                cursor.moveToNext();
            }
        }
        else{
            for(int i = 0; i < cursor.getCount(); i++){
                authorUris[i] = cursor.getString(1);
                authorUris[i+1] = cursor.getString(2);
                cursor.moveToNext();
            }
        }
        return authorUris;
    }

    public void deleteTable(SQLiteDatabase db){
        db.execSQL(SQL_DELETE_ENTRIES);
    }
}
