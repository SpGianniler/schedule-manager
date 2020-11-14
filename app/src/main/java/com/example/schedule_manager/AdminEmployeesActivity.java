package com.example.schedule_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminEmployeesActivity extends BaseActivity {
    private Button adminAddEmployeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_employees);
        System.out.println("2");
        adminAddEmployeeButton=(Button) findViewById(R.id.AddEmployeeButton);
        System.out.println("4");
        adminAddEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityAAEA();
            }

        });
        System.out.println("6");

    }

    public void openActivityAAEA(){

        Intent intent = new Intent(this,AdminAddEmployeeActivity.class);
        System.out.println("5");
        startActivity(intent);
    }
}