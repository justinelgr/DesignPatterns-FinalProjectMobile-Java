package com.example.finalprojectmobile.database;

import android.provider.BaseColumns;

public final class DbContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DbContract() {}

    /* Inner class that defines the table contents */
    public static class DbEntry implements BaseColumns {
        public static final String TABLE_NAME_USER = "user";
        public static final String COLUMN_NAME_USERNAME_USER = "username";
        public static final String COLUMN_NAME_PASSWORD_USER = "password";
        public static final String COLUMN_NAME_EMAIL_USER = "email";
        public static final String COLUMN_NAME_TYPE_USER = "type";
        public static final String COLUMN_NAME_ADMINISTRATOR_USER = "administrator";

        public static final String TABLE_NAME_PHOTO = "photo";
        public static final String COLUMN_NAME_AUTHOR_PHOTO = "author";
        public static final String COLUMN_NAME_BYTE_PHOTO = "bitmap";
        public static final String COLUMN_NAME_DESCRIPTION_PHOTO = "description";
        public static final String COLUMN_NAME_HASHTAGS_PHOTO = "hashtags";
        public static final String COLUMN_NAME_TYPE_PHOTO = "type";
    }
}
