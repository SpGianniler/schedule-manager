package com.example.schedule_manager.userUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.schedule_manager.R;
import com.example.schedule_manager.adminUI.AdminShowEmployeesActivity;

public class UserHomeFragment extends Fragment {
    Button adeia, programma;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_home_fragment, container, false); //checkit
        adeia = (Button) view.findViewById(R.id.buttonRequestAdeiaUSer);
        adeia.setOnClickListener(v -> openUserRequestLeaveActivity());
        programma = (Button) view.findViewById(R.id.buttonSeeScheduleUser);
        programma.setOnClickListener(v -> openUserSeeSqheduleActivity());
        return view;
    }
    public void openUserRequestLeaveActivity(){
        Intent intent = new Intent((getActivity()), UserLeaveRequestActivity.class);
        startActivity(intent);
    }
    public void openUserSeeSqheduleActivity(){
        Intent intent = new Intent((getActivity()), UserSeeSqheduleActivity.class);
        startActivity(intent);
    }
}
