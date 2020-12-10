package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.finalprojectmobile.user.User;

public class NewUserActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        TextView tv = (TextView)findViewById(R.id.newUser);
        user = (User)getIntent().getSerializableExtra("User");
        tv.setText(user.getUsername());
    }

    public void goToLoggedInActivity(View view){
        Intent intent = new Intent(this, LoggedInActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }
}