package com.example.schedule_manager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import java.text.DateFormatSymbols;
import java.util.ArrayList;

public class AdminAddEmployeeFragment extends Fragment {

    Spinner jidSpinner;
    String[] examples = {
        "Christos",
                "Apostolhs",
                "Spyros",
                "Basilhs",
    };
    ArrayAdapter<String> adapterJid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_add_employee_fragment, container, false);

        jidSpinner = (Spinner) view.findViewById(R.id.spinnerJid);
        jidPopulateSpinner();

        return view;
    }



    private void jidPopulateSpinner() {
        adapterJid = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,examples);
        adapterJid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jidSpinner.setAdapter(adapterJid);
    }
}