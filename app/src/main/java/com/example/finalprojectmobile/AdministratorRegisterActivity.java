package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.finalprojectmobile.database.DbHelper;
import com.example.finalprojectmobile.user.AdministratorUser;
import com.example.finalprojectmobile.user.User;
import com.google.android.material.snackbar.Snackbar;

public class AdministratorRegisterActivity extends AppCompatActivity {

    User user;
    DbHelper dbHelper;
    SQLiteDatabase db;
    AdministratorUser admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_register);

        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        user = User.getInstance();
        admin = new AdministratorUser(user);
        admin.decorate();
        user = admin.user;
    }

    public void goToNewUserActivity(View view) {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        EditText email = (EditText) findViewById(R.id.email);

        String usernameStr = username.getText().toString();
        String passwordStr = password.getText().toString();
        String emailStr = email.getText().toString();

        if (usernameStr.matches("")) { //--------------- No username
            Snackbar wrongUsername = Snackbar.make(view,
                    "Username cannot be empty",
                    Snackbar.LENGTH_LONG);
            wrongUsername.show();
        } else { //------------------------------------------ Username
            user.setUsername(username.getText().toString());
            if (passwordStr.matches("")) { //-------------------- No password
                Snackbar wrongPassword = Snackbar.make(view,
                        "Password cannot be empty",
                        Snackbar.LENGTH_LONG);
                wrongPassword.show();
            } else { //----------------------------------------------- Password
                user.setPassword(password.getText().toString());
                if (emailStr.matches("")) { //---------------------------- No email
                    Snackbar wrongEmail = Snackbar.make(view,
                            "Email cannot be empty",
                            Snackbar.LENGTH_LONG);
                    wrongEmail.show();
                } else { //---------------------------------------------------- Email
                    user.setEmail(email.getText().toString());
                    user.setType("free");
                    dbHelper.addUser(db, user);
                    Intent intent = new Intent(this, NewUserActivity.class);
                    intent.putExtra("User", user);
                    startActivity(intent);
                }
            }
        }
    }
}