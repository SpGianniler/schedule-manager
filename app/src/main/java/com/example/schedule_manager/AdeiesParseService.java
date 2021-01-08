package com.example.schedule_manager;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdeiesParseService {

    public static final String GET_ALL_LEAVES = MainActivity.URL + "/leaves/all";

    Context context;

    public AdeiesParseService(Context context) {
        this.context = context;
    }

    public interface AdeiesResponse {
        void onError(String message);

        void onResponse(ArrayList<Adeies> adArrayList);
    }

    public void getLeavesData(final AdeiesResponse adeiesResponse){
        ArrayList<Adeies> adeiesArrayList = new ArrayList<>();

        JsonArrayRequest jsonAdeiesRequest = new JsonArrayRequest(
                Request.Method.GET,
                GET_ALL_LEAVES,
                null,
                response -> {
                    try {
                        for(int i =0; i < response.length(); i++){
                            JSONObject jsonObject = response.getJSONObject(i);

                            adeiesArrayList.add(new Adeies(
                                    jsonObject.getString("leave_date"),
                                    jsonObject.getInt("eid"),
                                    jsonObject.getInt("duration"),
                                    jsonObject.getString("reason")));
                        }
                        adeiesResponse.onResponse(adeiesArrayList);
                    }catch (JSONException e){
                        Log.e("addErgContractDataError","JSONException caught");
                    }
                },
                error -> {
                    Log.e("Array Response Error", error.toString());
                    adeiesResponse.onError("Something's wrong! I can feel it!");
                }
        );
        RequestSingleton.getInstance(context).addToRequestQueue(jsonAdeiesRequest);
    }
}
