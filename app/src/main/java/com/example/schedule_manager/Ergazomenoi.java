package com.example.schedule_manager;

public class Ergazomenoi {
    protected String onoma;
    protected String epitheto;
    protected String eidikotita;
    protected int evWres;
    protected String contract;
    protected int erg_id;

    public Ergazomenoi(int erg_id, String onoma, String epitheto, String eidikotita, int whours, String contract) {
        this.erg_id = erg_id;
        this.onoma = onoma;
        this.epitheto = epitheto;
        this.eidikotita = eidikotita;
        this.evWres = whours;
        this.contract = contract;
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
    public int getEvWres() {
        return evWres;
    }
    public String getContract(){return contract;}
    public int getErg_id() { return erg_id; }
}