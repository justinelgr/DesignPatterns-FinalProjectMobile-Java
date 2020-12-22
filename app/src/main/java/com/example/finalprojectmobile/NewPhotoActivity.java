package com.example.finalprojectmobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectmobile.database.DbHelper;
import com.example.finalprojectmobile.photo.Photo;
import com.example.finalprojectmobile.photo.PhotoFactory;
import com.example.finalprojectmobile.user.User;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class NewPhotoActivity extends AppCompatActivity{

    private static final int RESULT_LOAD_IMAGE = 1;
    private int STORAGE_PERMISSION_CODE = 23;

    ImageView image;
    Bitmap imageBitmap;
    byte[] imageByte;
    Button uploadButton;
    EditText description, hashtags;
    User user;
    TextView username;
    DbHelper dbHelper;
    SQLiteDatabase db;

    String folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_photo);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);

        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        user = (User)getIntent().getSerializableExtra("User");
        username = (TextView)findViewById(R.id.username);
        username.setText(user.getUsername());

        image = (ImageView)findViewById(R.id.uploadImage);
        uploadButton = (Button)findViewById(R.id.uploadButton);
        description = (EditText)findViewById(R.id.description);
        hashtags = (EditText)findViewById(R.id.hashtags);

        folder = (String)getIntent().getSerializableExtra("folder");
        if(folder != null){
            TextView displayImage = (TextView)findViewById(R.id.displayImage);
            displayImage.setText("Display image");
        }
    }

    public  void chooseImage(View view){
        Intent intent = new Intent(this, ChoosePhotoActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void displayImage(View view) throws IOException{
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + folder);
        FileInputStream fis = new FileInputStream(file);
        imageByte = new byte[fis.available()];
        fis.read(imageByte);
        imageBitmap = BitmapFactory.decodeByteArray(imageByte, 0 , imageByte.length);
        image.setImageBitmap(imageBitmap);
    }

    public void goBackToHome(View view){
        Intent intent = new Intent(this, LoggedInActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void uploadPhoto(View view) throws IOException{
        RadioGroup typeButton = (RadioGroup)findViewById(R.id.saveAs);
        int selectedTypeID = typeButton.getCheckedRadioButtonId();
        RadioButton selectedType = (RadioButton)findViewById(selectedTypeID);

        if(!selectedType.isChecked()){ //------------------------------- No type
            Snackbar wrongType = Snackbar.make(view,
                    "You must select how you want to save your photo",
                    Snackbar.LENGTH_LONG);
            wrongType.show();
        } else { //------------------------------------------------------ Type
            String type = (String)selectedType.getText();
            description = (EditText)findViewById(R.id.description);
            hashtags = (EditText)findViewById(R.id.hashtags);

            String descriptionStr = (String)description.getText().toString();
            String hashtagsStr = (String)hashtags.getText().toString();

            String listHashtags[] = hashtagsStr.split("#");

            PhotoFactory photoFactory = new PhotoFactory();
            Photo photo = photoFactory.getPhoto(type);
            photo.postNew(user, imageByte, descriptionStr, listHashtags);

            dbHelper.addPhoto(db, photo);

            Intent intent = new Intent(this, LoggedInActivity.class);
            intent.putExtra("User", user);
            startActivity(intent);
        }
    }
}