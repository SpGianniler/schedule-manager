package com.example.schedule_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AdminShowEmployeesActivity extends AppCompatActivity {

    String[] examples = {
            "Christos",
            "Apostolhs",
            "Spyros",
            "Basilhs",

    };

    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_show_employees_activity);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, examples);
        listView.setAdapter(adapter);
    }
}