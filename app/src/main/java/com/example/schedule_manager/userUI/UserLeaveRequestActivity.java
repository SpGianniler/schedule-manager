package com.example.schedule_manager.userUI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schedule_manager.DataBaseAccess;
import com.example.schedule_manager.MainActivity;
import com.example.schedule_manager.R;

import org.json.JSONException;
import org.json.JSONObject;

public class UserLeaveRequestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_request_leave_activity);
        TextView date,duration,reason;
        EditText ETdate,ETduration,ETreason;
        Button request_leave;
        date = (TextView) findViewById(R.id.UserLeaveDate);
        duration = (TextView) findViewById(R.id.UserLeaveDuration);
        reason = (TextView) findViewById(R.id.UserLeaveReason);
        ETdate = (EditText) findViewById(R.id.UserLeaveDateET);
        ETduration = (EditText) findViewById(R.id.UserLeaveDurationET);
        ETreason = (EditText) findViewById(R.id.UserLeaveReasonET);
        DataBaseAccess dba = DataBaseAccess.getInstance(this);
        request_leave = (Button) findViewById(R.id.user_request_leave_button);
        request_leave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String hmer = ETdate.getText().toString();
                int diar = Integer.valueOf(ETduration.getText().toString());
                String logos = ETreason.getText().toString();
                int eid = UserLoginActivity.getEid();

                dba.insertAdeia(hmer,diar,logos,eid);
               /* JSONObject json = null;
                try {
                    json.put("Date", ETdate.getText().toString());
                    json.put("Duration", ETduration.getText().toString());
                    json.put("Reason", ETreason.getText().toString());
                   // json.put("eid",geteid)
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }
        });
    }
}
