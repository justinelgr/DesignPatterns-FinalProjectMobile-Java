package com.example.finalprojectmobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.finalprojectmobile.database.DbHelper;
import com.example.finalprojectmobile.photo.Photo;
import com.example.finalprojectmobile.photo.PhotoFactory;
import com.example.finalprojectmobile.user.User;
import com.google.android.material.snackbar.Snackbar;

public class NewPhotoActivity extends AppCompatActivity{

    private static final int RESULT_LOAD_IMAGE = 1;

    ImageView image;
    Bitmap imageBitmap;
    Uri selectedImage;
    Button uploadButton;
    EditText description, hashtags;
    User user;
    TextView username;
    DbHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_photo);

        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

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
            selectedImage = data.getData();
            image.setImageURI(selectedImage);
            imageBitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
            int id = imageBitmap.getGenerationId();
            System.out.println(imageBitmap);
            System.out.println(id);
        }
    }

    public void goBackToHome(View view){
        Intent intent = new Intent(this, LoggedInActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void uploadPhoto(View view){
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

            String bitmapStr = imageBitmap.toString();
            System.out.println(bitmapStr);

            PhotoFactory photoFactory = new PhotoFactory();
            Photo photo = photoFactory.getPhoto(type);
            photo.postNew(user, imageBitmap, descriptionStr, listHashtags);

            dbHelper.addPhoto(db, photo);

            Intent intent = new Intent(this, LoggedInActivity.class);
            intent.putExtra("User", user);
            startActivity(intent);
        }
    }
}