package com.example.finalprojectmobile.photo;

import com.example.finalprojectmobile.user.User;

public class BMP implements Photo {

    public User author;
    public byte[] image;
    public String description;
    public String[] hashtags;

    public void postNew(User author, byte[] image, String description, String[] hashtags){
        this.author = author;
        this.image = image;
        this.description = description;
        this.hashtags = hashtags;

    }

    public Photo addFilter(){
        Photo photoFilter = this;
        photoFilter.setDescription(this.getDescription() + "\n[FILTERED]");
        return photoFilter;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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
        return "BMP";
    }
}
