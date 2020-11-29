package com.example.schedule_manager;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.FrameLayout;

import java.util.Calendar;


public class AdminSheduleFragment extends Fragment {

    private Button showAdminShedule;
    private Button editAdminShedule;
    private Button savesAdminShedule;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_shedule_fragment, container, false);


        showAdminShedule = (Button) view.findViewById(R.id.adminShowSheduleBtn);
        editAdminShedule = (Button) view.findViewById(R.id.adminEditSheduleBtn);
        savesAdminShedule = (Button) view.findViewById(R.id.adminSavesSheduleBtn);

        showAdminShedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShowAdminSheduleActivity();
            }
        });

        editAdminShedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditAdminSheduleActivity();
            }
        });

        savesAdminShedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSavesAdminSheduleActivity();
            }
        });


        return view;



    }


    public void openShowAdminSheduleActivity(){
        Intent intent = new Intent((getActivity()), AdminSheduleShow.class);
        startActivity(intent);
    }

    public void openEditAdminSheduleActivity(){
        Intent intent = new Intent((getActivity()), AdminSheduleShow.class);
        startActivity(intent);
    }

    public void openSavesAdminSheduleActivity(){
        Intent intent = new Intent((getActivity()), AdminSheduleShow.class);
        startActivity(intent);
    }







}