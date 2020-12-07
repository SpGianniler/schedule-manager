package com.example.schedule_manager.adminUI;


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
import java.util.HashSet;
import java.util.Set;
import android.widget.Spinner;

import com.example.schedule_manager.MainActivity;
import com.example.schedule_manager.R;
import com.example.schedule_manager.Vardies;

public class AdminAddEmployeeFragment extends Fragment {

    Spinner jidSpinner;

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