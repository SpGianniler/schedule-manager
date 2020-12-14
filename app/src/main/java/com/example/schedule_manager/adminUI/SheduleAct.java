package com.example.schedule_manager.adminUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.schedule_manager.R;
import com.example.schedule_manager.Schedule;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class SheduleAct extends AppCompatActivity {

    private Button pickButton;
    private TextView firstDate;
    private TextView secondDate;
    private Button sheduleGenerateBtn;
    private TextView programmaText;

    protected String firDate;
    protected String secDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_shedule_activity);

        pickButton = (Button) findViewById(R.id.pick_range_btn);
        sheduleGenerateBtn = (Button) findViewById(R.id.generate_btn);
        firstDate = (TextView) findViewById(R.id.firstDate);
        secondDate = (TextView) findViewById(R.id.secondDate);
        programmaText = (TextView) findViewById(R.id.textView10);
        programmaText.setMovementMethod(new ScrollingMovementMethod());

        //MaterialDatePicker
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Select date range");
        final MaterialDatePicker materialDatePicker = builder.build();

        pickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(),"DATE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
//                Get the selected DATE RANGE
                Pair selectedDates = (Pair) materialDatePicker.getSelection();
//              then obtain the startDate & endDate from the range
                final Pair<Date, Date> rangeDate = new Pair<>(new Date((Long) selectedDates.first), new Date((Long) selectedDates.second));
//              assigned variables
                Date startDate = rangeDate.first;
                Date endDate = rangeDate.second;
//              Format the dates in ur desired display mode
                SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat simpleDay = new SimpleDateFormat("dd");
               // SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");

//              Display it by setText
                firstDate.setText("Start Date: "+ simpleFormat.format(startDate));
                secondDate.setText("End Date: "+ simpleFormat.format(endDate) );

                String fd = simpleFormat.format(startDate);
                String sd = simpleFormat.format(endDate);
                setFirDate(fd);
                setSecDate(sd);
            }

        });

        sheduleGenerateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programmaText.setText("");
                ArrayList<Schedule> programma = Schedule.onCreate(getRangeDate());
                for(int i=0; i < programma.size(); i++) {
                    if(i % 18 == 0 && i != 0){
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Calendar c = Calendar.getInstance();
                        try {
                            c.setTime(sdf.parse(firDate));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        c.add(Calendar.DATE, 1);  // number of days to add
                        firDate = sdf.format(c.getTime());
                    }
                    programmaText.append(firDate + " ");
                    programmaText.append(programma.get(i).getVardia() + " "+ programma.get(i).getOnoma() + " "+programma.get(i).getEpitheto() + " "+programma.get(i).getEidikothta() +"\n");
                }
            }
        });

    }


    public void setFirDate(String firDate) {
        this.firDate = firDate;
    }

    public void setSecDate(String secDate) {
        this.secDate = secDate;
    }

    public String getFirDate() {
        return firDate;
    }

    public String getSecDate() {
        return secDate;
    }

    public int getRangeDate(){
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateOne = null;
        Date dateTwo = null;

        try {
            dateOne = simpleFormat.parse(firDate);
            dateTwo = simpleFormat.parse(secDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar start = Calendar.getInstance();
        start.setTime(dateOne);
        Calendar end = Calendar.getInstance();
        end.setTime(dateTwo);

        long difference_In_Time =  dateTwo.getTime() - dateOne.getTime();
        long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
        difference_In_Days = difference_In_Days + 1;
        int dayRange=(int)difference_In_Days;
        return dayRange;
        }

    }