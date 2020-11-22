package com.example.schedule_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class AdminWelcomeActivity extends BaseActivity {

    private Button adminEmployeesButton;
    private Button adminScheduleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);


        /*text.setText("Ergazomenoi: ");
        List<Ergazomenoi> ergList = DataBaseAccess.getInstance(AdminWelcomeActivity.this).getEveryone();
        for (Ergazomenoi erg : ergList){
            text.append(erg.getEid()+" "+erg.getEpitheto()+" "+erg.getOnoma()+" "+erg.getEidikotita()+" "+erg.getEvWres()+" "+erg.getContract() +"\n");
        }*/
        adminEmployeesButton=(Button) findViewById(R.id.AdminEmployeesButton);
        adminEmployeesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityAEA();
            }
        });

        adminScheduleButton=(Button) findViewById(R.id.AdminScheduleButton);
        adminScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityASA();
            }
        });
    }


    public void openActivityAEA(){

        Intent intent = new Intent (this,AdminEmployeesActivity.class);
        startActivity(intent);

    }


    public void openActivityASA(){
        Intent intent = new Intent (this,AdminScheduleActivity.class);
        startActivity(intent);
    }

}