package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    public final static String MESSAGE_KEY ="com.example.finaleprojectmobile.message_key";
    EditText username, password, email;
    RadioButton type;
    RadioGroup typeButton;
    int selectedType;
    MyDBHandler helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        email = (EditText)findViewById(R.id.email);
        typeButton = (RadioGroup)findViewById(R.id.type);
        helper = new MyDBHandler();
    }

    public void addUser(View view){
        String userUsername = username.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        selectedType = typeButton.getCheckedRadioButtonId();
        type = (RadioButton)findViewById(selectedType);
        String userType = type.getText().toString();
        long id = helper.insertData(userUsername, userPassword, userEmail, userType);
        Intent intent = new Intent(this, NewUserActivity.class);
        intent.putExtra(MESSAGE_KEY, userUsername);
        startActivity(intent);
    }
}