package com.example.schedule_manager;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VardiesParseService {

    public static final String GET_ALL_SHIFTS = MainActivity.URL + "/shifts/all";
    public static final String ADD_SHIFT_JOBS = MainActivity.URL + "/shifts-jobs/all";
    public static final String ADD_JOBS_DETAILS = MainActivity.URL + "/jobs/all-jobs";

    Context context;

    public VardiesParseService(Context context) {
        this.context = context;
    }

    public interface VardiesResponse{
        void onError(String message);

        void onResponse(ArrayList<Vardies> vrdArrayList);
    }


    public void getVrdData(final VardiesResponse vardiesResponse){
        ArrayList<Vardies> vardiesArrayList = new ArrayList<>();

        JsonArrayRequest jsonVardRequest = new JsonArrayRequest(
                Request.Method.GET,
                GET_ALL_SHIFTS,
                null,
                response -> {
                    try{
                        for(int i=0; i < (response.length()); i++){
                            JSONObject jsonObject = response.getJSONObject(i);

                            int sid = jsonObject.getInt("sid");
                            String shift_name = jsonObject.getString("shift_name");

                            vardiesArrayList.add(new Vardies(sid,shift_name));
                        }
                        vardiesResponse.onResponse(vardiesArrayList);
                    } catch (JSONException e){
                        Log.e("getErgDataError","JSONException caught");
                    }
                },
                error -> {
                    Log.e("Array Response Error", error.toString());
                    vardiesResponse.onError("Something's wrong! I can feel it!");
                }
        );
        RequestSingleton.getInstance(context).addToRequestQueue(jsonVardRequest);
    }

    public void addShiftJobsData(final VardiesResponse vardiesResponse, ArrayList<Vardies> arrayList){

        JsonArrayRequest jsonShiftJobsRequest = new JsonArrayRequest(
                Request.Method.GET,
                ADD_SHIFT_JOBS,
                null,
                response -> {
                    try{
                        for(int i=0;i<(response.length());i++){
                            JSONObject jsonObject = response.getJSONObject(i);

                            for(Vardies v : arrayList){
                                if(v.sid == jsonObject.getInt("sid")){
                                    v.jid = jsonObject.getInt("jid");
                                    v.emploeesNo = jsonObject.getInt("employees_needed");
                                }
                            }
                            vardiesResponse.onResponse(arrayList);
                        }
                    } catch (JSONException e) {
                        Log.e("addVrdShiftJobError","JSONException caught");
                    }
                },
                error -> {
                    Log.e("Array Response Error", error.toString());
                    vardiesResponse.onError("Something's wrong! I can feel it!");
                }
        );
        RequestSingleton.getInstance(context).addToRequestQueue(jsonShiftJobsRequest);
    }

    public void addJobsData(final VardiesResponse vardiesResponse, ArrayList<Vardies> arrayList){

        JsonArrayRequest jsonJobsShData = new JsonArrayRequest(
                Request.Method.GET,
                ADD_JOBS_DETAILS,
                null,
                response -> {
                    try{
                        for(int i=0;i<(response.length());i++){
                            JSONObject jsonObject = response.getJSONObject(i);

                            for(Vardies v : arrayList){
                                if(v.jid == jsonObject.getInt("jid")){
                                    v.eidikotita = jsonObject.getString("job_name");
                                }
                            }
                            vardiesResponse.onResponse(arrayList);
                        }
                    } catch (JSONException e) {
                        Log.e("addVrdShiftJobError","JSONException caught");
                    }
                },
                error -> {
                    Log.e("Array Response Error", error.toString());
                    vardiesResponse.onError("Something's wrong! I can feel it!");
                }
        );
        RequestSingleton.getInstance(context).addToRequestQueue(jsonJobsShData);
    }
}


