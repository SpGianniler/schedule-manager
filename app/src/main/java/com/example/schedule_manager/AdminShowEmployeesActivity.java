package com.example.schedule_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AdminShowEmployeesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_show_employees_activity);

        DataBaseAccess dba = DataBaseAccess.getInstance(getApplicationContext());
        ListView listView = findViewById(R.id.listView);
        ArrayList<Ergazomenoi> employeesList = (ArrayList<Ergazomenoi>) dba.getEveryone();

        AdminShowEmployeesListAdapter adapter = new AdminShowEmployeesListAdapter(this,0,employeesList);
        listView.setAdapter(adapter);

    }

}