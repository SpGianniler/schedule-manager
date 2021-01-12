/**
 * η κλάση αυτή είναι υπέυθυνη για τον υπολογισμό του προγράμματος και όλους τους απαραίτητους
 * ελέγχους που πρέπει να  γίνουν για να έχουμε ενα σωστό αποτέλεσμα
 *
  */
package com.example.schedule_manager;

    import java.text.ParseException;
    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Random;

    public class Schedule {
        String date;
        String vardia;
        String onoma;
        String epitheto;
        String eidikothta;

        static ArrayList<Schedule> sched = new ArrayList<>();
        static ArrayList<Ergazomenoi> ergazomenoiList;
        static HashMap<String, String> shiftsMap;
        static List<Vardies> vardiesList;
        static Matrix[] matrix;

        /**
         * η κλάση αυτή αρχικοποιεί τις απαραίτητες λίστες και παιρνει ως όρισμα την διάρκεια για
         * την οποία θέλουμε να υπολογίσουμε το πρόγραμμα κάθως και την εναρκτήρια ημερομηνία
         * @param diarkeia
         * @param firDate
         * @return
         */
        public static ArrayList<Schedule> onCreate(int diarkeia, String firDate) {

            ergazomenoiList = MainActivity.getErgazomenoiArrayList();
            shiftsMap = MainActivity.getShiftsMap();
            vardiesList = MainActivity.getVardiesList();
            matrix = new Matrix[ergazomenoiList.size()];
            int i = 0;

            for (Ergazomenoi erg : ergazomenoiList) {
                matrix[i] = new Matrix(erg);
                i++;
            }
            for(int meres =0; meres <diarkeia; meres++) {
                ArrayList<String> arg = (ArrayList<String>) MainActivity.getArgeiesList();
                if(arg!=null){
                    for (String argia : arg) {
                        if (argia.equals(firDate)) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            Calendar c = Calendar.getInstance();
                            try {
                                c.setTime(sdf.parse(firDate));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            c.add(Calendar.DATE, 1);  // number of days to add
                            firDate = sdf.format(c.getTime());
                        }
                    }
                }
                //if(firDate.equals())
                createSchedule(matrix, vardiesList, shiftsMap);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(sdf.parse(firDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.add(Calendar.DATE, 1);  // number of days to add
                firDate = sdf.format(c.getTime());
            }
            return sched;
        }

        /**
         * η συνάρτηση αυτή υπολογίζει το πογραμμα κάνει τους απαραίτητους ελέγχους
         * και τα ορίσματα της είναι ένας πίνακας με τους εργαζόμενους (αντικείμενο Matrix),
         * μία λίστα με τις βάρδιες (ArrayList<Vardies>)
         * ενα HashMap με τις ειδικότητες HashMap<String, String>
         * @param matrix_arr
         * @param vardies
         * @param shiftsMap
         * @return
         */
        static public List<Schedule> createSchedule(Matrix[] matrix_arr, List<Vardies> vardies, HashMap<String, String> shiftsMap) {
            String on_vardias;
            String on_eidik;
            int ar_erg;
            int min = 0;
            boolean found = false;

                clearPicked(matrix_arr);
                for (Vardies vardia : vardies) {
                    HashMap<Matrix,Integer> ergazomenoiIdiasWras2 = new HashMap<>();
                    on_vardias = vardia.getOnoma();
                    on_eidik = vardia.getEidikotita();
                    ar_erg = vardia.getEmployeesNo();
                    min = minHoursOnVard(matrix_arr, on_vardias);
                        for (Matrix matrix : matrix_arr) {
                            found = false;
                            if (matrix.getErgazomenos().getEidikotitaName().equals(on_eidik) && ar_erg > 0 && !matrix.isPicked() && matrix.checkSeqDays(matrix) && matrix.getTotalHours() >= 0) {//changed
                                if (hasLeastHours(matrix, min, on_vardias)) {
                                    sched.add(new Schedule(on_vardias, matrix.getErgazomenos().getOnoma(), matrix.getErgazomenos().getEpitheto(), on_eidik));//changed vrd
                                    matrix.addHours(on_vardias);
                                    min = matrix.getHours(on_vardias);
                                    matrix.remHours();
                                    //matrix.seqDays++;
                                    min = matrix.getHours(on_vardias);
                                    ar_erg--;
                                    matrix.pick();
                                    found = true;
                                } else if (hasEqualsHours(matrix, min, on_vardias)) {
                                    ergazomenoiIdiasWras2.put(matrix, matrix.getTotalHours());
                                }
                            }
                        }
                        if (!found || ergazomenoiIdiasWras2.size() > ar_erg) {
                            if(ergazomenoiIdiasWras2.size() < ar_erg){
                                Matrix[] matrixArrayList = new Matrix[100];
                                int i =0;
                                for(Matrix mtr : matrix_arr){
                                    if(mtr.getErgazomenos().getEidikotitaName().equals(on_eidik) && mtr.isPicked() == false){//changed
                                        matrixArrayList[i] = mtr;
                                        i++;
                                    }
                                }
                                while(ergazomenoiIdiasWras2.size() < ar_erg) {
                                    Random rand = new Random();
                                    int pos = rand.nextInt(matrixArrayList.length);
                                    Matrix mtr = matrixArrayList[pos];
                                    if (mtr != null) {
                                        ergazomenoiIdiasWras2.put(mtr, mtr.getTotalHours());
                                    }
                                }
                            }
                                while (ar_erg != 0) {
                                    Matrix matrix = getLeastTotal(ergazomenoiIdiasWras2);
                                    sched.add(new Schedule(on_vardias, matrix.getErgazomenos().getOnoma(), matrix.getErgazomenos().getEpitheto(), on_eidik));//change vrd
                                    matrix.addHours(on_vardias);
                                    min = matrix.getHours(on_vardias);
                                    matrix.remHours();
                                    //matrix.seqDays++;
                                    ar_erg--;
                                    matrix.pick();
                                    ergazomenoiIdiasWras2.remove(matrix.getErgazomenos());
                                 }
                        }
                    }

            return sched;
        }

        /**
         * η μέθοδος αυτή βρίσκει τον εργαζόμενο με τις λιγότερες ώρες σε συγκεκριμένη βάρδια
         * μιας συγκεκριμενης ειδικοτητας
         * @param matrix
         * @param on_vard
         * @return
         */
        private static int minHoursOnVard(Matrix[] matrix, String on_vard) {
            int hours=1000;
            for(Matrix mtr : matrix){
                if(mtr.getHours(on_vard) < hours && mtr.getErgazomenos().getEidikotitaName().equals(on_vard)){//changed
                    hours = mtr.getHours(on_vard);
                }
            }
            return hours;

        }

        public Schedule(String Date, String vardia, String onoma, String epitheto, String eidikothta) {
            this.date = Date;
            this.vardia = vardia;
            this.onoma = onoma;
            this.epitheto = epitheto;
            this.eidikothta = eidikothta;
        }

        /**
         * η συνάρτηση αυτή επιστρέφει τον εργαζόμενο με τις ελάχιστες ώρες εργασίας
         * @param ergazomenoi
         * @return
         */
        public static Matrix getLeastTotal(HashMap<Matrix,Integer> ergazomenoi) {
            int elaxistes = 1000;
            for (int el : ergazomenoi.values()) {
                if (el < elaxistes) {
                    elaxistes = el;
                }
            }
            Matrix matrix_to_return = null;
            for (Matrix matrix : ergazomenoi.keySet()) {
                if (matrix.getTotalHours() <= elaxistes)
                   matrix_to_return = matrix;
            }
            ergazomenoi.remove(matrix_to_return);
            return matrix_to_return;
        }

        /**
         * Η μ'εθοδος αυτή θέτει όλους τους εργαζόμενους διαθέσιμους για επιλογή από την εφαρμογή
         * για τοποθέτηση στο πρόγραμμα εργασίας
         * @param matrix
         */
        static public void clearPicked(Matrix[] matrix){
            for(Matrix mtr: matrix){
                mtr.clrPick();
            }
        }

        /**
         * η μέθοδος αυτή βρίσκει τον εργαζόμενο με τις ελάχιστες ώρες σε μία συγκεκριμένη βαρδια
         * @param matrix
         * @param min
         * @param vardia
         * @return
         */
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

        /**
         * η μέθοδος αυτή ελέγχει αν ο εργαζόμενος εχει ώρες εργασίας ίσες με τις ελάχιστες στην συγκεκιμένη
         * βάρδια στην συγκεκριμένη ειδικότητα
         * @param matrix
         * @param min
         * @param vardia
         * @return
         */
        public static boolean hasEqualsHours(Matrix matrix, int min, String vardia){
            if(vardia.equals("6")){
                if(matrix.getWres6()==min || matrix.getWres6()==min+4 ){
                    return true;
                }
            }
            else if(vardia.equals("1")){
                if(matrix.getWres1()==min || matrix.getWres1()==min+4){
                    return true;
                }
            }
            else if(vardia.equals("2")){
                if(matrix.getWres2()==min || matrix.getWres2()==min+4){
                    return true;
                }
            }
            else if(vardia.equals("3")|| matrix.getWres3()==min+4){
                if(matrix.getWres3()==min){
                    return true;
                }
            }
            else if(vardia.equals("4") || matrix.getWres4()==min+4){
                if(matrix.getWres4()==min){
                    return true;
                }
            }
            else if(vardia.equals("5") || matrix.getWres5()==min+4){
                if(matrix.getWres5()==min){
                    return true;
                }
            }
            return false;
        }

        /**
         * η μέθοδος αυτή επιλέγει τυχαία εναν εργαζόμενο σε περίπτωση που περισσοτεροι από ενας εχουν
         * ώρες εργασίας ίσες με τις ελάχιστες
         * @param matrix
         * @return
         */
        public static Matrix pickRand(Matrix[] matrix){
            Random rand = new Random();
            int pos = rand.nextInt(matrix.length);
            Matrix mtr = matrix[pos];
            return mtr;
        }
        public static Matrix pickLeastTotHours(Matrix[] matrices){
            int ela=matrices[0].getTotalHours();
            Matrix[] matrixArrayList = new Matrix[matrices.length];
            int j =0;

            for(int i=0;i<matrices.length;i++){
                if(matrices[i].getTotalHours()<=ela){
                    ela = matrices[i].getTotalHours();
                    matrixArrayList[j] = (matrices[i]);
                    j++;
                }
            }
            Matrix returnedMatrix = pickRand(matrixArrayList);
            return returnedMatrix;
        }

        /**
         * η μέθοδος αυτή ελέγχει αν ο αριθμός εργαζόμενων επαρκεί για τον υπολογισμό του προγράμματος
         * @return
         */
        public static boolean checkErg(){
            ArrayList<Vardies> ArAtomonSeVardies = (ArrayList<Vardies>) MainActivity.getVardiesList();
            int empNo,empNoNeeded;
            for(Vardies vrd : ArAtomonSeVardies) {
                empNo =0;
                empNoNeeded = vrd.getEmployeesNo();
                for (Ergazomenoi erg :MainActivity.getErgazomenoiArrayList()) {
                    if (vrd.getEidikotita().equals(erg.getEidikotitaName())){//changed
                        empNo++;
                    }
                }
                if(empNo < empNoNeeded){
                    return false;
                }
            }
            return true;
        }

        public Schedule(String vardia, String onoma, String epitheto, String eidikothta) {
            this.vardia = vardia;
            this.onoma = onoma;
            this.epitheto = epitheto;
            this.eidikothta = eidikothta;
        }

        public String getVardia() {
            return vardia;
        }

        public String getOnoma() {
            return onoma;
        }

        public String getEpitheto() {
            return epitheto;
        }

        public String getEidikothta() {
            return eidikothta;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDate() {
            return date;
        }

        public void setVardia(String vardia) {
            this.vardia = vardia;
        }

        public void setOnoma(String onoma) {
            this.onoma = onoma;
        }

        public void setEpitheto(String epitheto) {
            this.epitheto = epitheto;
        }

        public void setEidikothta(String eidikothta) {
            this.eidikothta = eidikothta;
        }

        public static ArrayList<Schedule> getSched() {
            return sched;
        }

        public static void setSched(ArrayList<Schedule> sched) {
            Schedule.sched = sched;
        }

        public static ArrayList<Ergazomenoi> getErgazomenoiList() {
            return ergazomenoiList;
        }

        public static void setErgazomenoiList(ArrayList<Ergazomenoi> ergazomenoiList) {
            Schedule.ergazomenoiList = ergazomenoiList;
        }

        public static HashMap<String, String> getShiftsMap() {
            return shiftsMap;
        }

        public static void setShiftsMap(HashMap<String, String> shiftsMap) {
            Schedule.shiftsMap = shiftsMap;
        }

        public static List<Vardies> getVardiesList() {
            return vardiesList;
        }

        public static void setVardiesList(List<Vardies> vardiesList) {
            Schedule.vardiesList = vardiesList;
        }

        public static Matrix[] getMatrix() {
            return matrix;
        }

        public static void setMatrix(Matrix[] matrix) {
            Schedule.matrix = matrix;
        }

    }