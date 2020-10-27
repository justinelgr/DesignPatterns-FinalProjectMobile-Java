package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.finalprojectmobile.user.User;

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