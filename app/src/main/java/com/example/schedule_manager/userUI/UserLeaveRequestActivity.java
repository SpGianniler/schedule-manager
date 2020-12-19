package com.example.schedule_manager.userUI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
        request_leave = (Button) findViewById(R.id.user_request_leave_button);
        request_leave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                JSONObject json = null;
                try {
                    json.put("Date", date.getText());
                    json.put("Duration", duration.getText());
                    json.put("Reason", reason.getText());
                   // json.put("eid",geteid)
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.wtf("ADD",json.toString());
            }
        });
    }
}
