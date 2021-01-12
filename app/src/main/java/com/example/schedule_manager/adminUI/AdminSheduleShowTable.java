package com.example.schedule_manager.adminUI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.schedule_manager.R;

import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class AdminSheduleShowTable extends AppCompatActivity {

    ListView listView;
    String vardies[] = {"Prwi","Apogeuma", "Vrady"};
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

        listView = findViewById(R.id.listshedule);

        MyAdapterShedule adapter = new MyAdapterShedule(this,vardies);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();


        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                listView.setAdapter(adapter);
            }
        });


    }

    class MyAdapterShedule extends ArrayAdapter<String> {
       Context context;
        String vardies[];

        MyAdapterShedule (Context c,String vardia[]){
            super(c, R.layout.custom_admin_shedule_show_list, R.id.vardia,vardia);
            this.context = c;
            this.vardies = vardia;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutinflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutinflater.inflate(R.layout.custom_admin_shedule_show_list, parent,false);
            TextView var = row.findViewById(R.id.vardia);

            var.setText(vardies[position]);

            return row;
        }
    }


}