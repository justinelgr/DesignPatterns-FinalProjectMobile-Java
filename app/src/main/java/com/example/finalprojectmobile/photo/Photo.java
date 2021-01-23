package com.example.finalprojectmobile.photo;

import android.graphics.Bitmap;
import android.net.Uri;

import com.example.finalprojectmobile.user.User;

import java.util.List;

public interface Photo {
    void postNew(User author, byte[] image, String description, String[] hashtags);
    Photo addFilter();
    User getAuthor();
    String getDescription();
    void setDescription(String description);
    byte[] getImage();
    String[] getHashtags();
    String getType();
}
