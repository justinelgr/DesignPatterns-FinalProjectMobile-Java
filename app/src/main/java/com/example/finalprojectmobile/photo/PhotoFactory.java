package com.example.finalprojectmobile.photo;

public class PhotoFactory {

    public Photo getPhoto(String photoType){
        if(photoType.equalsIgnoreCase("PNG")){
            return new PNG();
        }
        else if(photoType.equalsIgnoreCase("JPEG")){
            return new JPEG();
        }
        else if(photoType.equalsIgnoreCase("BMP")){
            return new BMP();
        }
        return null;
    }
}
