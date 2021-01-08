package com.example.schedule_manager;

import java.io.Serializable;

public class Vardies implements Serializable {
    protected String onoma;
    protected String eidikotita;
    protected int employeesNo;
    protected int sid; //new
    protected int jid; //new

    public Vardies(String onoma, String eidikotita, int employeesNo) {
        this.onoma = onoma;
        this.eidikotita = eidikotita;
        this.employeesNo = employeesNo;
    }

    public Vardies(String onoma, int employeesNo, int sid, int jid) {
        this.onoma = onoma;
        //this.eidikotita = eidikotita;
        this.employeesNo = employeesNo;
        this.sid = sid;
        this.jid = jid;
    }

    public Vardies(int sid, int jid,int employeesNo) {
        this.employeesNo = employeesNo;
        this.sid = sid;
        this.jid = jid;
    }

    public Vardies(){

    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getSid() {
        return sid;
    }

    public String getOnoma() {
        return onoma;
    }

    public String getEidikotita() {
        return eidikotita;
    }

    public int getEmployeesNo() {
        return employeesNo;
    }

    public void setOnoma(String onoma) {
        this.onoma = onoma;
    }

    public void setEidikotita(String eidikotita) {
        this.eidikotita = eidikotita;
    }

    public void setEmployeesNo(int employeesNo) {
        this.employeesNo = employeesNo;
    }
}
