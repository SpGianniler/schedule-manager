package com.example.schedule_manager.adminUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schedule_manager.BaseActivity;
import com.example.schedule_manager.Credentials;
import com.example.schedule_manager.Ergazomenoi;
import com.example.schedule_manager.MainActivity;
import com.example.schedule_manager.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.schedule_manager.MainActivity.vardiesList;

public class AdminLoginActivity extends BaseActivity {
    private Button adminLoginButton;
    protected static String Username, Eidikotita;

    final MainActivity mainActivity = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login_activity);


        adminLoginButton=(Button) findViewById(R.id.adminLoginButton);
        EditText username,password;
        username = (EditText) findViewById(R.id.editTextEmailAdmin);
        password = (EditText) findViewById(R.id.editTextPasswordAdmin);

//        if(mainActivity.shiftsMap==null && mainActivity.eidikotitesMap ==null) {
            mainActivity.shiftsMap = mainActivity.mainService.popShiftsMap(vardiesList);
            mainActivity.eidikotitesMap = mainActivity.mainService.popEidList(vardiesList);
//        }

        /**
         * με το πάτημα του κουμπιού login γίνεται ο απαραίτητος ελεγχος από την κλάση Credentials
         * για την επαληθευση των στοιχείων που πληκτρολογήθηκαν
         */adminLoginButton.setOnClickListener(v -> {
             String usernametext = username.getText().toString();
             String passwordtext = password.getText().toString();
             boolean result;
             ArrayList<Credentials> listOfCreds = MainActivity.getCredentialsArrayList();

             result = Credentials.isValid(usernametext,passwordtext,true, listOfCreds);

             if(result) {
                 int eid = searchByUserName(usernametext, listOfCreds);
                 Username = searchByEid(eid, MainActivity.getErgazomenoiArrayList());
                 Eidikotita = searchEidikotita(eid, MainActivity.getErgazomenoiArrayList());
                 openActivityAWA();
             }
             else {
                 Toast.makeText(AdminLoginActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
             }
         });
    }

    /**
     * αναζήτηση χρήστη βάση του Username του
     * @param username
     * @param listOfCreds
     * @return
     */
    public  static int searchByUserName(String username, ArrayList<Credentials> listOfCreds){
        int eID=0;

        for(Credentials cred : listOfCreds){
            if(cred.getUsername().toString().equals(username)) {
                eID = cred.getEid();
                return eID;
            }
        }
        return 0;
    }

    /**
     * η μέθοδος αυτή κάνει αναζήτηση βάση του eid του χρήστη
     * @param eID
     * @param arrayList
     * @return
     */
    static public String searchByEid(int eID, ArrayList<Ergazomenoi>arrayList){
        String username = null;

        List<Ergazomenoi> ergazomenoiList = arrayList;
        for(Ergazomenoi erg : ergazomenoiList){
            if(erg.getErg_id() == eID){
                username = erg.getOnoma();
            }
        }
        return username;
    }

    /**
     * η μέθοδος αυτή κάνει αναζήτηση της ειδικότητας ενός εργαζόμενου
     * @param eID
     * @param ergazomenois
     * @return
     */
    static public String searchEidikotita(int eID, ArrayList<Ergazomenoi> ergazomenois){

        String eidikotita = null;

        List<Ergazomenoi> ergazomenoiList = ergazomenois;
        for(Ergazomenoi erg : ergazomenoiList){
            if(erg.getErg_id() == eID){
                return erg.getEidikotitaName();
            }
        }
        return eidikotita;
    }

    public static String getEidikotita() {
        return Eidikotita;
    }

    public static String getUsername(){
        return Username;
    }

    public void openActivityAWA(){
        Intent intent = new Intent(this, AdminWelcomeActivity.class);
        startActivity(intent);
    }
}