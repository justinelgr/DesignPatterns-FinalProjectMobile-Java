package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.finalprojectmobile.database.UserDbHelper;
import com.example.finalprojectmobile.user.User;

public class SubscriptionTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_type);

        User user = (User)getIntent().getSerializableExtra("User");
        TextView username = (TextView)findViewById(R.id.username);
        TextView subscription = (TextView)findViewById(R.id.currentSub);

        username.setText(user.getUsername());
        subscription.setText(user.getType());
    }

    public void changeSubscription(View view){
        UserDbHelper dbHelper = new UserDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        User user = (User)getIntent().getSerializableExtra("User");
    }
}