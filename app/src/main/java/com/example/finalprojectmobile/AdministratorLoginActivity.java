package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdministratorLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_login);
    }

    public void goToAdministratorRegisterActivity(View view){
        Intent intent = new Intent(this, AdministratorRegisterActivity.class);
        startActivity(intent);
    }
}