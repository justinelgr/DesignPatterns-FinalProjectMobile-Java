package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.finalprojectmobile.database.DbHelper;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
    }

    public void goToLoginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void cleanUserTable(View view){
        dbHelper.deleteUserTable(db);
    }

    public void cleanPhotoTable(View view){
        dbHelper.deletePhotoTable(db);
    }
}