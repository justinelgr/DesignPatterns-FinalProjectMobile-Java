package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.finalprojectmobile.database.DbHelper;
import com.example.finalprojectmobile.user.AnonymousUser;
import com.example.finalprojectmobile.user.AnonymousUserAdapter;
import com.example.finalprojectmobile.user.User;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    User user;
    DbHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
    }

    public void goToLoggedInActivity(View view){
        Snackbar wrongLogin = Snackbar.make(view, "Username or password is incorrect please try again", Snackbar.LENGTH_LONG);
        Snackbar administrator = Snackbar.make(view, "Please log in on the administrator page", Snackbar.LENGTH_LONG);

        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        String usernameStr = username.getText().toString();
        String passwordStr = password.getText().toString();

        user = dbHelper.findUser(db, usernameStr);
        String dataUsername = user.getUsername();
        String dataPassword = user.getPassword();

        if(user.getAdministrator() != 0){ //--------------- Administrator
            administrator.show();
        } else{ //----------------------------------------- User
            if(dataUsername == "USER NOT FOUND"){ //------- Incorrect username
                wrongLogin.show();
            } else { //------------------------------------ Correct username
                if(!dataPassword.equals(passwordStr)){ //-- Incorrect password
                    wrongLogin.show();
                } else{ //--------------------------------- Correct password
                    Intent intent = new Intent(this, LoggedInActivity.class);
                    intent.putExtra("User", user);
                    startActivity(intent);
                }
            }
        }
    }

    public void goToRegisterActivity(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void goToLoggedInActivityAnonymous(View view){
        AnonymousUser au = new AnonymousUser();
        AnonymousUserAdapter aua = new AnonymousUserAdapter();
        aua.MakeUserAnonymous(au);
        user = aua.user;
        Intent intent = new Intent(this, LoggedInActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void goToAdministratorActivity(View view){
        Intent intent = new Intent(this, AdministratorLoginActivity.class);
        startActivity(intent);
    }
}