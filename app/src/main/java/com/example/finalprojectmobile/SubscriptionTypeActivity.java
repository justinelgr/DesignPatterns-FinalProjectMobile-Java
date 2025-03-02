package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.finalprojectmobile.database.DbHelper;
import com.example.finalprojectmobile.user.User;
import com.google.android.material.snackbar.Snackbar;

public class SubscriptionTypeActivity extends AppCompatActivity {

    User user;
    DbHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_type);

        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        user = (User)getIntent().getSerializableExtra("User");
        TextView username = (TextView)findViewById(R.id.username);
        TextView subscription = (TextView)findViewById(R.id.currentSub);

        username.setText(user.getUsername());
        subscription.setText(user.getType());
    }

    public void changeSubscription(View view){
        RadioGroup typeButton = (RadioGroup)findViewById(R.id.subscription);
        int selectedTypeID = typeButton.getCheckedRadioButtonId();
        RadioButton selectedType = (RadioButton)findViewById(selectedTypeID);

        if(!selectedType.isChecked()){ //------------------------------- No type
            Snackbar wrongType = Snackbar.make(view,
                    "You must select a type of subscription",
                    Snackbar.LENGTH_LONG);
            wrongType.show();
        } else { //------------------------------------------------------ Type
            user.setType((String)selectedType.getText());
        }

        dbHelper.changeType(db, user);

        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }
}