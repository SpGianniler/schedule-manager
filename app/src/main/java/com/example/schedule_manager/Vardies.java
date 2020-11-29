package com.example.schedule_manager;

public class Vardies {
    private String onoma;
    private String eidikotita;
    private int emploeesNo;

    public Vardies(String onoma, String eidikotita, int emploeesNo) {
        this.onoma = onoma;
        this.eidikotita = eidikotita;
        this.emploeesNo = emploeesNo;
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
