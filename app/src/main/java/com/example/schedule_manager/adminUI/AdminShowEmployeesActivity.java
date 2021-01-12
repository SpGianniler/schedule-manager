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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_show_employees_activity);

        List<Ergazomenoi> ergazomenoiList = MainActivity.getErgazomenoiArrayList();

        String[] examples = new String[ergazomenoiList.size()];
        for(int i = 0; i < ergazomenoiList.size();i++ ){
            examples[i] =(
                            ergazomenoiList.get(i).getOnoma()+" "+
                            ergazomenoiList.get(i).getEpitheto()+" "+
                            MainActivity.eidikotitesMap.get(Integer.parseInt(ergazomenoiList.get(i).getEidikotita()))+" "+
                            ergazomenoiList.get(i).getEvWres()+" "+
                            ergazomenoiList.get(i).getContract()
            );
        }
        ListView listView = findViewById(R.id.listView);

        AdminShowEmployeesListAdapter adapter = new AdminShowEmployeesListAdapter(this,0, (ArrayList<Ergazomenoi>) ergazomenoiList);
        listView.setAdapter(adapter);

    }

}