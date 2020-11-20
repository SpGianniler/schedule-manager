package com.example.schedule_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AdminLoginActivity extends BaseActivity {
    private Button adminLoginButton;
    private int eid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


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
                    eid = searchByUserName(usernametext, dataBaseAccess);
                    //Toast.makeText(AdminLoginActivity.this,eid,Toast.LENGTH_SHORT).show();
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

    public int getEid() {
        return eid;
    }

    public void openActivityAWA(){
        Intent intent = new Intent(this, AdminWelcomeActivity.class);
        startActivity(intent);
    }
}