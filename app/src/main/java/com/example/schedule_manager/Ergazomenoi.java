package com.example.schedule_manager;

public class Ergazomenoi {
    protected String onoma;
    protected String epitheto;
    protected String eidikotita;
    protected int evWres;
    protected String contract;
    protected int erg_id;
    protected boolean is_admin;

    public Ergazomenoi(int erg_id, String onoma, String epitheto, String eidikotita, int whours, String contract, boolean is_admin) {
        this.erg_id = erg_id;
        this.onoma = onoma;
        this.epitheto = epitheto;
        this.eidikotita = eidikotita;
        this.evWres = whours;
        this.contract = contract;
        this.is_admin = is_admin;
    }

    public Ergazomenoi(String onoma, String epitheto, String eidikotita, int whours, String contract, boolean is_admin) {
        this.onoma = onoma;
        this.epitheto = epitheto;
        this.eidikotita = eidikotita;
        this.evWres = whours;
        this.contract = contract;
        this.is_admin = is_admin;
    }

    public String getOnoma() {
        return onoma;
    }
    public String getEpitheto() {
        return epitheto;
    }
    public String getEidikotita() {
        return eidikotita;
    }
    public String  getMail(){return "E-mail";}
    public String getPhone(){return "69898789878";}
    public int getEvWres() {
        return evWres;
    }
    public String getContract(){return contract;}
    public int getErg_id() { return erg_id; }
    public boolean getIsAdmin() { return is_admin; }
}