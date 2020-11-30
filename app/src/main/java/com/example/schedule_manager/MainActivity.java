package com.example.schedule_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends BaseActivity {
    private Button userLoginButton;
    private Button adminLoginButton;
    public static ArrayList<Ergazomenoi> ergazomenoiArrayList;
    public static ArrayList<Credentials> credentialsList;
    public static HashMap<String, String> shiftsMap;
    public static List<Vardies> vardiesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseAccess dba = DataBaseAccess.getInstance(this);
        this.ergazomenoiArrayList = (ArrayList<Ergazomenoi>) dba.getEveryone();
        this.credentialsList = (ArrayList<Credentials>) dba.getCredentials();
        this.shiftsMap = dba.getShifts();
        this.vardiesList = dba.getVardies();
        //Schedule.onCreate();

        userLoginButton = (Button) findViewById(R.id.userButton);
        userLoginButton.setOnClickListener(v -> openActivityULA());


        adminLoginButton = (Button) findViewById(R.id.adminButton);
        adminLoginButton.setOnClickListener(v -> openActivityALA());
    }

    public void openActivityULA () {
        Intent intent = new Intent(this, UserLoginActivity.class);
        startActivity(intent);
    }


    public void openActivityALA () {
        Intent intent = new Intent(this, AdminLoginActivity.class);
        startActivity(intent);
    }

    static public ArrayList<Ergazomenoi> getErgazomenoiArrayList() {
        return ergazomenoiArrayList;
    }

    static public ArrayList<Credentials> getCredentialsArrayList() {
        return credentialsList;
    }

    public static HashMap<String, String> getShiftsMap() {
        return shiftsMap;
    }

    public static List<Vardies> getVardiesList() {
        return vardiesList;
    }
}


