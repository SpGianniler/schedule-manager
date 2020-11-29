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


        ListView listView = findViewById(R.id.listView);
        ArrayList<CustomListAdminEmployees> employeesList = new ArrayList<>();
        employeesList.add(new CustomListAdminEmployees("1","Xrhstos","Terzenidhs","12/12/12","xrhstos@gmail.com","545454","Mhxanikos"));
        employeesList.add(new CustomListAdminEmployees("2","Kwstas","Aughtidhs","14/12/1222","dsdsds@gmail.com","545454","Katharisths"));
        employeesList.add(new CustomListAdminEmployees("2","Kwstas","Aughtidhs","14/12/1222","dsdsds@gmail.com","545454","Katharisths"));
        employeesList.add(new CustomListAdminEmployees("2","Kwstas","Aughtidhs","14/12/1222","dsdsds@gmail.com","545454","Katharisths"));
        employeesList.add(new CustomListAdminEmployees("2","Kwstas","Aughtidhs","14/12/1222","dsdsds@gmail.com","545454","Katharisths"));
        employeesList.add(new CustomListAdminEmployees("2","Kwstas","Aughtidhs","14/12/1222","dsdsds@gmail.com","545454","Katharisths"));
        employeesList.add(new CustomListAdminEmployees("2","Kwstas","Aughtidhs","14/12/1222","dsdsds@gmail.com","545454","Katharisths"));
        employeesList.add(new CustomListAdminEmployees("2","Kwstas","Aughtidhs","14/12/1222","dsdsds@gmail.com","545454","Katharisths"));
        employeesList.add(new CustomListAdminEmployees("2","Kwstas","Aughtidhs","14/12/1222","dsdsds@gmail.com","545454","Katharisths"));
        employeesList.add(new CustomListAdminEmployees("2","Kwstas","Aughtidhs","14/12/1222","dsdsds@gmail.com","545454","Katharisths"));

        AdminShowEmployeesListAdapter adapter = new AdminShowEmployeesListAdapter(this,0,employeesList);
        listView.setAdapter(adapter);

    }

}