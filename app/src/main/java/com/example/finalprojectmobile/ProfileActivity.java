package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.finalprojectmobile.user.User;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = (User)getIntent().getSerializableExtra("User");
        TextView username = (TextView)findViewById(R.id.loggedUser);
        TextView type = (TextView)findViewById(R.id.subscriptionType);
        TextView isAdministrator = (TextView)findViewById(R.id.isAdministrator);

        username.setText(user.getUsername());
        type.setText(user.getType());
        if(user.getAdministrator() == 0){isAdministrator.setText(R.string.isUser);}
        else{isAdministrator.setText(R.string.isAdministrator);}
    }

    public void goToSubscriptionTypeActivity(View view){
        Intent intent = new Intent(this, SubscriptionTypeActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void goBackToHome(View view){
        Intent intent = new Intent(this, LoggedInActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }
}