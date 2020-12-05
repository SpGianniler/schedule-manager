package com.example.schedule_manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Schedule {
    String vardia;
    String onoma;
    String epitheto;
    String eidikothta;


    static public ArrayList<Schedule> onCreate(){

        List<Ergazomenoi> ergazomenoiList = MainActivity.getErgazomenoiArrayList();
        HashMap<String, String> shiftsMap= MainActivity.getShiftsMap();
        List<Vardies> vardiesList = MainActivity.getVardiesList();
        //vardiesList.add(new Vardies("4","grammateia",1));
        Matrix matrix[] = new Matrix[ergazomenoiList.size()];
        int i =0;

        for(Ergazomenoi erg : ergazomenoiList){
            matrix[i] = new Matrix (erg);
            i++;
        }
        return (ArrayList)createSchedule(matrix, vardiesList, shiftsMap);
    }

    static public List<Schedule> createSchedule(Matrix[] matrix_arr, List<Vardies> vardies, HashMap<String, String> shiftsMap) {
        List<Ergazomenoi> ergazomenoiIdiasWras = new ArrayList<>();
        String on_vardias;
        String on_eidik;
        int ar_erg;
        int min = 0;
        boolean found = false;

        List<Schedule> scd = new ArrayList<>();
            clearPicked(matrix_arr);
            for (Vardies vardia : vardies) {
                on_vardias = vardia.getOnoma();
                on_eidik = vardia.getEidikotita();
                ar_erg = vardia.getEmploeesNo();

                for (Matrix matrix : matrix_arr) {
                    if (matrix.getErgazomenos().getEidikotita().equals(on_eidik) && ar_erg > 0 && matrix.isPicked() == false && matrix.checkSeqDays()) {
                        if (hasLeastHours(matrix, min, on_vardias)) {
                            scd.add(new Schedule(shiftsMap.get(on_vardias), matrix.getErgazomenos().getOnoma(), matrix.getErgazomenos().getEpitheto(), on_eidik));
                            matrix.addHours(on_vardias);
                            matrix.addSeqDays();
                            min = matrix.getHours(on_vardias);
                            ar_erg--;
                            matrix.pick();
                            found = true;
                        }
                        else if(hasEqualsHours(matrix, min, on_vardias)){
                            ergazomenoiIdiasWras.add(matrix.getErgazomenos());
                        }
                    }
                   // getMatrix(matrix);
                }
                if(!found && ergazomenoiIdiasWras.size() > 0) {
                    while(ar_erg != 0) {
                        Matrix matrix = pickRand(matrix_arr);
                        scd.add(new Schedule(shiftsMap.get(on_vardias), matrix.getErgazomenos().getOnoma(), matrix.getErgazomenos().getEpitheto(), on_eidik));
                        matrix.addHours(on_vardias);
                        matrix.addSeqDays();
                        min = matrix.getHours(on_vardias);
                        ar_erg--;
                        matrix.pick();
                        ergazomenoiIdiasWras.remove(matrix.getErgazomenos());
                    }
                    ergazomenoiIdiasWras.clear();
                }
            }
        return scd;
    }

    public Schedule(String vardia, String onoma, String epitheto, String eidikothta) {
        this.vardia = vardia;
        this.onoma = onoma;
        this.epitheto = epitheto;
        this.eidikothta = eidikothta;
    }

    static public void clearPicked(Matrix[] matrix){
        for(Matrix mtr: matrix){
            mtr.clrPick();
        }
    }

    public static boolean hasLeastHours(Matrix matrix, int min, String vardia){

        if(vardia.equals("6")){
            if(matrix.getWres6()<min){
                return true;
            }
        }
        else if(vardia.equals("1")){
            if(matrix.getWres1()<min){
                return true;
            }
        }
        else if(vardia.equals("2")){
            if(matrix.getWres2()<min){
                return true;
            }
        }
        else if(vardia.equals("3")){
            if(matrix.getWres3()<min){
                return true;
            }
        }
        else if(vardia.equals("4")){
            if(matrix.getWres4()<min){
                return true;
            }
        }
        else if(vardia.equals("5")){
            if(matrix.getWres5()<min){
                return true;
            }
        }
            return false;
    }
    public static boolean hasEqualsHours(Matrix matrix, int min, String vardia){
        if(vardia.equals("6")){
            if(matrix.getWres6()==min){
                return true;
            }
        }
        else if(vardia.equals("1")){
            if(matrix.getWres1()==min){
                return true;
            }
        }
        else if(vardia.equals("2")){
            if(matrix.getWres2()==min){
                return true;
            }
        }
        else if(vardia.equals("3")){
            if(matrix.getWres3()==min){
                return true;
            }
        }
        else if(vardia.equals("4")){
            if(matrix.getWres4()==min){
                return true;
            }
        }
        else if(vardia.equals("5")){
            if(matrix.getWres5()==min){
                return true;
            }
        }
        return false;
    }
    public static Matrix pickRand(Matrix[] matrix){
        Random rand = new Random();
        int pos = rand.nextInt(matrix.length);
        return  matrix[pos];

    }

}

//ToDo: Na vrw pws tha ksexwrizw tis diaforetikes meres gia na ginetai to clearPicked