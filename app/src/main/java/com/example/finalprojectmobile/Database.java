package com.example.finalprojectmobile;

public class Database {

    private String DATABASE_NAME = "instagramApp.db";
    private int DATABASE_VERSION = 1;

    private String USER_TABLE_NAME = "user";
    private String USER_KEY_ID = "id";
    private String USER_KEY_USERNAME = "username";
    private String USER_KEY_PASSWORD = "password";
    private String USER_KEY_EMAIL = "email";
    private String USER_KEY_TYPE = "type";

    private String USER_TABLE_CREATE = "CREATE TABLE " + USER_TABLE_NAME +
            "(" + USER_KEY_ID + "INTEGER PRIMARY KEY";

}
