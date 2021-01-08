/**
 * Η κλάση αυτή περιγράφει για κάθε εργαζόμενο τις ώρες που έχει δουλέψει σε κάθε βάρδια και επίσης
 * τις συνεχόμενες μερες τις οποίες εχει δουλέψει οπώς επίσης και τις προσωπικές πληροφορίες του
 * καθενός
 */
package com.example.schedule_manager;

public class Matrix {
    private Ergazomenoi ergazomenos;
    private int wres6;
    private int wres1;
    private int wres2;
    private int wres3;
    private int wres4;
    private int wres5;
    private int totalHours;
    protected boolean picked;
    protected int seqDays;

    public Matrix(Ergazomenoi ergazomenos) {
        this.ergazomenos = ergazomenos;
        this.wres6 = 0;
        this.wres1 = 0;
        this.wres2 = 0;
        this.wres3 = 0;
        this.wres4 = 0;
        this.wres5 = 0;
        this.totalHours=0;
        this.picked = false;
    }

    public Ergazomenoi getErgazomenos() { return ergazomenos; }

    public int getWres6() {
        return wres6;
    }

    public int getWres1() {
        return wres1;
    }

    public int getWres2() {
        return wres2;
    }

    public int getWres3() {
        return wres3;
    }

    public int getWres4() {
        return wres4;
    }

    public int getWres5() {
        return wres5;
    }

    public boolean isPicked() {
        return picked;
    }

    public void pick(){
        this.picked= true;
    }

    public int getTotalHours() {
        return totalHours;
    }

    /**
     * η μέθοδος αυτή προσθέτει τις απαραίτητες ώρες εργασίας στις συνολικςέ του κάθε υπαλλήλου
     * ανάλογα τον τυπο του συμβολαίου του
     * @param vardia
     */
        public void addHours(String vardia){
            if(vardia.equals("6")){
                if(this.ergazomenos.contract.equals("full"))
                    this.wres6 +=8;
                else
                    this.wres6+=4;
            }
            else if(vardia.equals("1")){
                if(this.ergazomenos.contract.equals("full"))
                    this.wres1 +=8;
                else
                   this.wres1+=4;
            }
            else if(vardia.equals("2")){
                if(this.ergazomenos.contract.equals("full"))
                    this.wres2 +=8;
                else
                   this.wres2+=4;
            }
            else if(vardia.equals("3")){
                if(this.ergazomenos.contract.equals("full"))
                    this.wres3 +=8;
                else
                    this.wres3+=4;
            }
            else if(vardia.equals("4")){
                if(this.ergazomenos.contract.equals("full"))
                    this.wres4+=8;
                else
                    this.wres4+=4;
            }
            else if(vardia.equals("5")){
                if(this.ergazomenos.contract.equals("full"))
                    this.wres5 +=8;
                else
                    this.wres5+=4;
            }
            if(this.ergazomenos.contract.equals("full"))
                this.totalHours+=8;
            else
                this.totalHours+=4;
        }

    /**
     * Η συνάρτηση αυτή επιστρέφει τις ώρες που δούλεψε ο εργαζόμενος σε συγκεκριμένη βάρδια την οποία παίρνει
     * ως όρισμα
     * @param vardia
     * @return
     */
    public int getHours(String vardia){
        if(vardia.equals("6")){
            return this.wres6;
        }
        else if(vardia.equals("1")){
            return this.wres1;
        }
        else if(vardia.equals("2")){
            return this.wres2;
        }
        else if(vardia.equals("3")){
            return this.wres3;
        }
        else if(vardia.equals("4")){
            return this.wres4;
        }
        else{
            return this.wres5;
        }
    }

    /**
     * η Μέθοδος αυτή θέτει όλους τους εργαζόμενους διαθέσιμους για προσθήκη στο πρόγραμμα
     */
    public void clrPick(){
        this.picked = false;
    }

    /**
     * η συνάρτηση αυτή επιστρέφει τις συνεχόμενες μερες τις οποίες έχει εργαστεί ενας εργαζόμενος
     * @return
     */
    public int getSeqDays() {
        return seqDays;
    }

    /**
     * η μέθοδος αυτή προσθέτει μια μέρα στις συνεχόμενεςμέρες εργασίας του συγκεκριμένου υπαλληλου
     * @param matrix
     */
    public void addSeqDays(Matrix matrix){
        matrix.seqDays++;
    }

    /**
     * η μέθοδος αυτή ελεχει τις συνεχόμενες μέρες τις οποίες έχει δουλέψει ο εργαζόμενος
     * αν οι συνεχόμενες μερες ειναι 3 τότε τον βάζει μη διαθέσιμο για τοποθέτηση στο πρόγραμμα
     * και μηδενίζει τις συνεχόμενες μέρες.
     * @param matrix
     * @return
     */
    public boolean checkSeqDays(Matrix matrix){
        boolean returned = false;
        if(matrix.seqDays < 3){
            returned = true;
        }
        else {
            if(matrix.seqDays == 3){
                matrix.picked = true;
                matrix.seqDays = 0;
                returned = false;
            }
        }
        return returned;
    }

    /**
     * η μέθοδος αυτή αφαιρεί από τις επιθυμητές εβδομαδιαίες ώρες εργασίας ενος συγκεκριμένουυ υπαλληλου
     * ανάλογα τις ώρες που υποστηρίζει το συμβόλαιο του
     */
        public void remHours() {
            if(this.ergazomenos.contract.equals("full"))
                this.ergazomenos.evWres -=8;
            else
                this.ergazomenos.evWres-=4;
        }
}
