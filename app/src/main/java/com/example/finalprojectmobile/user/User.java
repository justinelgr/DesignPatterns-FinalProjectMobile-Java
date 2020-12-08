package com.example.finalprojectmobile.user;

import java.io.Serializable;

public class User implements Serializable {

    public enum UserType {FREE, PREMIUM, GOLD};

    // private field that refers to the object
    private static User singleUser;

    private String email;
    public String username;
    private String password;
    private UserType type;
    private int administrator;

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
    public void setType(String selectedType){
        if(selectedType.equals("Free")){
            this.type = UserType.FREE;
        } else if (selectedType.equals("Premium ($4.99/month)")){
            this.type = UserType.PREMIUM;
        } else{
            this.type = UserType.GOLD;
        }
    }
    public int getAdministrator(){
        return this.administrator;
    }
    public void setAdministrator(int administrator){
        this.administrator = administrator;
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

    void decorate(){
        administrator = 1;
    }
}
