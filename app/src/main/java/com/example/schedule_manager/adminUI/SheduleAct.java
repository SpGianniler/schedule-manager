package com.example.schedule_manager.adminUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.util.Pair;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.schedule_manager.DataBaseAccess;
import com.example.schedule_manager.Ergazomenoi;
import com.example.schedule_manager.MainActivity;
import com.example.schedule_manager.R;
import com.example.schedule_manager.RequestSingleton;
import com.example.schedule_manager.Schedule;
import com.google.android.material.datepicker.MaterialDatePicker;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
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
    protected Date first,second;
    private TextView programmaText;
    public static final String POST_SCHEDULE = MainActivity.URL+"/schedule/add";
    private ArrayList<Schedule> programma;

    protected String firDate;
    protected String secDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_shedule_activity);

        pickButton = (Button) findViewById(R.id.pick_range_btn);
        sheduleGenerateBtn = (Button) findViewById(R.id.generate_btn);
        firstDate = (TextView) findViewById(R.id.UserSettingsDOBText);
        secondDate = (TextView) findViewById(R.id.secondDate);
        programmaText = (TextView) findViewById(R.id.textView10);
        programmaText.setMovementMethod(new ScrollingMovementMethod());


        //MaterialDatePicker
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Select date range");
        final MaterialDatePicker materialDatePicker = builder.build();

        pickButton.setOnClickListener(v -> materialDatePicker.show(getSupportFragmentManager(),"DATE_PICKER"));

        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
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

//              Display it by setText
            firstDate.setText("Start Date: "+ simpleFormat.format(startDate));
            secondDate.setText("End Date: "+ simpleFormat.format(endDate) );

            String fd = simpleFormat.format(startDate);
            String sd = simpleFormat.format(endDate);
            setFirDate(fd);
            setSecDate(sd);
            setFirst(startDate);
            setSecond(endDate);
            rangeDateString();
        });
/**
 * Με το πάτηματου κουμπιού Generate Program δημιουργείται το πρόγραμμα και το αποθηκεύει στη βάση
 * δεδομένων
 */
        sheduleGenerateBtn.setOnClickListener(v -> {
            programmaText.setText("");
            if(Schedule.checkErg()) {
                programma = Schedule.onCreate(getRangeDate(), firDate);
                if (programma != null) {
                    DataBaseAccess dba = DataBaseAccess.getInstance(this);
                    for (int i = 0; i < programma.size(); i++) {
                        if (i % 18 == 0 && i != 0) {
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
                        ArrayList<String> arg = (ArrayList<String>) MainActivity.getArgeiesList();
                        if(arg!=null){
                            for (String argia : arg) {
                                if (argia.equals(firDate)) {
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
                            }
                        }
                        programma.get(i).setDate(firDate);
                        programmaText.append(programma.get(i).getDate()+" "+programma.get(i).getVardia() + " " + programma.get(i).getOnoma() + " " + programma.get(i).getEpitheto() + " " + programma.get(i).getEidikothta() + "\n");
                        int eid = 0;
                        for (Ergazomenoi erg : MainActivity.getErgazomenoiArrayList()) {
                            if (erg.getEpitheto().equals(programma.get(i).getEpitheto()) && erg.getOnoma().equals(programma.get(i).getOnoma())) {
                                eid = erg.getErg_id();
                            }
                        }
                        JSONObject jsonSchedule = new JSONObject();
                        try{
                            jsonSchedule.put("date",firDate);
                            jsonSchedule.put("shift_name",programma.get(i).getVardia());
                            jsonSchedule.put("eid",eid);
                            jsonSchedule.put("schedule_name",firDate+"-"+secDate);
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }

                        JsonObjectRequest jsonSchedulePost = new JsonObjectRequest(
                                Request.Method.POST,
                                POST_SCHEDULE,
                                jsonSchedule,
                                response -> {
//                                    Toast.makeText(this, jsonSchedule.toString(), Toast.LENGTH_SHORT).show()
                                },
                                error -> Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show()
                        );
                        RequestSingleton.getInstance(this).addToRequestQueue(jsonSchedulePost);
                    }
                } else {
                    Toast.makeText(this, "Pick Date", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this,"Not Enough Employees",Toast.LENGTH_SHORT).show();
            }
            try {

                StringBuilder data = new StringBuilder();
                data.append("Ημέρα,Βάρδια,Όνομα,Επίθετο,Πόστο");
                for(Schedule s : programma){
                    data.append("\n").append(s.getDate()).append(",").append(s.getVardia()).append(",").append(s.getOnoma()).append(",").append(s.getEpitheto()).append(",").append(s.getEidikothta());
                }

                FileOutputStream outputStream = openFileOutput("Schedule.csv", Context.MODE_PRIVATE);
                outputStream.write(data.toString().getBytes());
                outputStream.close();

                Context context = getApplicationContext();
                File fileLocation = new File(getFilesDir(),"Schedule.csv");
                Uri path = FileProvider.getUriForFile(context,"com.example.schedule_manager.fileprovider",fileLocation);
                Intent fileIntent = new Intent(Intent.ACTION_SEND);
                fileIntent.setType("text/csv");
                fileIntent.putExtra(Intent.EXTRA_SUBJECT,"Schedule");
                fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                startActivity(Intent.createChooser(fileIntent,"Send mail"));

            } catch (Exception e) {
                System.out.println("exception :" + e.getMessage());
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

    /**
     * Η συγκεκριμένη συναρτηση μετατρέπει σε String και εμφανίζει τις ημερομηνίες που
     * επιλέχθηκαν
     * @return
     */
    public String rangeDateString(){
        String range;
        range = "Start:"+getFirDate()+"End:"+getSecDate();
        System.out.println(range);
        return range;
    }

    //Date Range Integer
    /**
     * H γκεκριμένη συνάρτηση μας υπολογίζει την αριθμητική διαφορά αναμεσα στις 2 ημερομηνίες που επιλέξαμε
     * @return
     */
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
