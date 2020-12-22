package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalprojectmobile.user.User;

public class ChoosePhotoActivity extends AppCompatActivity {

    String folder;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo);

        user = (User)getIntent().getSerializableExtra("User");
    }

    public void onClickBeach(View view){
        folder = "/beach.jpg/";
        Intent intent = new Intent(this, NewPhotoActivity.class);
        intent.putExtra("folder", folder);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void onClickBridge(View view){
        folder = "/bridge.jpg";
        Intent intent = new Intent(this, NewPhotoActivity.class);
        intent.putExtra("folder", folder);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void onClickCat(View view){
        folder = "/cat.jpg";
        Intent intent = new Intent(this, NewPhotoActivity.class);
        intent.putExtra("folder", folder);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void onClickChildren(View view){
        folder = "/children.jpg";
        Intent intent = new Intent(this, NewPhotoActivity.class);
        intent.putExtra("folder", folder);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void onClickDog(View view){
        folder = "/dog.jpg";
        Intent intent = new Intent(this, NewPhotoActivity.class);
        intent.putExtra("folder", folder);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void onClickFootball(View view){
        folder = "/football.jpg";
        Intent intent = new Intent(this, NewPhotoActivity.class);
        intent.putExtra("folder", folder);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void onClickForest(View view){
        folder = "/forest.jpg";
        Intent intent = new Intent(this, NewPhotoActivity.class);
        intent.putExtra("folder", folder);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void onClickGirl(View view){
        folder = "/girl.jpg";
        Intent intent = new Intent(this, NewPhotoActivity.class);
        intent.putExtra("folder", folder);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void onClickGirl2(View view){
        folder = "/girl2.jpg";
        Intent intent = new Intent(this, NewPhotoActivity.class);
        intent.putExtra("folder", folder);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void onClickHorse(View view){
        folder = "/horse.jpg";
        Intent intent = new Intent(this, NewPhotoActivity.class);
        intent.putExtra("folder", folder);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void onClickMountain(View view){
        folder = "/mountain.png";
        Intent intent = new Intent(this, NewPhotoActivity.class);
        intent.putExtra("folder", folder);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void onClickSubway(View view){
        folder = "/subway.jpg";
        Intent intent = new Intent(this, NewPhotoActivity.class);
        intent.putExtra("folder", folder);
        intent.putExtra("User", user);
        startActivity(intent);
    }
}