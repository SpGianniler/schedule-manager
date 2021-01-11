package com.example.schedule_manager.userUI;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.schedule_manager.MainActivity;
import com.example.schedule_manager.R;
import com.example.schedule_manager.RequestSingleton;

import org.json.JSONException;
import org.json.JSONObject;


public class UserLeaveRequestActivity extends AppCompatActivity {

    public static final String POST_ADEIES = MainActivity.URL+"/leaves/add";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_request_leave_activity);

        EditText ETdate,ETduration,ETreason;
        Button request_leave;
        ETdate = (EditText) findViewById(R.id.UserLeaveDateET);
        ETduration = (EditText) findViewById(R.id.UserLeaveDurationET);
        ETreason = (EditText) findViewById(R.id.UserLeaveReasonET);
        request_leave = (Button) findViewById(R.id.user_request_leave_button);

        request_leave.setOnClickListener(v -> {

            JSONObject jsonAdeies = new JSONObject();
            try {
                jsonAdeies.put("leave_date", ETdate.getText().toString());
                jsonAdeies.put("duration", Integer.parseInt(ETduration.getText().toString()));
                jsonAdeies.put("reason", ETreason.getText().toString());
                jsonAdeies.put("eid",UserLoginActivity.getEid());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest jsonAdeiesPost = new JsonObjectRequest(
                    Request.Method.POST,
                    POST_ADEIES,
                    jsonAdeies,
                    response -> Toast.makeText(getApplicationContext(), jsonAdeies.toString(), Toast.LENGTH_SHORT).show(),
                    error -> Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show()
            );
            RequestSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonAdeiesPost);
        });
    }
}
