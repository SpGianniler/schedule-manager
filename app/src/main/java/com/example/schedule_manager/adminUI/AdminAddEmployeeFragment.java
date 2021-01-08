package com.example.schedule_manager.adminUI;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashSet;

import com.example.schedule_manager.MainActivity;
import com.example.schedule_manager.R;
import com.example.schedule_manager.Vardies;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminAddEmployeeFragment extends Fragment {

    Spinner jidSpinner;
    RadioButton is_admin, is_user;
    EditText name,surname,dateOfBirth;
    JSONObject json = new JSONObject();
    Button addButton;

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
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {
                    json.put("first_name", name.getText());
                    json.put("last_name", surname.getText());
                    json.put("BirthDate", dateOfBirth.getText());
                    if(is_admin.isChecked()) {
                        json.put("is_admin",1);
                    }
                    else{
                        json.put("is_admin",0);
                    }
                    int eidikotita = 0;
                    String eidik = (jidSpinner.getSelectedItem().toString());
                    for(int eidikotites : MainActivity.getEidikotitesMap().keySet()){
                        if(MainActivity.getEidikotitesMap().get(eidikotites).equals(eidik)){
                            eidikotita = eidikotites;
                        }
                    }
                    json.put("eidikotita",eidikotita);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            Log.wtf("ADD",json.toString());
            }
        });
        jidPopulateSpinner();

        return view;
    }

    public JSONObject getJson() {
        return json;
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