package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.finalprojectmobile.database.UserDbHelper;
import com.example.finalprojectmobile.user.User;
import com.google.android.material.snackbar.Snackbar;

import java.io.Console;

public class RegisterActivity extends AppCompatActivity {

    //public final static String MESSAGE_KEY ="com.example.finaleprojectmobile.message_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void goToNewUserActivity(View view){
        UserDbHelper dbHelper = new UserDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        User user = User.getInstance();
        user.setAdministrator(0);

        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        EditText email = (EditText)findViewById(R.id.email);
        RadioGroup typeButton = (RadioGroup)findViewById(R.id.type);
        int selectedTypeID = typeButton.getCheckedRadioButtonId();
        RadioButton selectedType = (RadioButton)findViewById(selectedTypeID);

        String usernameStr = username.getText().toString();
        String passwordStr = password.getText().toString();
        String emailStr = email.getText().toString();

        if(usernameStr.matches("")){ //--------------- No username
            Snackbar wrongUsername = Snackbar.make(view,
                    "Username cannot be empty",
                    Snackbar.LENGTH_LONG);
            wrongUsername.show();
        } else{ //------------------------------------------ Username
            user.setUsername(username.getText().toString());
            if(passwordStr.matches("")){ //-------------------- No password
                Snackbar wrongPassword = Snackbar.make(view,
                        "Password cannot be empty",
                        Snackbar.LENGTH_LONG);
                wrongPassword.show();
            } else{ //----------------------------------------------- Password
                user.setPassword(password.getText().toString());
                if(emailStr.matches("")){ //---------------------------- No email
                    Snackbar wrongEmail = Snackbar.make(view,
                            "Email cannot be empty",
                            Snackbar.LENGTH_LONG);
                    wrongEmail.show();
                } else{ //---------------------------------------------------- Email
                    user.setEmail(email.getText().toString());
                    if(!selectedType.isChecked()){ //------------------------------- No type
                        Snackbar wrongType = Snackbar.make(view,
                                "You must select a type of subscription",
                                Snackbar.LENGTH_LONG);
                        wrongType.show();
                    } else{ //------------------------------------------------------ Type
                        user.setType((String)selectedType.getText());
                        dbHelper.addUser(db, user);
                        Intent intent = new Intent(this, NewUserActivity.class);
                        intent.putExtra("User", user);
                        startActivity(intent);
                    }
                }
            }
        }
    }
}