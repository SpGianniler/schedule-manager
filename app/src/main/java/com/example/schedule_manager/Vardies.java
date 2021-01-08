package com.example.schedule_manager;

import java.io.Serializable;

public class Vardies implements Serializable {
    protected String onoma;
    protected String eidikotita;
    protected int emploeesNo;
    protected int sid; //new
    protected int jid; //new

    public Vardies(String onoma, String eidikotita, int emploeesNo) {
        this.onoma = onoma;
        this.eidikotita = eidikotita;
        this.emploeesNo = emploeesNo;
    }

    public Vardies(String onoma, int emploeesNo, int sid,int jid) {
        this.onoma = onoma;
        //this.eidikotita = eidikotita;
        this.emploeesNo = emploeesNo;
        this.sid = sid;
        this.jid = jid;
    }

    public Vardies(int sid, String onoma) {
        this.onoma = onoma;
        this.sid = sid;
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

    public int getEmploeesNo() {
        return emploeesNo;
    }

    public void setOnoma(String onoma) {
        this.onoma = onoma;
    }

    public void setEidikotita(String eidikotita) {
        this.eidikotita = eidikotita;
    }

    public void setEmploeesNo(int emploeesNo) {
        this.emploeesNo = emploeesNo;
    }
}
