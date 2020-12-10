package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.finalprojectmobile.database.UserDbHelper;
import com.example.finalprojectmobile.user.AdministratorUser;
import com.example.finalprojectmobile.user.User;
import com.google.android.material.snackbar.Snackbar;

public class AdministratorLoginActivity extends AppCompatActivity {

    User user;
    UserDbHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_login);

        dbHelper = new UserDbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
    }

    public void goToLoggedInActivity(View view){
        Snackbar wrongLogin = Snackbar.make(view, "Username or password is incorrect please try again", Snackbar.LENGTH_LONG);

        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        String usernameStr = username.getText().toString();
        String passwordStr = password.getText().toString();

        user = dbHelper.findUser(db, usernameStr);
        String dataUsername = user.getUsername();
        String dataPassword = user.getPassword();

        if(dataUsername == "USER NOT FOUND"){ //----- Incorrect username
            wrongLogin.show();
        } else { //---------------------------------- Correct username
            if(!dataPassword.equals(passwordStr)){ //------ Correct username but incorrect password
                wrongLogin.show();
            } else{ //------------------------------- Correct username and correct password
                AdministratorUser au = new AdministratorUser(user);
                au.decorate();
                user = au.user;
                Intent intent = new Intent(this, LoggedInActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        }
    }

    public void goToAdministratorRegisterActivity(View view){
        Intent intent = new Intent(this, AdministratorRegisterActivity.class);
        startActivity(intent);
    }
}