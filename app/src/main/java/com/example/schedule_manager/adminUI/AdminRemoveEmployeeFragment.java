package com.example.schedule_manager.adminUI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

public class AdminRemoveEmployeeFragment extends Fragment {

    Spinner eidSpinner;
    ArrayAdapter<String> adapterEid;
    EditText name,surname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_remove_employee_fragment, container, false);

        eidSpinner = (Spinner) view.findViewById(R.id.spinnerEid);
        eidPopulateSpinner();


        return view;
    }


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