package com.example.schedule_manager;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CredentialsParseService {

    public static final String GET_ALL_CREDENTIALS = MainActivity.URL + "/credentials/all";

    Context context;

    public CredentialsParseService(Context context) {
        this.context = context;
    }

    public interface CredentialsResponse {
        void onError(String message);

        void onResponse(ArrayList<Credentials> credArrayList);
    }

    public void getCredData(final CredentialsResponse credentialsResponse){
        ArrayList<Credentials> credentialsArrayList = new ArrayList<>();

        JsonArrayRequest jsonCredRequest = new JsonArrayRequest(
                Request.Method.GET,
                GET_ALL_CREDENTIALS,
                null,
                response -> {
                    try {
                        for(int i=0; i<(response.length());i++){
                            JSONObject jsonObject = response.getJSONObject(i);

                            credentialsArrayList.add(new Credentials(
                                    jsonObject.getInt("eid"),
                                    jsonObject.getInt("is_admin")==1,
                                    jsonObject.getString("username"),
                                    jsonObject.getString("password")
                            ));
                        }
                        credentialsResponse.onResponse(credentialsArrayList);
                    } catch (JSONException e) {
                        Log.e("getErgDataError","JSONException caught");
                    }
                },
                error -> {
                    Log.e("Array Response Error", error.toString());
                    credentialsResponse.onError("Something's wrong! I can feel it!");
                }
        );
        RequestSingleton.getInstance(context).addToRequestQueue(jsonCredRequest);
    }
}
