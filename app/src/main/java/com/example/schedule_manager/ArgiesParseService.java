package com.example.schedule_manager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArgiesParseService {

    public static final String GET_ALL_HOLIDAYS = MainActivity.URL + "/holidays/all";

    Context context;

    public ArgiesParseService(Context context) {
        this.context = context;
    }

    public interface ArgiesResponse {
        void onError(String message);

        void onResponse(ArrayList<String> argList);
    }

    public void getArgiesData(final ArgiesResponse argiesResponse){
        ArrayList<String> argiesList = new ArrayList<>();

        JsonArrayRequest jsonArgiesRequest = new JsonArrayRequest(
                Request.Method.GET,
                GET_ALL_HOLIDAYS,
                null,
                response -> {
                    try {
                        for(int i =0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);

                            argiesList.add(jsonObject.getString("date"));
                        }
                        argiesResponse.onResponse(argiesList);
                    }catch (JSONException e){
                        Log.e("addErgContractDataError","JSONException caught");
                    }
                },
                error -> {
                    Log.e("Array Response Error", error.toString());
                    argiesResponse.onError("Something's wrong! I can feel it!");
                }
        );
        RequestSingleton.getInstance(context).addToRequestQueue(jsonArgiesRequest);
    }
}
