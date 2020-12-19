package com.example.schedule_manager;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataBaseOpenHelper extends SQLiteAssetHelper {
    public static final String TABLE_NAME = "Schedule";

    // Table columns
    public static final String Date= "Date";
    public static final String Vardia = "Vardia";
    public static final String eid = "eid";

    private static final String DB_NAME = "company.db";
    private static final int DB_VERSION = 1;

    //constructor
    public DataBaseOpenHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
}
