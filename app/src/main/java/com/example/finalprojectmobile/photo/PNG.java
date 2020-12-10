package com.example.finalprojectmobile.photo;

import android.net.Uri;

import com.example.finalprojectmobile.user.User;

import java.util.List;

public class PNG implements Photo {

    public User author;
    public Uri uri;
    public String description;
    public String[] hashtags;

    public void postNew(User author, Uri uri, String description, String[] hashtags){
        this.author = author;
        this.uri = uri;
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

    public String getUri() {
        return uri.toString();
    }

    public void setUri(Uri uri) {
        this.uri = uri;
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
        return "PNG";
    }
}
