package com.example.schedule_manager;

import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

public class UserLoginActivity extends BaseActivity {
    private Button userLoginButton;
    protected static String Username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        userLoginButton = (Button) findViewById(R.id.userLoginButton);
        EditText username, password;
        username = (EditText) findViewById(R.id.editTextEmailUser);
        password = (EditText) findViewById(R.id.editTextPasswordUser);

        userLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernametext = username.getText().toString();
                String passwordtext = password.getText().toString();
                boolean result;
                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());

                result = Credentials.isValid(usernametext,passwordtext,false,dataBaseAccess);

                if(result){
                    int eid = searchByUserName(usernametext, dataBaseAccess);
                    Username = searchByEid(eid, dataBaseAccess);
                    openActivityUWS();
                }
                else
                    Toast.makeText(UserLoginActivity.this,"Try Again",Toast.LENGTH_SHORT).show();
            }
        });
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

    public int searchByUserName(String username, DataBaseAccess dba){
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

    public static String getUsername(){
        return Username;
    }

    public void openActivityUWS(){
        Intent intent = new Intent(this, UserWelcomeActivity.class);
        startActivity(intent);
    }
}