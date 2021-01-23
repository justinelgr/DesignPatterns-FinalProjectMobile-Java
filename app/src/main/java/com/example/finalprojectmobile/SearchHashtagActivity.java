package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.finalprojectmobile.database.DbContract;
import com.example.finalprojectmobile.database.DbHelper;
import com.example.finalprojectmobile.photo.Photo;
import com.example.finalprojectmobile.photo.PhotoFacade;
import com.example.finalprojectmobile.photo.PhotoFactory;
import com.example.finalprojectmobile.user.User;

public class SearchHashtagActivity extends AppCompatActivity {

    String hashtag;
    User user;
    DbHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hashtag);

        hashtag = (String) getIntent().getSerializableExtra("Hashtag");
        user = (User) getIntent().getSerializableExtra("User");

        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        Photo[] photos = dbHelper.getPhotos(db);
        PhotoFacade photoFacade = new PhotoFacade();

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

        for(int i = 0; i < photos.length; i++){
            if(photoFacade.searchHashtag(photos[i], hashtag)){
                TextView author = new TextView(SearchHashtagActivity.this);
                ImageView photo = new ImageView(SearchHashtagActivity.this);
                TextView description = new TextView(SearchHashtagActivity.this);
                TextView hashtags = new TextView(SearchHashtagActivity.this);

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

                author.setText(photos[i].getAuthor().getUsername());
                byte[] image = photos[i].getImage();
                Bitmap bm = BitmapFactory.decodeByteArray(image, 0 , image.length);
                photo.setImageBitmap(bm);
                description.setText(photos[i].getDescription());
                String hashtagsStr = "";
                String[] hashtag = photos[i].getHashtags();
                hashtagsStr += hashtag[0];
                for(int j = 1; j < hashtag.length; j++){
                    hashtagsStr += "#" + hashtag[j];
                }
                hashtags.setText(hashtagsStr);
            }
        }
    }
}