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
    public static ArrayList<Adeies> adeiesList;
    public static HashMap<String, String> shiftsMap;
    public static List<Vardies> vardiesList;
    public static List<String> argeiesList;
    public static HashMap<Integer, String> eidikotitesMap;

    public static final String URL = "https://db-access-api.herokuapp.com";
    public final MainService mainService = new MainService();
    final AdeiesParseService adeiesParseService = new AdeiesParseService(MainActivity.this);
    final ArgiesParseService argiesParseService = new ArgiesParseService(MainActivity.this);
    final CredentialsParseService credentialsParseService = new CredentialsParseService(MainActivity.this);
    final VardiesParseService vardiesParseService = new VardiesParseService(MainActivity.this);
    final ErgazomenoiParseService ergazomenoiParseService = new ErgazomenoiParseService(MainActivity.this);


    public static HashMap<Integer, String> getEidikotitesMap() {
        return eidikotitesMap;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DataBaseAccess dba = DataBaseAccess.getInstance(this);

        callAdeiesService();
        callArgiesService();
        callCredService();
        callErgService();
        callVardService();



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



    private void callAdeiesService() {
        adeiesParseService.getLeavesData(new AdeiesParseService.AdeiesResponse() {
            @Override
            public void onError(String message) {
                Log.e("Callback Tag","Error");
            }

            @Override
            public void onResponse(ArrayList<Adeies> adArrayList) {
                adeiesList = adArrayList;
            }
        });
    }

    private void callArgiesService() {
        argiesParseService.getArgiesData(new ArgiesParseService.ArgiesResponse() {
            @Override
            public void onError(String message) {
                Log.e("Callback Tag","Error");
            }

            @Override
            public void onResponse(ArrayList<String> argList) {
                argeiesList = argList;
            }
        });
    }

    private void callCredService() {
        credentialsParseService.getCredData(new CredentialsParseService.CredentialsResponse() {
            @Override
            public void onError(String message) {
                Log.e("Callback Tag","Error");
            }

            @Override
            public void onResponse(ArrayList<Credentials> credArrayList) {
                credentialsList = credArrayList;
            }
        });
    }

    private void callVardService() {
        vardiesParseService.getVrdData(new VardiesParseService.VardiesResponse() {
            @Override
            public void onError(String message) {
                Log.e("Callback Tag","Error");
            }

            @Override
            public void onResponse(ArrayList<Vardies> vrdArrayList) {
                vardiesList = vrdArrayList;
                vardiesParseService.addShiftJobsData(new VardiesParseService.VardiesResponse() {
                    @Override
                    public void onError(String message) {
                        Log.e("Callback Tag","Error");
                    }

                    @Override
                    public void onResponse(ArrayList<Vardies> vrdArrayList) {
                        vardiesList = vrdArrayList;
                    }
                }, (ArrayList<Vardies>) vardiesList);

                vardiesParseService.addJobsData(new VardiesParseService.VardiesResponse() {
                    @Override
                    public void onError(String message) {
                        Log.e("Callback Tag","Error");
                    }

                    @Override
                    public void onResponse(ArrayList<Vardies> vrdArrayList) {
                        vardiesList = vrdArrayList;
                    }
                }, (ArrayList<Vardies>) vardiesList);
            }
        });
    }

    public void callErgService(){
        ergazomenoiParseService.getErgData(new ErgazomenoiParseService.ErgazomenoiResponse() {
            @Override
            public void onError(String message) {
                Log.e("Callback Tag","Error");
            }

            @Override
            public void onResponse(ArrayList<Ergazomenoi> ergArrayList) {
                ergazomenoiArrayList = ergArrayList;
                ergazomenoiParseService.addErgContractData(new ErgazomenoiParseService.ErgazomenoiResponse() {
                    @Override
                    public void onError(String message) {
                        Log.e("Callback Tag","Error");
                    }

                    @Override
                    public void onResponse(ArrayList<Ergazomenoi> ergArrayList) {
                        ergazomenoiArrayList = ergArrayList;
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
                    }
                }, ergazomenoiArrayList);
            }
        });
    }

    public void openActivityULA () {
        Intent intent = new Intent(this, UserLoginActivity.class);
        startActivity(intent);
    }


    public void openActivityALA () {
        Intent intent = new Intent(this, AdminLoginActivity.class);
        startActivity(intent);
    }


    public static ArrayList<Ergazomenoi> getErgazomenoiArrayList() {
        return ergazomenoiArrayList;
    }

    public static ArrayList<Credentials> getCredentialsArrayList() {
        return credentialsList;
    }

    public static HashMap<String, String> getShiftsMap() {
        return shiftsMap;
    }

    public static List<String> getArgeiesList() {
        return argeiesList;
    }

    public static List<Vardies> getVardiesList() {
        return vardiesList;
    }

    public static ArrayList<Adeies> getAdeiesList() {
        return adeiesList;
    }
}


