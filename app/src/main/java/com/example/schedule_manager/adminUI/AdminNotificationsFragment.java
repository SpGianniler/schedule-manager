package com.example.schedule_manager.adminUI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.schedule_manager.Adeies;
import com.example.schedule_manager.MainActivity;
import com.example.schedule_manager.R;

public class AdminNotificationsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_notifications_fragment, container, false);
        TextView adeies;
        adeies = (TextView) view.findViewById(R.id.notificationsFragment);
        adeies.setText("Αιτήσεις Αδειών\n\n");
        for(Adeies adeia : MainActivity.getAdeiesList()){
            adeies.append("Date: "+adeia.getDate()+"\neid: "+adeia.getEid()+"\nDuration: "+adeia.getDuration()+"\nReason: "+adeia.getReason()+"\n\n");
        }
        return view;
    }
}