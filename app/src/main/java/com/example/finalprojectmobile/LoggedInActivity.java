package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.finalprojectmobile.user.User;

public class LoggedInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        User user = (User)getIntent().getSerializableExtra("User");
        TextView tv = (TextView)findViewById(R.id.loggedUser);
        tv.setText(user.getUsername());
    }

    public void goToListUsersActivity(View view){
        User user = (User)getIntent().getSerializableExtra("User");
        Intent intent = new Intent(this, ListUsersActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }
}