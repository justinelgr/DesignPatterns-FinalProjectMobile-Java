package com.example.finalprojectmobile.photo;

import android.database.sqlite.SQLiteDatabase;

import com.example.finalprojectmobile.database.DbContract;
import com.example.finalprojectmobile.database.DbHelper;

public class PhotoFacade {

    public Photo[] searchHashtag(Photo[] photos, String hashtag){
            Photo[] result = new Photo[photos.length];
            int count = 0;
            for(int i = 0; i < photos.length; i ++){
                String[] hashtags = photos[i].getHashtags();
                for(int j = 0; j < hashtags.length; j++){
                    if(hashtags[j].equals(hashtag)){
                        result[count] = photos[i];
                        count ++;
                    }
                }
            }
            return result;
    }
}
