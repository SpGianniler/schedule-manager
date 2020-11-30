package com.example.schedule_manager.adminUI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.schedule_manager.R;

public class AdminProfileFragment extends Fragment {
    protected TextView username, specialty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_profile_fragment, container, false);
        username = (TextView) view.findViewById(R.id.Admin_UserName);
        username.setText(AdminLoginActivity.getUsername());

        specialty= (TextView) view.findViewById(R.id.Admin_Specialty);
        specialty.append(" "+AdminLoginActivity.getEidikotita());
        return view;
    }
//TODO: na kanw kai stis upoloipess klaseis na epistrefei view kai oxi inflater.inflate(R....)

}