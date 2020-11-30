package com.example.schedule_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AdminShowEmployeesActivity extends AppCompatActivity {


    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_show_employees_activity);
        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());
        dataBaseAccess.openDB();
        List<Ergazomenoi> ergazomenoiList = dataBaseAccess.getEveryone();
        String[] examples = new String[ergazomenoiList.size()];
        for(int i = 0; i < ergazomenoiList.size();i++ ){
            examples[i] =(ergazomenoiList.get(i).getOnoma()+" "+ergazomenoiList.get(i).getEpitheto()+" "+ergazomenoiList.get(i).getEidikotita()+" "+ergazomenoiList.get(i).getEvWres()+" "+ergazomenoiList.get(i).getContract());
        }
        dataBaseAccess.closeDB();

        DataBaseAccess dba = DataBaseAccess.getInstance(getApplicationContext());
        ListView listView = findViewById(R.id.listView);
        ArrayList<Ergazomenoi> employeesList = (ArrayList<Ergazomenoi>) dba.getEveryone();

        AdminShowEmployeesListAdapter adapter = new AdminShowEmployeesListAdapter(this,0,employeesList);
        listView.setAdapter(adapter);

    }

}