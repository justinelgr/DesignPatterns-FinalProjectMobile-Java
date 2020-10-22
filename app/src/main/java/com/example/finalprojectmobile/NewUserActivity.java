package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finalprojectmobile.database.UserDbHelper;

import org.w3c.dom.Text;

public class NewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        TextView tv = (TextView)findViewById(R.id.newUser);
        User user = (User)getIntent().getSerializableExtra("User");
        tv.setText(user.getUsername());
    }
}