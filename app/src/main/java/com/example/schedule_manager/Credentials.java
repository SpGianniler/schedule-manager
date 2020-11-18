package com.example.schedule_manager;

import android.content.Context;

import java.util.List;

public class Credentials {
    private int eid;
    private boolean admin;
    private String username;
    private String password;

    public Credentials(int eid, boolean admin, String username, String password) {
        this.eid = eid;
        this.admin = admin;
        this.username = username;
        this.password = password;
    }

    public int getEid() {
        return eid;
    }

    public boolean is_admin() {
        return admin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    static public boolean isValid(String username, String password, boolean is_Admin, Context context){

        DataBaseAccess dba;
        dba = DataBaseAccess.getInstance(context);
        dba.openDB();
        List<Credentials> listOfCreds = dba.getCredentials();

        for(Credentials cred : listOfCreds){
            if(cred.getUsername().equals(username) && cred.getPassword().equals(password) && cred.is_admin() == is_Admin){
                return true;
            }
        }

        dba.closeDB();
        return false;
    }
}
