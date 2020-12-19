package com.example.schedule_manager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.schedule_manager.adminUI.AdminLoginActivity;
import com.example.schedule_manager.userUI.UserLoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

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
    public static  HashMap<Integer, String> eidikotitesList;

    public static String URL = "http://192.168.56.1:8080";
    final ErgazomenoiParseService ergazomenoiParseService = new ErgazomenoiParseService(MainActivity.this);

    public static HashMap<Integer, String> getEidikotitesList() {
        return eidikotitesList;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseAccess dba = DataBaseAccess.getInstance(this);

        this.ergazomenoiArrayList = (ArrayList<Ergazomenoi>) dba.getEveryone();
        this.credentialsList = (ArrayList<Credentials>) dba.getCredentials();
        this.shiftsMap = dba.getShifts();
        this.vardiesList = dba.getVardies();
        this.eidikotitesList = dba.getEidikotites();
        Schedule.checkErg();
        ergazomenoiParseService.getErgData(new ErgazomenoiParseService.ErgazomenoiResponse() {
            @Override
            public void onError(String message) {
                Log.e("Callback Tag","Error");
            }

            @Override
            public void onResponse(ArrayList<Ergazomenoi> ergArrayList) {
                ergazomenoiArrayList = ergArrayList;
                Log.wtf("Stop",ergazomenoiArrayList.get(1).toString());
                ergazomenoiParseService.addErgContractData(new ErgazomenoiParseService.ErgazomenoiResponse() {
                    @Override
                    public void onError(String message) {
                        Log.e("Callback Tag","Error");
                    }

                    @Override
                    public void onResponse(ArrayList<Ergazomenoi> ergArrayList) {
                        ergazomenoiArrayList = ergArrayList;
                        Log.wtf("Stop",ergazomenoiArrayList.get(1).toString());
                    }
                }, ergazomenoiArrayList);

                ergazomenoiParseService.addErgCredData(new ErgazomenoiParseService.ErgazomenoiResponse() {
                    @Override
                    public void onError(String message) {
                        Log.e("Callback Tag","Error");
                    }

                    @Override
                    public void onResponse(ArrayList<Ergazomenoi> ergArrayList) {
                        ergazomenoiArrayList = ergArrayList;
                        Log.wtf("Stop",ergazomenoiArrayList.get(1).toString());
                    }
                }, ergazomenoiArrayList);
            }
        });

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {

                        if(task.isSuccessful()){
                            String token=task.getResult().getToken();
                            Log.d("TAG", "Token: "+token);
                        }else{
                            Log.d("TAG", "Token generation failed");
                        }

                    }
                });
        FirebaseMessaging.getInstance().subscribeToTopic("Programma");

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


