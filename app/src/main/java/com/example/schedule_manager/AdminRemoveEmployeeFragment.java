package com.example.schedule_manager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.sql.Array;
import java.util.ArrayList;

public class AdminRemoveEmployeeFragment extends Fragment {

    Spinner eidSpinner;
    String[] examples = {
            "1",
            "2",
            "3",
            "4",
    };

    ArrayAdapter<String> adapterEid;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_remove_employee_fragment, container, false);

        eidSpinner = (Spinner) view.findViewById(R.id.spinnerEid);
        eidPopulateSpinner();


        return view;
    }


    private void eidPopulateSpinner() {
        adapterEid = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,examples);
        adapterEid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eidSpinner.setAdapter(adapterEid);
    }



}