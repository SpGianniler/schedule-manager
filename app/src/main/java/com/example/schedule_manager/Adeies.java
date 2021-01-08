/**
 * Αυτή η κλάση ειναι υπεθυνη για την διαχείριση των αιτησεων Αδειώναπό τους εργαζόμενους
 */
package com.example.schedule_manager;

public class Adeies {
    protected String date;
    protected int eid ;
    protected int duration;
    protected String reason;

    public Adeies(String date, int eid, int duration, String reason) {
        this.date = date;
        this.eid = eid;
        this.duration = duration;
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
