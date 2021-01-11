package com.example.schedule_manager.userUI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schedule_manager.BaseActivity;
import com.example.schedule_manager.Credentials;
import com.example.schedule_manager.MainActivity;
import com.example.schedule_manager.R;
import com.example.schedule_manager.adminUI.AdminLoginActivity;

import static com.example.schedule_manager.MainActivity.vardiesList;


public class UserLoginActivity extends BaseActivity {
    private Button userLoginButton;
    protected static String Username;
    protected static String Eidikotita;
    protected static int eid=0;

    final MainActivity mainActivity = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_activity);

        userLoginButton = (Button) findViewById(R.id.userLoginButton);
        EditText username = (EditText) findViewById(R.id.editTextEmailUser);
        EditText password = (EditText) findViewById(R.id.editTextPasswordUser);

        if(MainActivity.shiftsMap==null && MainActivity.eidikotitesMap ==null) {
            mainActivity.shiftsMap = mainActivity.mainService.popShiftsMap(vardiesList);
            mainActivity.eidikotitesMap = mainActivity.mainService.popEidList(vardiesList);
        }

        userLoginButton.setOnClickListener(v -> {
            String usernametext = username.getText().toString();
            String passwordtext = password.getText().toString();
            boolean result;

            result = Credentials.isValid(usernametext,passwordtext,false, MainActivity.getCredentialsArrayList());

            if(result){
                eid = AdminLoginActivity.searchByUserName(usernametext, MainActivity.credentialsList);
                Username = AdminLoginActivity.searchByEid(eid, MainActivity.getErgazomenoiArrayList());
                Eidikotita = AdminLoginActivity.searchEidikotita(eid, MainActivity.getErgazomenoiArrayList());
                openActivityUWS();
            }
            else {
                Toast.makeText(UserLoginActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static String getEidikotita() {
        return Eidikotita;
    }

    public static String getUsername() {
        return Username;
    }

    public static int getEid(){return eid;}

    public void openActivityUWS(){
        Intent intent = new Intent(this, UserWelcomeActivity.class);
        startActivity(intent);
    }
}