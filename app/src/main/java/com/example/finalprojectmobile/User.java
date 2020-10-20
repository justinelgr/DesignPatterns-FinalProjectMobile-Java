package com.example.finalprojectmobile;

public class User {

    enum UserType {free, premium, gold};

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
    public void setType(String type){
        UserType uType;
        if(type == "Free"){
            uType = UserType.free;
        } else if (type == "Premium ($4.99/month)"){
            uType = UserType.premium;
        } else{
            uType = UserType.gold;
        }
        this.type = uType;
    }

    User() {
        // constructor of the SingletonExample class
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

    public User createUser(User newUser, String email, String username, String password, UserType type){
        newUser.email = email;
        newUser.username = username;
        newUser.password = password;
        newUser.type = type;
        return newUser;
    }
}
