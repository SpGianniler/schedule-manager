package com.example.schedule_manager.userUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schedule_manager.BaseActivity;
import com.example.schedule_manager.Credentials;
import com.example.schedule_manager.MainActivity;
import com.example.schedule_manager.R;
import com.example.schedule_manager.adminUI.AdminLoginActivity;


public class UserLoginActivity extends BaseActivity {
    private Button userLoginButton;
    protected static String Username;
    protected static String Eidikotita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_activity);

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

                result = Credentials.isValid(usernametext,passwordtext,false, MainActivity.getCredentialsArrayList());

                if(result){
                    int eid = AdminLoginActivity.searchByUserName(usernametext, MainActivity.credentialsList);
                    Username = AdminLoginActivity.searchByEid(eid, MainActivity.getErgazomenoiArrayList());
                    Eidikotita = AdminLoginActivity.searchEidikotita(eid, MainActivity.getErgazomenoiArrayList());
                    openActivityUWS();
                }
                else
                    Toast.makeText(UserLoginActivity.this,"Try Again",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static String getEidikotita() {
        return Eidikotita;
    }

    public static String getUsername() {
        return Username;
    }

    public void openActivityUWS(){
        Intent intent = new Intent(this, UserWelcomeActivity.class);
        startActivity(intent);
    }
}