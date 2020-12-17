package com.example.schedule_manager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.schedule_manager.adminUI.AdminLoginActivity;
import com.example.schedule_manager.userUI.UserLoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends BaseActivity {
    private Button userLoginButton;
    private Button adminLoginButton;
    public static ArrayList<Ergazomenoi> ergazomenoiArrayList = new ArrayList<>();
    public static ArrayList<Credentials> credentialsList;
    public static HashMap<String, String> shiftsMap;
    public static List<Vardies> vardiesList;
    public static String URL = "http://192.168.56.1:8080";
    final ErgazomenoiParseService ergazomenoiParseService = new ErgazomenoiParseService(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseAccess dba = DataBaseAccess.getInstance(this);
//        this.ergazomenoiArrayList = (ArrayList<Ergazomenoi>) dba.getEveryone();
        this.credentialsList = (ArrayList<Credentials>) dba.getCredentials();
        this.shiftsMap = dba.getShifts();
        this.vardiesList = dba.getVardies();

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

//        JsonObjectRequest objectRequest = new JsonObjectRequest(
//                Request.Method.GET,
//                (URL+"/jobs/job/1"),
//                null,
//                response -> {
//                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
////                    Log.e("Rest Response0 GET", response.toString());
//                    try {
//                        int jid = response.getInt("jid");
//                        String job_name = response.getString("job_name");
//                        Log.e("Rest Response0 GET", job_name + " " + jid);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                },
//                error -> Log.e("Rest Response0 Error",error.toString())
//        );
//        requestQueue.add(objectRequest);

//        JsonObjectRequest objectRequestErg = new JsonObjectRequest(
//                Request.Method.GET,
//                (URL+"/employees/employee/1"),
//                null,
//                response -> {
//                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
//                    Log.e("Rest Response1 GET", response.toString());
//                },
//                error -> Log.e("Rest Response1 Error",error.toString())
//        );
//        requestQueue.add(objectRequestErg);

//        JsonObjectRequest objectRequest1 = new JsonObjectRequest(
//                Request.Method.POST,
//                (URL+"/job/add"),
//                ergJson,
//                response -> {
//                    Toast.makeText(getApplicationContext(),ergJson.toString(),Toast.LENGTH_SHORT).show();
//                    Log.e("Rest Response PUT", response.toString());
//                },
//                error -> Log.e("Rest Response PUT",error.toString())
//        );
//        requestQueue.add(objectRequest1);
        List<Ergazomenoi> jsonResponses = new ArrayList<>();



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


