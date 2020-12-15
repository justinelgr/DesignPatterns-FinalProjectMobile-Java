package com.example.finalprojectmobile.photo;

import android.graphics.Bitmap;
import android.net.Uri;

import com.example.finalprojectmobile.user.User;

public class JPEG implements Photo {

    public User author;
    public Bitmap bitmap;
    public String description;
    public String[] hashtags;

    public void postNew(User author, Bitmap bitmap, String description, String[] hashtags){
        this.author = author;
        this.bitmap = bitmap;
        this.description = description;
        this.hashtags = hashtags;

    }

    public void changeDescription(String newDescription){
        this.description = newDescription;
    }

    public void changeHashtags(String[] newHashtags){
        this.hashtags = newHashtags;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getBitmap() {
        return bitmap.toString();
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getHashtags() {
        return hashtags;
    }

    public void setHashtags(String[] hashtags) {
        this.hashtags = hashtags;
    }

    public String getType(){
        return "JPEG";
    }
}
