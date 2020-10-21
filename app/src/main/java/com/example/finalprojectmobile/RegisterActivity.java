package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.finalprojectmobile.database.UserContract;
import com.example.finalprojectmobile.database.UserDbHelper;

import org.w3c.dom.Text;

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
        //dbHelper.onUpgrade(db, 0, 0); // When I want to clean the database
        User user = User.getInstance();

        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        EditText email = (EditText)findViewById(R.id.email);
        RadioGroup typeButton = (RadioGroup)findViewById(R.id.type);

        user.setUsername(username.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        int selectedType = typeButton.getCheckedRadioButtonId();
        user.setType(selectedType);

        dbHelper.addUser(db, user);

        Intent intent = new Intent(this, NewUserActivity.class);
        startActivity(intent);
    }
}