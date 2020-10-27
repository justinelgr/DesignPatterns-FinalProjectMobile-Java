package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finalprojectmobile.database.UserDbHelper;
import com.example.finalprojectmobile.user.User;

public class ListUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        User user = (User)getIntent().getSerializableExtra("User");
        TextView username = (TextView)findViewById(R.id.username);
        username.setText(user.getUsername());

        UserDbHelper dbHelper = new UserDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        TextView list = (TextView)findViewById(R.id.list);
        list.setText(dbHelper.displayUsernames(db));
    }
}