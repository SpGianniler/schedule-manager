package com.example.schedule_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminScheduleActivity extends BaseActivity {
    private Button scheduleCreateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_schedule);


        scheduleCreateButton=(Button) findViewById(R.id.scheduleCreateButton);
        scheduleCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityASRA();
            }
        });
    }


    public void openActivityASRA(){
        Intent intent = new Intent (this,AdminScheduleResultActivity.class);
        startActivity(intent);
    }
}