package com.example.schedule_manager.adminUI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.ref.Reference;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.schedule_manager.Ergazomenoi;
import com.example.schedule_manager.MainActivity;
import com.example.schedule_manager.R;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminRemoveEmployeeFragment extends Fragment {

    Spinner eidSpinner;
    ArrayAdapter<String> adapterEid;
    EditText name,surname;
    JSONObject json = new JSONObject();
    Button remButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_remove_employee_fragment, container, false);

        eidSpinner = (Spinner) view.findViewById(R.id.spinnerEid);
        name = (EditText) view.findViewById(R.id.firstNameAdminEditEmployees);
        surname = (EditText) view.findViewById(R.id.lastNameAdminAddEmployees);
        remButton = (Button)view.findViewById(R.id.button);
        remButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {

                    String eid = eidSpinner.getSelectedItem().toString();
                    eid = eid.substring(0,2);
                    if(eid.contains(" ")){
                        eid = eid.substring(0,1);
                    }
                    json.put("eid", eid);

                    json.put("first_name", name.getText());
                    json.put("last_name", surname.getText());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


        eidPopulateSpinner();


        return view;
    }

    public JSONObject getJson() {
        return json;
    }

    /**
     * Gemizei to spiner me tous kataxvrhmenous ergazomenous
     */
    private void eidPopulateSpinner() {
        int i =0 ;
        ArrayList<Ergazomenoi> ergazomenoiArrayList = MainActivity.getErgazomenoiArrayList();
        String[] examples2 = new String[ergazomenoiArrayList.size()];
        for(Ergazomenoi erg: ergazomenoiArrayList){
             examples2[i] = erg.getErg_id()+" "+erg.getOnoma()+" "+erg.getEpitheto();
             i++;
        }

        adapterEid = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,examples2);
        adapterEid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eidSpinner.setAdapter(adapterEid);
    }
}