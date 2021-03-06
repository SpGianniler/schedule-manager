package com.example.schedule_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.HashMap;
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
    DataBaseAccess(Context context){
        this.openHelper = new DataBaseOpenHelper(context);
    }

    public DataBaseAccess() {
        return;
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

    public List<Ergazomenoi> getEveryone(){
        openDB();
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
        closeDB();
        return returnList;
    }

    public List<Credentials> getCredentials(){
        openDB();
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
        closeDB();
        return returnList;
    }
  
    public List<Vardies> getVardies(){
        openDB();
        c = db.rawQuery("Select SHIFTS.sid, name, employees_needed from  SHIFTS, JOBS, SHIFTS_JOBS where SHIFTS.sid = SHIFTS_JOBS.sid and JOBS.jid=SHIFTS_JOBS.jid", null);
        List<Vardies> returnList = new ArrayList<>();
        while(c.moveToNext()){
            String vardiat = c.getString(0);
            String eidikotita = c.getString(1);
            int employeesNo = c.getInt(2);

            Vardies vardia = new Vardies(vardiat, eidikotita, employeesNo);
            returnList.add(vardia);

        }
        closeDB();
        return returnList;
    }

    public List<String> getArgeies(){
        openDB();
        c = db.rawQuery("Select * from Argies", null);
        List<String> returnList = new ArrayList<>();
        while(c.moveToNext()){
            String argeia = c.getString(0);
            returnList.add(argeia);

        }
        closeDB();
        return returnList;
    }

    public HashMap<String, String> getShifts(){
        openDB();
        c = db.rawQuery("Select * from  SHIFTS", null);
        HashMap<String, String> shiftMap = new HashMap<>();
        while(c.moveToNext()){
            String sid = c.getString(0);
            String onoma = c.getString(1);

            shiftMap.put(sid,onoma);
        }
        closeDB();
        return shiftMap;

    }
    public HashMap<Integer, String> getEidikotites(){
        openDB();
        c = db.rawQuery("Select * from  JOBS", null);
        HashMap<Integer, String> shiftMap = new HashMap<>();
        while(c.moveToNext()){
            int jid = c.getInt(0);
            String onoma = c.getString(1);

            shiftMap.put(jid,onoma);
        }
        closeDB();
        return shiftMap;
    }
    public void insertProgram(String Date, String vardia, int eid){
        openDB();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DataBaseOpenHelper.Date, Date);
        contentValue.put(DataBaseOpenHelper.Vardia, vardia);
        contentValue.put(DataBaseOpenHelper.eid, eid);
        db.insert(DataBaseOpenHelper.TABLE_NAME, null, contentValue);
        closeDB();
    }
    public ArrayList<Schedule> getDBSchedule(){
        openDB();
        ArrayList <Schedule> programma = new ArrayList<>();
        c = db.rawQuery("Select * from  Schedule", null);
        while(c.moveToNext()){
            String Date = c.getString(0);
            String vardia = c.getString(1);
            int eid = c.getInt(2);

            for(Ergazomenoi erg : MainActivity.getErgazomenoiArrayList()){
                if(erg.getErg_id() == eid){
                    Schedule sch = new Schedule(Date,vardia , erg.getOnoma(), erg.getEpitheto(), erg.eidikotita);
                    programma.add(sch);
                }
            }
        }
        closeDB();
        return programma;
    }
    public void insertAdeia(String Date, int Duration, String Reason, int eid){
        openDB();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DataBaseOpenHelper.LeaveDate, Date);
        contentValue.put(DataBaseOpenHelper.LeaveDuration, Duration);
        contentValue.put(DataBaseOpenHelper.eid, eid);
        contentValue.put(DataBaseOpenHelper.LeaveReason, Reason);
        db.insert(DataBaseOpenHelper.LEAVE_TABLE, null, contentValue);
        closeDB();
    }

    public ArrayList<Adeies> getAdeies(){
        openDB();
        ArrayList<Adeies> adeies= new ArrayList<>();
        c = db.rawQuery("Select * from  Leaves", null);
        while(c.moveToNext()){
            String Date = c.getString(0);
            int eid = c.getInt(1);
            int Duration = c.getInt(2);
            String Reason = c.getString(3);
            adeies.add(new Adeies(Date, eid, Duration, Reason));
        }
        closeDB();
        return adeies;
    }
}
