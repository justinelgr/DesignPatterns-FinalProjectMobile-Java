package com.example.finalprojectmobile.photo;

import android.database.sqlite.SQLiteDatabase;

import com.example.finalprojectmobile.database.DbContract;
import com.example.finalprojectmobile.database.DbHelper;

public class PhotoFacade {

    public boolean searchHashtag(Photo photo, String hashtag){
        for(int j = 0; j < photo.getHashtags().length; j++){
            if(photo.getHashtags()[j].equals(hashtag)){
                return true;
            }
        }
        return false;
    }
}
