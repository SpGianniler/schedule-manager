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

    public Ergazomenoi() {
    }

    public Ergazomenoi(int erg_id, String onoma, String epitheto, String eidikotita) {
        this.erg_id = erg_id;
        this.onoma = onoma;
        this.epitheto = epitheto;
        this.eidikotita = eidikotita;
        this.evWres = 0;
        this.contract = "";
        this.is_admin = false;
    }

    public Ergazomenoi(String onoma, String epitheto, String eidikotita, int whours, String contract, boolean is_admin) {
        this.onoma = onoma;
        this.epitheto = epitheto;
        this.eidikotita = eidikotita;
        this.evWres = whours;
        this.contract = contract;
        this.is_admin = is_admin;
    }

    @Override
    public String toString() {
        return "Ergazomenoi{" +
                "onoma='" + onoma + '\'' +
                ", epitheto='" + epitheto + '\'' +
                ", eidikotita='" + eidikotita + '\'' +
                ", evWres=" + evWres +
                ", contract='" + contract + '\'' +
                ", erg_id=" + erg_id +
                ", is_admin=" + is_admin +
                '}';
    }

    public void setOnoma(String onoma) {
        this.onoma = onoma;
    }

    public void setEpitheto(String epitheto) {
        this.epitheto = epitheto;
    }

    public void setEidikotita(String eidikotita) {
        this.eidikotita = eidikotita;
    }

    public void setEvWres(int evWres) {
        this.evWres = evWres;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public void setErg_id(int erg_id) {
        this.erg_id = erg_id;
    }

    public void setIs_admin(boolean is_admin) {
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