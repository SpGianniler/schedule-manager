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
    private boolean picked;
    private int seqDays;

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

    public void clrPick(){
        this.picked = false;
    }

    public int getSeqDays() {
        return seqDays;
    }
    public void addSeqDays(){
        this.seqDays++;
    }
    public boolean checkSeqDays(){
        if(this.seqDays < 5){
            return true;
        }
        else {
            if(this.seqDays == 5){
                this.picked = true;
                this.seqDays = 0;
            }
            return false;
        }
    }

        public void remHours() {
            if(this.ergazomenos.contract.equals("full"))
                this.ergazomenos.evWres -=8;
            else
                this.ergazomenos.evWres-=4;
        }
}
