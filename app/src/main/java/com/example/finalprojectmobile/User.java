package com.example.finalprojectmobile;

import java.lang.reflect.Type;

public class User {

    public enum UserType {FREE, PREMIUM, GOLD};

    // private field that refers to the object
    private static User singleUser;

    private String email;
    public String username;
    private String password;
    private UserType type;

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getType(){
        return String.valueOf(this.type);
    }
    public void setType(int selectedType){
        UserType uType;
        if(selectedType == 2131230831){
            uType = UserType.FREE;
        } else if (selectedType == 2131230882){
            uType = UserType.PREMIUM;
        } else{
            uType = UserType.GOLD;
        }
        this.type = uType;
    }

    User() {
        // constructor of the User class
        email = new String();
        username = new String();
        password = new String();
    }

    public static User getInstance() {
        // write code that allows us to create only one object
        // access the object as per our need
        if(singleUser == null) {
            singleUser = new User();
        }

        // returns the singleton object
        return singleUser;
    }
}
