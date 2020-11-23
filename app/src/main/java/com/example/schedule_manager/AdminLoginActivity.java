package com.example.schedule_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());

                result = Credentials.isValid(usernametext,passwordtext,true,dataBaseAccess);

                if(result) {
                    int eid = searchByUserName(usernametext, dataBaseAccess);
                    Username = searchByEid(eid, dataBaseAccess);
                    Eidikotita = searchEidikotita(eid, dataBaseAccess);
                    openActivityAWA();
                }
                else
                    Toast.makeText(AdminLoginActivity.this,"Try Again",Toast.LENGTH_SHORT).show();
            }
        });
    }

    int searchByUserName(String username, DataBaseAccess dba){
        int eID=0;

        dba.openDB();

        List<Credentials> listOfCreds = dba.getCredentials();

        for(Credentials cred : listOfCreds){
            if(cred.getUsername().toString().equals(username)) {
                eID = cred.getEid();
                return eID;
            }
        }
        return 0;
    }

    public String searchByEid(int eID, DataBaseAccess dba){
        String username = null;
        dba.openDB();
        List<Ergazomenoi> ergazomenoiList = dba.getEveryone();
        for(Ergazomenoi erg : ergazomenoiList){
            if(erg.getErg_id() == eID){
                username = erg.getOnoma();
            }
        }
        dba.closeDB();
        return username;
    }
    public static String searchEidikotita(int eID, DataBaseAccess dba){

        String eidikotita = null;
        dba.openDB();
        List<Ergazomenoi> ergazomenoiList = dba.getEveryone();
        for(Ergazomenoi erg : ergazomenoiList){
            if(erg.getErg_id() == eID){
                eidikotita = erg.getEidikotita();
            }
        }
        dba.closeDB();
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