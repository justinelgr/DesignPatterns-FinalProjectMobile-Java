package com.example.finalprojectmobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.finalprojectmobile.database.DbHelper;
import com.example.finalprojectmobile.photo.Photo;
import com.example.finalprojectmobile.photo.PhotoFactory;
import com.example.finalprojectmobile.user.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UploadedPhotosActivity extends AppCompatActivity {

    DbHelper dbHelper;
    SQLiteDatabase db;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploaded_photos);

        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();


        LinearLayout parentLayout = (LinearLayout)findViewById(R.id.parent);

        user = (User) getIntent().getSerializableExtra("User");
        String listPhotos = dbHelper.displayPhotosHomePage(db);
        String[] listPhotosTab = listPhotos.split("&");
        for (int i = 0; i < listPhotosTab.length; i ++) {
            System.out.println(listPhotosTab[i]);
            String[] photoDetails = listPhotosTab[i].split("~");

            System.out.println(photoDetails[0]);
            System.out.println(photoDetails[1]);
            System.out.println(photoDetails[2]);
            System.out.println(photoDetails[3]);

            TextView author = new TextView(UploadedPhotosActivity.this);
            ImageView photo = new ImageView(UploadedPhotosActivity.this);
            TextView description = new TextView(UploadedPhotosActivity.this);
            TextView hashtags = new TextView(UploadedPhotosActivity.this);

            author.setText(photoDetails[0]);

            byte [] encodeByte = Base64.decode(photoDetails[1],Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            System.out.println(bitmap);
            photo.setImageBitmap(bitmap);
            description.setText(photoDetails[2]);
            hashtags.setText(photoDetails[3]);

            /*parentLayout.addView(author);
            parentLayout.addView(photo);
            parentLayout.addView(description);
            parentLayout.addView(hashtags);*/

            /*String authorStr = listPhotosTab[i];
            String uriStr = listPhotosTab[i + 1];
            String descriptionStr = listPhotosTab[i + 2];
            String hashtagsStr = "#" + listPhotosTab[i + 3];
            Uri uri = Uri.parse(uriStr);

            TextView author = new TextView(UploadedPhotosActivity.this);
            author.setText(authorStr);
            parentLayout.addView(author);

            ImageView photo = new ImageView(UploadedPhotosActivity.this);
            photo.setImageURI(uri);
            parentLayout.addView(photo);

            TextView description = new TextView(UploadedPhotosActivity.this);
            description.setText(descriptionStr);
            parentLayout.addView(description);

            TextView hashtags = new TextView(UploadedPhotosActivity.this);
            author.setText(hashtagsStr);
            parentLayout.addView(hashtags);*/
        }
    }
}