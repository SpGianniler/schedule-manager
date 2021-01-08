package com.example.schedule_manager.adminUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.schedule_manager.Ergazomenoi;
import com.example.schedule_manager.MainActivity;
import com.example.schedule_manager.R;

import java.util.ArrayList;
import java.util.List;

public class AdminShowEmployeesActivity extends AppCompatActivity {


    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_show_employees_activity);

//        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());
//        dataBaseAccess.openDB();
        List<Ergazomenoi> ergazomenoiList = MainActivity.getErgazomenoiArrayList();
//        List<Ergazomenoi> ergazomenoiList = dataBaseAccess.getEveryone();
        String[] examples = new String[ergazomenoiList.size()];
        String eidik=null;
        for(int i = 0; i < ergazomenoiList.size();i++ ){
            for(int j =0; j<MainActivity.eidikotitesMap.size();j++){
                eidik = (MainActivity.eidikotitesMap.get(Integer.parseInt(ergazomenoiList.get(i).getEidikotita())));

            }
            examples[i] =(ergazomenoiList.get(i).getOnoma()+" "+ergazomenoiList.get(i).getEpitheto()+" "+eidik+" "+ergazomenoiList.get(i).getEvWres()+" "+ergazomenoiList.get(i).getContract());
//            examples[i] =(ergazomenoiList.get(i).getOnoma()+" "+ergazomenoiList.get(i).getEpitheto()+" "+ergazomenoiList.get(i).getEidikotita()+" "+ergazomenoiList.get(i).getEvWres()+" "+ergazomenoiList.get(i).getContract());
        }
//        dataBaseAccess.closeDB();

//        DataBaseAccess dba = DataBaseAccess.getInstance(getApplicationContext());
        ListView listView = findViewById(R.id.listView);
//        ArrayList<Ergazomenoi> employeesList = (ArrayList<Ergazomenoi>) dba.getEveryone();

        AdminShowEmployeesListAdapter adapter = new AdminShowEmployeesListAdapter(this,0, (ArrayList<Ergazomenoi>) ergazomenoiList);
        listView.setAdapter(adapter);

    }

}