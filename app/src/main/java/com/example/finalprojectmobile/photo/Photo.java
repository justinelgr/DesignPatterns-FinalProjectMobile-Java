package com.example.finalprojectmobile.photo;

import android.graphics.Bitmap;
import android.net.Uri;

import com.example.finalprojectmobile.user.User;

import java.util.List;

public interface Photo {
    void postNew(User author, Bitmap bitmap, String description, String[] hashtags);
    void changeDescription(String newDescription);
    void changeHashtags(String[] newHasthags);
    User getAuthor();
    String getDescription();
    String getBitmap();
    String[] getHashtags();
    String getType();
}
