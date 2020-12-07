package com.example.schedule_manager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.schedule_manager.adminUI.AdminLoginActivity;
import com.example.schedule_manager.userUI.UserLoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    public static String URL = "http://192.168.56.1:8080";

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

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                (URL+"/jobs/job/1"),
                null,
                response -> {
                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                    Log.e("Rest Response GET", response.toString());
                },
                error -> Log.e("Rest Response GET",error.toString())
        );
        requestQueue.add(objectRequest);

        JsonObjectRequest objectRequestErg = new JsonObjectRequest(
                Request.Method.GET,
                (URL+"/employees/employee/1"),
                null,
                response -> {
                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                    Log.e("Rest Response GET", response.toString());
                },
                error -> Log.e("Rest Response GET",error.toString())
        );
        requestQueue.add(objectRequest);

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

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("data");
                        for(int i = 0; i < jsonArray.length(); i++){
                           JSONObject jsonObject = jsonArray.getJSONObject(i);
                           String first_name = jsonObject.getString("first_name");
                           String last_name = jsonObject.getString("last_name");
                           Ergazomenoi ergazomenoi = new Ergazomenoi(first_name,last_name,"test",0,"part",true);


                            jsonResponses.add(ergazomenoi);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                Throwable::printStackTrace
        );
        requestQueue.add(jsonObjectRequest);

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


