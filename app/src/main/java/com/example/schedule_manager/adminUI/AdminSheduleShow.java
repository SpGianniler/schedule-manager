package com.example.schedule_manager.adminUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.schedule_manager.R;

public class AdminSheduleShow extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";
    private CalendarView calendarView;
    FrameLayout showThisDaySheduleFrame;
    ShowThisDaySheduleFragment showThisDaySheduleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_shedule_show_activity);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        showThisDaySheduleFrame = (FrameLayout) findViewById(R.id.adminShowThisDaySheduleFrame);
        showThisDaySheduleFragment = new ShowThisDaySheduleFragment();



        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Toast.makeText(AdminSheduleShow.this,"You have Selected : " + dayOfMonth +" / " + (month+1) + " / " + year, Toast.LENGTH_LONG).show();
            setFragment(showThisDaySheduleFragment);
        });




}


    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.adminShowThisDaySheduleFrame, fragment);
        fragmentTransaction.commit();
    }






}