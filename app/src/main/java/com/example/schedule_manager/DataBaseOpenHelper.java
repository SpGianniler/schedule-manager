package com.example.schedule_manager;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataBaseOpenHelper extends SQLiteAssetHelper {
    private static final String DB_NAME = "company.db";
    private static final int DB_VERSION = 1;

    //constructor
    public DataBaseOpenHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);


    }
}
