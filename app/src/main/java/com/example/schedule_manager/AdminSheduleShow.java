package com.example.schedule_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.Date;

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



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(AdminSheduleShow.this,"You have Selected : " + dayOfMonth +" / " + (month+1) + " / " + year, Toast.LENGTH_LONG).show();
                setFragment(showThisDaySheduleFragment);
            }
        });




}


    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.adminShowThisDaySheduleFrame, fragment);
        fragmentTransaction.commit();
    }






}