package com.example.schedule_manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataBaseAccess {
    private SQLiteAssetHelper openHelper;
    private SQLiteDatabase db;
    private static DataBaseAccess instance;
    Cursor c = null;

    /**
     * private constructor of the DataBaseAccess class so that object creation from outside the class is avoided
     *
     * @param context used to get the context of the class it is called from eg:MainActivity.this
     */
    private DataBaseAccess(Context context){
        this.openHelper = new DataBaseOpenHelper(context);
    }

    /**
     *returns the single instance of the class
     *
     * @param context used to get the context of the class it is called from eg:MainActivity.this
     * @return instance of the class
     */
    public static DataBaseAccess getInstance(Context context){
        if(instance==null){
            instance = new DataBaseAccess(context);
        }
        return instance;
    }

    /**
     * opens the database connection
     */
    public void openDB(){
        this.db = openHelper.getWritableDatabase();
    }

    /**
     * closes the database connection
     */
    public void closeDB(){
        if(db!=null){
            this.db.close();
        }
    }

    /**
     * a method to query and return the result from database
     *
     * will query for date of birth by passing first_name
     *
     * @param name name we want to search for
     * @return date of birth of the name queried
     */
    public String getDoB(String name){
        c = db.rawQuery("SELECT dob FROM EMPLOYEES where first_name='"+name+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String dob = c.getString(0);
            buffer.append(""+dob);
        }
        return buffer.toString();
    }

}
