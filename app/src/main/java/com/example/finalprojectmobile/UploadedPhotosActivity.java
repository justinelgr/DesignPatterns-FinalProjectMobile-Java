package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.finalprojectmobile.database.DbHelper;
import com.example.finalprojectmobile.photo.Photo;
import com.example.finalprojectmobile.photo.PhotoFacade;
import com.example.finalprojectmobile.photo.PhotoFactory;
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
            author.setTextAppearance(getApplicationContext(), R.style.boldText);
            description.setTextSize(20);
            hashtags.setTextSize(20);
            hashtags.setPadding(0, 0, 0, 10);
            hashtags.setTextAppearance(getApplicationContext(), R.style.italicText);

            author.setText(cursor.getString(1));
            byte[] image = cursor.getBlob(2);
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0 , image.length);
            photo.setImageBitmap(bm);
            description.setText(cursor.getString(3));
            hashtags.setText(cursor.getString(4));

            if(cursor.getString(1).equals(user.getUsername())){
                Button addFilterButton = new Button(UploadedPhotosActivity.this);
                linearLayout.addView(addFilterButton);
                addFilterButton.setTextSize(20);
                addFilterButton.setText("Add filter");

                PhotoFactory photoFactory = new PhotoFactory();
                final Photo photoFiltered = photoFactory.getPhoto(cursor.getString(5));
                User userPhoto = dbHelper.findUser(db, cursor.getString(1));
                String[] listHashtags = cursor.getString(4).split("#");
                photoFiltered.postNew(userPhoto, image, cursor.getString(3), listHashtags);
                photoFiltered.addFilter();
                final String descriptionStr = cursor.getString(3);
                final Intent intent = new Intent(this, UploadedPhotosActivity.class);

                addFilterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbHelper.updatePhoto(db, descriptionStr, photoFiltered);
                        intent.putExtra("User", user);
                        startActivity(intent);
                    }
                });
            }
            cursor.moveToNext();
        }
    }

    public void goToSearchHashtagActivity(View view){
        EditText hashtagText = (EditText) findViewById(R.id.searchHashtag);
        String hashtag = hashtagText.getText().toString();

        Intent intent = new Intent(this, SearchHashtagActivity.class);
        intent.putExtra("Hashtag", hashtag);
        intent.putExtra("User", user);
        startActivity(intent);
    }
}