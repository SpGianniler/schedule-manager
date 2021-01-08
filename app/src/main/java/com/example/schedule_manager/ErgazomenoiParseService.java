package com.example.schedule_manager;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ErgazomenoiParseService {

    public static final String GET_ALL_EMPLOYEES = MainActivity.URL + "/employees/all";
    public static final String ADD_CONTRACT_DETAILS = MainActivity.URL + "/contracts/all";
    public static final String ADD_CREDENTIAL_DETAILS = MainActivity.URL + "/credentials/all";

    Context context;

    public ErgazomenoiParseService(Context context) {
        this.context = context;
    }

    public interface ErgazomenoiResponse {
        void onError(String message);

        void onResponse(ArrayList<Ergazomenoi> ergArrayList);
    }


    public void getErgData(final ErgazomenoiResponse ergazomenoiResponse) {
        ArrayList<Ergazomenoi> ergazomenoiArrayList = new ArrayList<>();

        JsonArrayRequest jsonErgazRequest = new JsonArrayRequest(
                Request.Method.GET,
                GET_ALL_EMPLOYEES,
                null,
                response -> {
                    try {
                        for(int i = 0; i < (response.length()); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);

                            int eid = jsonObject.getInt("eid");
                            String first_name = jsonObject.getString("first_name");
                            String last_name = jsonObject.getString("last_name");
                            String job_name = Integer.toString(jsonObject.getInt("jid"));

                            ergazomenoiArrayList.add(new Ergazomenoi(eid,first_name,last_name,job_name));
//                            Log.e("Array tag 1",ergazomenoiArrayList.get(i).onoma);
                        }
                        ergazomenoiResponse.onResponse(ergazomenoiArrayList);
                    } catch (JSONException e) {
                        Log.e("getErgDataError","JSONException caught");
                    }
                },
                error -> {
                    Log.e("Array Response Error", error.toString());
                    ergazomenoiResponse.onError("Something's wrong! I can feel it!");
                }
        );
        RequestSingleton.getInstance(context).addToRequestQueue(jsonErgazRequest);
    }

    public void addErgContractData(final ErgazomenoiResponse ergazomenoiResponse, ArrayList<Ergazomenoi> arrayList) {

        JsonArrayRequest jsonErgCtrRequest = new JsonArrayRequest(
                Request.Method.GET,
                ADD_CONTRACT_DETAILS,
                null,
                response -> {
                    try {
                        for(int i = 0; i < (response.length()); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);

                            for( Ergazomenoi x : arrayList){
                                if(x.erg_id == jsonObject.getInt("eid")){
                                    x.contract = jsonObject.getString("type");
                                    x.evWres = jsonObject.getInt("hours");
                                }
                            }
                        }
                        ergazomenoiResponse.onResponse(arrayList);
                    } catch (JSONException e) {
                        Log.e("addErgContractDataError","JSONException caught");
                    }
                },
                error -> {
                    Log.e("Array Response Error", error.toString());
                    ergazomenoiResponse.onError("Something's wrong! I can feel it!");
                }
        );
        RequestSingleton.getInstance(context).addToRequestQueue(jsonErgCtrRequest);
    }

    public void addErgCredData(final ErgazomenoiResponse ergazomenoiResponse, ArrayList<Ergazomenoi> arrayList) {

        JsonArrayRequest jsonErgCredRequest = new JsonArrayRequest(
                Request.Method.GET,
                ADD_CREDENTIAL_DETAILS,
                null,
                response -> {
                    try {
                        for(int i = 0; i < (response.length()); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);

                            for( Ergazomenoi x : arrayList){
                                if(x.erg_id == jsonObject.getInt("eid")){
                                    x.is_admin = jsonObject.getInt(("is_admin") )==1;
                                }
                            }
                        }
                        ergazomenoiResponse.onResponse(arrayList);
                    } catch (JSONException e) {
                        Log.e("addErgContractDataError","JSONException caught");
                    }
                },
                error -> {
                    Log.e("Array Response Error", error.toString());
                    ergazomenoiResponse.onError("Something's wrong! I can feel it!");
                }
        );
        RequestSingleton.getInstance(context).addToRequestQueue(jsonErgCredRequest);
    }

}
