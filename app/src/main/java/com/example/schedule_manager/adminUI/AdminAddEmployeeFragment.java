package com.example.schedule_manager.adminUI;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.example.schedule_manager.MainActivity;
import com.example.schedule_manager.R;
import com.example.schedule_manager.RequestSingleton;
import com.example.schedule_manager.Vardies;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminAddEmployeeFragment extends Fragment {

    Spinner jidSpinner;
    RadioButton is_admin, is_user;
    EditText name,surname,dateOfBirth;
    public static boolean needUpdate = false;
    JSONObject jsonEmployee = new JSONObject();
    JSONObject jsonCred = new JSONObject();
    Button addButton;
    MainActivity mainActivity = new MainActivity();
    public static final String POST_EMPLOYEE = MainActivity.URL+"/employees/add";
    public static final String POST_CREDENTIALS = MainActivity.URL+"/credentials/add";

    ArrayAdapter<String> adapterJid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_add_employee_fragment, container, false);

        jidSpinner = (Spinner) view.findViewById(R.id.spinnerJid);
        name = (EditText) view.findViewById(R.id.firstNameAdminEditEmployees);
        surname = (EditText) view.findViewById(R.id.lastNameAdminAddEmployees);
        dateOfBirth = (EditText) view.findViewById(R.id.birthdayAdminAddEmployees);
        is_admin = (RadioButton) view.findViewById(R.id.radioMaleBtnAdminAddEmployee);
        is_user =  (RadioButton) view.findViewById(R.id.radioFemaleBtnAdminAddEmployee);
        addButton = (Button) view.findViewById(R.id.button);
        addButton.setOnClickListener(v -> {

            try {
                jsonEmployee.put("first_name", name.getText());
                jsonEmployee.put("last_name", surname.getText());
                jsonEmployee.put("birth_date", dateOfBirth.getText());
                if(is_admin.isChecked()) {
                    jsonCred.put("is_admin",1);
                }
                else{
                    jsonCred.put("is_admin",0);
                }
                jsonCred.put("username","test"+ (MainActivity.getErgazomenoiArrayList().size() + 1));
                jsonCred.put("password",surname.getText());
                jsonCred.put("eid",MainActivity.getErgazomenoiArrayList().size()+1);
                int eidikotita = 0;
                String eidik = (jidSpinner.getSelectedItem().toString());
                for(int eidikotites : MainActivity.getEidikotitesMap().keySet()){
                    if(MainActivity.getEidikotitesMap().get(eidikotites).equals(eidik)){
                        eidikotita = eidikotites;
                    }
                }
                jsonEmployee.put("jid",eidikotita);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            RequestFuture<JSONObject> future = RequestFuture.newFuture();
            JsonObjectRequest jsonEmployeePost = new JsonObjectRequest(Request.Method.POST,
                    POST_EMPLOYEE,
                    jsonEmployee,
                    future,
                    future);
            RequestSingleton.getInstance(getContext()).addToRequestQueue(jsonEmployeePost);

            try {
                JSONObject response = future.get(1, TimeUnit.SECONDS); // this will block

            } catch (InterruptedException | ExecutionException e) {
                // exception handling
            } catch (TimeoutException e) {
                Toast.makeText(getContext(), jsonEmployee.toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            mainActivity.callErgService();
//            if (mainActivity.ergazomenoiArrayList.size() != length){
//                JsonObjectRequest jsonCredentialPost = new JsonObjectRequest(
//                        Request.Method.POST,
//                        POST_CREDENTIALS,
//                        jsonCred,
//                        response -> Toast.makeText(getContext(), jsonCred.toString(), Toast.LENGTH_SHORT).show(),
//                        error -> Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show()
//                );
//                RequestSingleton.getInstance(getContext()).addToRequestQueue(jsonCredentialPost);
//            }


//            JsonObjectRequest jsonEmployeePost = new JsonObjectRequest(
//                    Request.Method.POST,
//                    POST_EMPLOYEE,
//                    jsonEmployee,
//                    response -> Toast.makeText(getContext(), jsonEmployee.toString(), Toast.LENGTH_SHORT).show(),
//                    error -> Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show()
//            );
//            RequestSingleton.getInstance(getContext()).addToRequestQueue(jsonEmployeePost);



        });
        jidPopulateSpinner();



        return view;
    }

    public JSONObject getJsonEmployee() {
        return jsonEmployee;
    }

    /**
     * η μέθοδος αυτή γεμίζει το spinner με όλες τις διαφορετικές ειδικότητες
     */
    private void jidPopulateSpinner() {

             int i =0 ;
             ArrayList<Vardies> vardies = (ArrayList<Vardies>) MainActivity.getVardiesList();
             HashSet<String> vardiesSet = new HashSet<>();
             for(Vardies vrd: vardies){
                  vardiesSet.add(vrd.getEidikotita());
             }
             String[] examples2 = new String[vardiesSet.size()];
             for(String eidik : vardiesSet){
                 examples2[i] = eidik;
                 i++;
             }
         adapterJid = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,examples2);

        adapterJid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jidSpinner.setAdapter(adapterJid);
    }
}