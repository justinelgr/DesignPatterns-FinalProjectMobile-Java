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

    /*public void addUser(View view){
        UserDbHelper dbHelper = new UserDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        EditText email = (EditText)findViewById(R.id.email);
        RadioGroup typeButton = (RadioGroup)findViewById(R.id.type);

        String userUsername = username.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        int selectedType = typeButton.getCheckedRadioButtonId();
        RadioButton type = (RadioButton)findViewById(selectedType);
        String userType = type.getText().toString();

        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.COLUMN_NAME_USERNAME, userUsername);
        values.put(UserContract.UserEntry.COLUMN_NAME_PASSWORD, userPassword);
        values.put(UserContract.UserEntry.COLUMN_NAME_EMAIL, userEmail);
        values.put(UserContract.UserEntry.COLUMN_NAME_TYPE, userType);

        //long newRowId = db.insert(UserContract.UserEntry.TABLE_NAME, null, values);

        Intent intent = new Intent(this, NewUserActivity.class);
        //intent.putExtra(MESSAGE_KEY, userUsername);
        startActivity(intent);
    }*/

    public void goToNewUserActivity(View view){
        Intent intent = new Intent(this, NewUserActivity.class);
        startActivity(intent);
    }
}