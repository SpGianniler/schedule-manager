package com.example.schedule_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminScheduleResultActivity extends AppCompatActivity {
    private Button scheduleDetailsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_schedule_result);

        scheduleDetailsButton=(Button) findViewById(R.id.scheduleDetailsButton);
        scheduleDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityASDA();
            }
        });
    }

    public void openActivityASDA(){
        Intent intent = new Intent (this,AdminScheduleDetailsActivity.class);
        startActivity(intent);
    }
}