package com.example.schedule_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminEditEmployeesActivity extends AppCompatActivity {

    private FrameLayout editAdminFrame;
    private BottomNavigationView editAdminNav;
    private AdminAddEmployeeFragment addEmployeeFragment;
    private AdminRemoveEmployeeFragment removeEmployeeFragment;
    private AdminEditInfoEmployeeFragment editInfoEmployeeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_edit_employees_activity);

        editAdminFrame = (FrameLayout) findViewById(R.id.edit_admin_frame);
        editAdminNav = (BottomNavigationView) findViewById(R.id.bottomEditAdminNavigationView);

        addEmployeeFragment = new AdminAddEmployeeFragment();
        removeEmployeeFragment = new AdminRemoveEmployeeFragment();
        editInfoEmployeeFragment = new AdminEditInfoEmployeeFragment();

        setFragment(addEmployeeFragment);

        editAdminNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.miAdminAddEmployee :
                        setFragment(addEmployeeFragment);
                        return true;
                    case R.id.miAdminDeleteEmployee :
                        setFragment(removeEmployeeFragment);
                        return true;
                    case R.id.miAdminEditInfoEmployee:
                        setFragment(editInfoEmployeeFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });



    }


    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.edit_admin_frame, fragment);
        fragmentTransaction.commit();
    }



}