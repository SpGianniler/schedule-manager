package com.example.schedule_manager.adminUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.schedule_manager.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class SheduleAct extends AppCompatActivity {

    private Button pickButton;
    private TextView firstDate;
    private TextView secondDate;
    private Button sheduleGenerateBtn;
    Date first,second;

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
                firstDate.setText("Start Date : "+ simpleFormat.format(startDate));
                secondDate.setText("End Date:"+ simpleFormat.format(endDate) );

                String fd = simpleFormat.format(startDate);
                String sd = simpleFormat.format(endDate);
                setFirDate(fd);
                setSecDate(sd);
                setFirst(startDate);
                setSecond(endDate);
                rangeDateString();
            }

        });

        sheduleGenerateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //KWDIKAS GIA THN EMFANISH TOU SHEDULE
            }
        });

    }

    public void setFirst(Date first) {
        this.first = first;
    }

    public void setSecond(Date second) {
        this.second = second;
    }

    public Button getPickButton() {
        return pickButton;
    }

    public Date getFirst() {
        return first;
    }

    public Date getSecond() {
        return second;
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

    //String For Spinner in AdminSheduleShowTable
    public String rangeDateString(){
        String range;
        range = "Start:"+getFirDate()+"End:"+getSecDate();
        System.out.println(range);
        return range;
    }

    //Date Range Integer
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