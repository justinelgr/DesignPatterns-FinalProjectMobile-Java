package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.finalprojectmobile.user.User;
import com.google.android.material.snackbar.Snackbar;

public class LoggedInActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        user = (User)getIntent().getSerializableExtra("User");
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
        Intent intent = new Intent(this, ListUsersActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void goToProfileActivity(View view){
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
        if(user.getUsername().equals("AnonymousUser")){
            Snackbar anonymousUser = Snackbar.make(view, "Anonymous users cannot upload photos", Snackbar.LENGTH_LONG);
            anonymousUser.show();
        } else{
            Intent intent = new Intent(this, NewPhotoActivity.class);
            intent.putExtra("User", user);
            startActivity(intent);
        }
    }

    public void logOut(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToUploadedPhotosActivity(View view){
        Intent intent = new Intent(this, UploadedPhotosActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }
}