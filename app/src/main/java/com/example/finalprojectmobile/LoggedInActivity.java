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
        TextView username = (TextView)findViewById(R.id.loggedUser);
        TextView title = (TextView)findViewById(R.id.welcome);
        TextView profileButton = (TextView)findViewById(R.id.profile);
        if(user.getUsername().equals("AnonymousUser")){
            title.setText(R.string.welcomeAnonymous);
            profileButton.setText(R.string.registerButton);
        } else{
            title.setText(R.string.welcomeBack);
            username.setText(user.getUsername());
            profileButton.setText(R.string.profile);
        }
    }

    public void goToListUsersActivity(View view){
        User user = (User)getIntent().getSerializableExtra("User");
        Intent intent = new Intent(this, ListUsersActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void goToProfileActivity(View view){
        User user = (User)getIntent().getSerializableExtra("User");
        if(user.getUsername().equals("AnonymousUser")){
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        } else{
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("User", user);
            startActivity(intent);
        }
    }

    public void goToUploadActivity(View view){
        User user = (User)getIntent().getSerializableExtra("User");
        Intent intent = new Intent(this, NewPhotoActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }
}