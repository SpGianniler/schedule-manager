package com.example.schedule_manager.adminUI;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.schedule_manager.R;
import com.example.schedule_manager.userUI.UserProfileSettings;

public class AdminProfileFragment extends Fragment {
    protected TextView username, specialty;
    private ImageButton settingsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_profile_fragment, container, false);
        username = (TextView) view.findViewById(R.id.Admin_UserName);
        username.setText(AdminLoginActivity.getUsername());

        specialty= (TextView) view.findViewById(R.id.Admin_Specialty);
        specialty.append(" "+AdminLoginActivity.getEidikotita());
        settingsButton=(ImageButton) view.findViewById(R.id.settingsBTN);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySettings();
            }
        });
        return view;
    }
    public void openActivitySettings(){
        Intent intent = new Intent((getActivity()), AdminProfileSettings.class);
        startActivity(intent);
    }
}