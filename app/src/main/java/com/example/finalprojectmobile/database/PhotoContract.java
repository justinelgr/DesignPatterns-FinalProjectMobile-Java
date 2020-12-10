package com.example.finalprojectmobile.database;

import android.provider.BaseColumns;

public class PhotoContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private PhotoContract() {}

    /* Inner class that defines the table contents */
    public static class PhotoEntry implements BaseColumns {
        public static final String TABLE_NAME = "photo";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_URI = "uri";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_HASHTAGS = "hashtags";
        public static final String COLUMN_NAME_TYPE = "type";
    }
}
