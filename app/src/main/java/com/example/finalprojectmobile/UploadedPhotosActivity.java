package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.finalprojectmobile.database.DbHelper;
import com.example.finalprojectmobile.user.User;


import static com.example.finalprojectmobile.database.DbHelper.PHOTO_SELECT_ALL;

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

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

        user = (User) getIntent().getSerializableExtra("User");

        Cursor cursor = db.rawQuery(PHOTO_SELECT_ALL, null);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            TextView author = new TextView(UploadedPhotosActivity.this);
            ImageView photo = new ImageView(UploadedPhotosActivity.this);
            TextView description = new TextView(UploadedPhotosActivity.this);
            TextView hashtags = new TextView(UploadedPhotosActivity.this);

            linearLayout.addView(author);
            linearLayout.addView(photo);
            linearLayout.addView(description);
            linearLayout.addView(hashtags);

            author.setTextSize(20);
            author.setPadding(0,10,0,0);
            description.setTextSize(20);
            hashtags.setTextSize(20);

            author.setText(cursor.getString(1));
            byte[] image = cursor.getBlob(2);
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0 , image.length);
            photo.setImageBitmap(bm);
            description.setText(cursor.getString(3));
            hashtags.setText(cursor.getString(4));

            cursor.moveToNext();
        }

        /*
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

            linearLayout.addView(author);
            linearLayout.addView(photo);
            linearLayout.addView(description);
            linearLayout.addView(hashtags);

            author.setText(photoDetails[0]);
            Bitmap bm = BitmapFactory.decodeByteArray(photoDetails[1], 0 , photoDetails[1].length);
        }*/
    }
}