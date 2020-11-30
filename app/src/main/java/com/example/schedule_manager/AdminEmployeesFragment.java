package com.example.schedule_manager;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class AdminEmployeesFragment extends Fragment {

    private Button showEmployeesAdminBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.admin_employees_fragment, container, false);

        showEmployeesAdminBtn = (Button) view.findViewById(R.id.showAdminEmployeesButton);

        showEmployeesAdminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShowEmployeesActivity();
            }
        });

        return view;
    }

    public void openShowEmployeesActivity(){
        Intent intent = new Intent((getActivity()), AdminShowEmployeesActivity.class);
        startActivity(intent);
    }




}