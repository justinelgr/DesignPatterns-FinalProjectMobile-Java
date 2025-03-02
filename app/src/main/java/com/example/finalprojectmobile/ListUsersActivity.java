package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finalprojectmobile.database.DbHelper;
import com.example.finalprojectmobile.user.User;

public class ListUsersActivity extends AppCompatActivity {

    User user;
    DbHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        user = (User)getIntent().getSerializableExtra("User");
        TextView username = (TextView)findViewById(R.id.username);
        username.setText(user.getUsername());

        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        TextView list = (TextView)findViewById(R.id.list);

        if(user.getAdministrator() == 0){
            list.setText(dbHelper.displayUsernames(db));
        }
        else if(user.getAdministrator() == 1){
            list.setText(dbHelper.displayUsers(db));
        }
    }
}