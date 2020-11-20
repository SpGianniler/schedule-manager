package com.example.schedule_manager;

import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class UserLoginActivity extends BaseActivity {
    private Button userLoginButton;
    private int eid=0;
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
                    eid = searchByUserName(usernametext, dataBaseAccess);
                    openActivityUWS();
                }
                else
                    Toast.makeText(UserLoginActivity.this,"Try Again",Toast.LENGTH_SHORT).show();
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

    public void openActivityUWS(){
        Intent intent = new Intent(this, UserWelcomeActivity.class);
        startActivity(intent);
    }
}