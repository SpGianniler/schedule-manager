/**
 * Αυτή η κλάση ειναι υπεθυνη για την διαχείριση των αιτησεων Αδειώναπό τους εργαζόμενους
 */
package com.example.schedule_manager;

public class Adeies {
    protected String Date;
    protected int eid ;
    protected int Duration ;
    protected String Reason;

    public Adeies(String date, int eid, int duration, String reason) {
        Date = date;
        this.eid = eid;
        Duration = duration;
        Reason = reason;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }
}
