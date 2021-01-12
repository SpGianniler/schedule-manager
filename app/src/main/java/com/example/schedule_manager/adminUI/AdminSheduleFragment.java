package com.example.schedule_manager.adminUI;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.schedule_manager.R;


public class AdminSheduleFragment extends Fragment {

    private Button showAdminShedule;
    private Button editAdminShedule;
    private Button savesAdminShedule;
    private Button generateAdminShedule;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_shedule_fragment, container, false);

        generateAdminShedule = (Button) view.findViewById(R.id.adminGenerateSheduleButton);
        editAdminShedule = (Button) view.findViewById(R.id.adminEditSheduleBtn);
        savesAdminShedule = (Button) view.findViewById(R.id.adminSavesSheduleBtn);
        showAdminShedule = (Button) view.findViewById((R.id.adminShowSheduleButton));



        showAdminShedule.setOnClickListener(v -> openShowAdminSheduleActivity());

        editAdminShedule.setOnClickListener(v -> openEditAdminSheduleActivity());

        savesAdminShedule.setOnClickListener(v -> openSavesAdminSheduleActivity());

        generateAdminShedule.setOnClickListener(v -> openGenerateAdminSheduleActivity());
        return view;
    }


    public void openGenerateAdminSheduleActivity(){
        Intent intent = new Intent((getActivity()), SheduleAct.class );
        startActivity(intent);
    }

    public void openShowAdminSheduleActivity(){
        Intent intent = new Intent((getActivity()), AdminSheduleShowTable.class);
        startActivity(intent);
    }

    public void openEditAdminSheduleActivity(){
        Intent intent = new Intent((getActivity()), AdminSheduleEdit.class);
        startActivity(intent);
    }

    public void openSavesAdminSheduleActivity(){
        Intent intent = new Intent((getActivity()), AdminSheduleSaves.class);
        startActivity(intent);
    }







}