package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.finalprojectmobile.database.UserDbHelper;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goToLoggedInActivity(View view){
        UserDbHelper dbHelper = new UserDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Snackbar wrongLogin = Snackbar.make(view, "Username or password is incorrect please try again", Snackbar.LENGTH_LONG);

        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        String usernameStr = username.getText().toString();
        String passwordStr = password.getText().toString();

        User user = dbHelper.findUser(db, usernameStr);
        String dataUsername = user.getUsername();
        String dataPassword = user.getPassword();

        if(dataUsername == "USER NOT FOUND"){ //----- Incorrect username
            wrongLogin.show();
        } else { //---------------------------------- Correct username
            if(!dataPassword.equals(passwordStr)){ //------ Correct username but incorrect password
                wrongLogin.show();
            } else{ //------------------------------- Correct username and correct password
                Intent intent = new Intent(this, LoggedInActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        }
    }

    public void goToRegisterActivity(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}