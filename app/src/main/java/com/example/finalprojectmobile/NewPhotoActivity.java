package com.example.finalprojectmobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalprojectmobile.user.User;

import org.w3c.dom.Text;

public class NewPhotoActivity extends AppCompatActivity{

    private static final int RESULT_LOAD_IMAGE = 1;

    ImageView image;
    Button uploadButton;
    EditText description, hashtags;
    User user;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_photo);

        user = (User)getIntent().getSerializableExtra("User");
        username = (TextView)findViewById(R.id.username);
        username.setText(user.getUsername());

        image = (ImageView)findViewById(R.id.uploadImage);
        uploadButton = (Button)findViewById(R.id.uploadButton);
        description = (EditText)findViewById(R.id.description);
        hashtags = (EditText)findViewById(R.id.hashtags);
    }

    public void onClickImage(View view){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            image.setImageURI(selectedImage);
        }
    }

    public void goBackToHome(View view){
        Intent intent = new Intent(this, LoggedInActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }
}