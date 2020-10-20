package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NewUserActivity extends AppCompatActivity {

    public final static String MESSAGE_KEY ="com.example.finaleprojectmobile.message_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MESSAGE_KEY);
        TextView username = findViewById(R.id.username);
        username.setText(message);
        setContentView(username);
    }
}