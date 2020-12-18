package com.example.schedule_manager.adminUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.schedule_manager.R;

import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class AdminSheduleShowTable extends AppCompatActivity {

    Spinner savedShedules;
    Date[] dates = {

    };

    Calendar c = Calendar.getInstance();




    ArrayAdapter<Date> adapterEid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_shedule_show_table_activity);

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);


        /*horizontalCalendar.setRange(Calendar startDate, Calendar endDate);*/

        //convert
        /*Calendar calendar = Calendar.getInstance();
        Date newDate = calendar.setTime(date);*/



        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                //do something
            }
        });


    }
}