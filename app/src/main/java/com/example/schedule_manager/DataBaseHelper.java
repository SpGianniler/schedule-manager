package com.example.schedule_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The class that connects the app with the database
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION =1;

    public static final String DATABASE_NAME = "company.db";
    public static final String EMPLOYEES_TABLE = "EMPLOYEES_TABLE";
    public static final String COLUMN_EID = "eid";
    public static final String COLUMN_EIDfk = COLUMN_EID;
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_FIRST_NAME = "first_" + COLUMN_NAME;
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_IS_ADMIN = "is_admin";
    public static final String COLUMN_JID = "jid";
    public static final String COLUMN_JIDfk = COLUMN_JID;
    public static final String COLUMN_LAST_NAME = "last_" + COLUMN_NAME;
    public static final String JOBS_TABLE = "JOBS";
    public static final String CONTRACTS_TABLE = "CONTRACTS";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_END_DATE = "end_date";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_WORK_HOURS = "work_hours";
    public static final String COLUMN_LEAVES_ALLOWED = "leaves_allowed";
    public static final String SHIFTS_TABLE = "SHIFTS";
    public static final String COLUMN_SID = "sid";
    public static final String COLUMN_SHIFT_NAME = "shift_name";
    public static final String COLUMN_EMPLOYEES_NEEDED = "employees_needed";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";


    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * onCreate is called the first time a database is accessed.
     * <p>
     *     This is where the code that creates the db is contained
     *
     * @param db the database that will be created
     */
    @Override
    public void onCreate(SQLiteDatabase db){
        String createTableStatement = "CREATE TABLE " + JOBS_TABLE + "(" +
                COLUMN_JID + " INTEGER CONSTRAINT pk_job PRIMARY KEY AUTOINCREMENT NOT NULL," +
                COLUMN_NAME + " VARCHAR (40) NOT NULL)";
        db.execSQL(createTableStatement);

        String createTableStatement1= "CREATE TABLE " + EMPLOYEES_TABLE + "(" +
                COLUMN_EID + " INTEGER CONSTRAINT pk_em PRIMARY KEY AUTOINCREMENT NOT NULL," +
                COLUMN_FIRST_NAME + " VARCHAR (60) NOT NULL," +
                COLUMN_LAST_NAME + "  VARCHAR (80) NOT NULL," +
                COLUMN_DOB + " DATE DEFAULT ('1900/1/30')," +
                COLUMN_IS_ADMIN + " BOOLEAN NOT NULL DEFAULT (0)," +
                COLUMN_USERNAME + " VARCHAR (25) NOT NULL," +
                COLUMN_PASSWORD + " VARCHAR(18) NOT NULL," +
                COLUMN_JIDfk + " CONSTRAINT fk_em_jo REFERENCES " + JOBS_TABLE + " (" + COLUMN_JID + "))";
        db.execSQL(createTableStatement1);

        String createTableStatement2 = "CREATE TABLE " + CONTRACTS_TABLE + "(" +
                COLUMN_EIDfk + " INTEGER CONSTRAINT fk_con_em REFERENCES " + EMPLOYEES_TABLE + " (eid) NOT NULL," +
                COLUMN_START_DATE + " DATE DEFAULT ('1900/1/30') NOT NULL," +
                COLUMN_END_DATE + " DATE DEFAULT ('1900/1/30') NOT NULL," +
                COLUMN_TYPE + " VARCHAR (25) NOT NULL DEFAULT  '[Part time]'," +
                COLUMN_WORK_HOURS + " INTEGER," +
                COLUMN_LEAVES_ALLOWED + " INTEGER," +
                "CONSTRAINT pk_con PRIMARY KEY (" + COLUMN_EIDfk + "," + COLUMN_START_DATE + "))";
        db.execSQL(createTableStatement2);

        String createTableStatement3 = "CREATE TABLE " + SHIFTS_TABLE + "(" +
                COLUMN_SID + " INTEGER CONSTRAINT pk_shi PRIMARY KEY AUTOINCREMENT NOT NULL," +
                COLUMN_SHIFT_NAME + " VARCHAR (40) NOT NULL," +
                COLUMN_EMPLOYEES_NEEDED + " INTEGER NOT NULL DEFAULT (1))";
        db.execSQL(createTableStatement3);
    }

    /**
     * onUpgrade is a method called if there is a change in the db version number<br><br>
     * <p>
     *     It is used to prevent previous users' apps from breaking when there is a change in the db design.<br>
     *         It will be triggered automatically and modifies the schema of the database adding or removing whatever is needed.
     *
     * @param db the database you are using
     * @param oldVersion the old version number of the database
     * @param newVersion the new version number of the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){

    }

// TODO: 14/11/2020 modify to actual app parameters!
    /**
     * adds a record to the table EMPLOYEES_TABLE in the database
     *
     * @param ergazomenoi the ERGAZOMENOI object from which we take the information needed to add data in the table
     * @return false if it failed, true if it succeeded
     */
    public boolean addOne(Ergazomenoi ergazomenoi){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues(); //hashmap

        cv.put(COLUMN_FIRST_NAME, ergazomenoi.getOnoma());
        cv.put(COLUMN_LAST_NAME, ergazomenoi.getEpitheto());
        cv.put(COLUMN_JIDfk, ergazomenoi.getEidikotita());

        long insert = db.insert(EMPLOYEES_TABLE, null, cv);
        return insert != -1;
    }

    // TODO: 14/11/2020 set the query up with the correct parameters!
    public List<Ergazomenoi> getEveryone(){
        List<Ergazomenoi> returnList = new ArrayList<>();

        String queryString = "SELECT EMPLOYEES_TABLE.eid, first_name, last_name, dob, type, work_hours, is_admin"+
                " FROM EMPLOYEES_TABLE , CONTRACTS"  +
                " WHERE CONTRACTS.eid = EMPLOYEES_TABLE.eid";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            //loop through the cursor(result set) and create new employee objects then put them into the return list.
            do{
                int eid = cursor.getInt(0);
                String first_name = cursor.getString(1);
                String last_name = cursor.getString(2);
                String dob = cursor.getString(3);
                String contract = cursor.getString(4);
                int whours = cursor.getInt(5);
                boolean is_admin = cursor.getInt(6) == 1;

                Ergazomenoi ergazomenoi = new Ergazomenoi(eid,first_name,last_name,dob,whours,contract,is_admin);

                returnList.add(ergazomenoi);

            } while (cursor.moveToNext());
        }
//        else{
//            //failure.do not add anything to the list
//        }

        //close both the cursor and the db when done
        cursor.close();
        db.close();
        return returnList;
    }
}
