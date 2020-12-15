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

public class AdminLoginActivity extends BaseActivity {
    private Button adminLoginButton;
    protected static String Username, Eidikotita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login_activity);


        adminLoginButton=(Button) findViewById(R.id.adminLoginButton);
        EditText username,password;
        username = (EditText) findViewById(R.id.editTextEmailAdmin);
        password = (EditText) findViewById(R.id.editTextPasswordAdmin);

        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
    }

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
    static public String searchEidikotita(int eID, ArrayList<Ergazomenoi> ergazomenois){

        String eidikotita = null;

        List<Ergazomenoi> ergazomenoiList = ergazomenois;
        for(Ergazomenoi erg : ergazomenoiList){
            if(erg.getErg_id() == eID){
                eidikotita = erg.getEidikotita();
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