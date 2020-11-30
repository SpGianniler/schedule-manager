package com.example.schedule_manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

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
     * a method to query and return the result from database<br><br>
     *<p>
     * will query for date of birth by passing first_name<br><br>
     *
     * @param name name we want to search for
     * @return date of birth of the name queried
     */
    public String getDoB(String name){
        c = db.rawQuery("SELECT dob FROM EMPLOYEES where first_name='"+name+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String dob = c.getString(0);
            buffer.append("").append(dob);
        }
        return buffer.toString();
    }

    public List<Ergazomenoi> getEveryone(){//ToDo: Na allaksei gia na pairnei dedomena apo tous swstous pinakes
        c = db.rawQuery("SELECT distinct EMPLOYEES.eid, first_name, last_name, type, work_hours, EMPLOYEES.is_admin, JOBS.name FROM EMPLOYEES,JOBS , CONTRACTS , CREDENTIALS WHERE CONTRACTS.eid = EMPLOYEES.eid AND EMPLOYEES.eid = CONTRACTS.eid AND EMPLOYEES.jid = JOBS.jid", null);
        List<Ergazomenoi> returnList = new ArrayList<>();
        while(c.moveToNext()){
            int eid = c.getInt(0);
            String onoma = c.getString(1);
            String epitheto = c.getString(2);
            String type = c.getString(3);
            int evWres = c.getInt(4);
            boolean is_admin = c.getInt(5) == 1;
            String jid = c.getString(6);
            Ergazomenoi erg = new Ergazomenoi(eid, onoma, epitheto, jid,evWres,type, is_admin);
            returnList.add(erg);

        }
        return returnList;
    }

    public List<Credentials> getCredentials(){
        c = db.rawQuery("SELECT * FROM CREDENTIALS", null);
        List<Credentials> returnList = new ArrayList<>();
        while(c.moveToNext()){
            int eid = c.getInt(0);
            boolean is_admin = c.getInt(1) == 1;
            String username = c.getString(2);
            String password = c.getString(3);

            Credentials cred = new Credentials(eid, is_admin, username,password);
            returnList.add(cred);

        }
        return returnList;
    }
}
