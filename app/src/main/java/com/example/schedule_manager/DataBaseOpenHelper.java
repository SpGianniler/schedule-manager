package com.example.schedule_manager;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataBaseOpenHelper extends SQLiteAssetHelper {
    public static final String TABLE_NAME = "Schedule";
    public static final String LEAVE_TABLE = "Leaves";
    public static final String EMPLOYEES_TABLE = "EMPLOYEES";
    // Table columns
    public static final String Date= "Date";
    public static final String Vardia = "Vardia";
    public static final String eid = "eid";

    public static final String LeaveDate= "Date";
    public static final String LeaveDuration = "Duration";
    public static final String LeaveReason = "Reason";

    public static final String EmployeeFirstName= "first_name";
    public static final String EmployeeLastName = "last_name";

    private static final String DB_NAME = "company2.db";
    private static final int DB_VERSION = 1;

    //constructor
    public DataBaseOpenHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
}
