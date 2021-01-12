package com.example.schedule_manager.adminUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.schedule_manager.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminEditEmployeesActivity extends AppCompatActivity {

    private FrameLayout editAdminFrame;
    private BottomNavigationView editAdminNav;
    private AdminAddEmployeeFragment addEmployeeFragment;
    private AdminRemoveEmployeeFragment removeEmployeeFragment;
    private AdminEditInfoEmployeeFragment editInfoEmployeeFragment;
//    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_edit_employees_activity);

        editAdminFrame = (FrameLayout) findViewById(R.id.admin_employees_frame);
        editAdminNav = (BottomNavigationView) findViewById(R.id.bottomEditAdminNavigationView);

        addEmployeeFragment = new AdminAddEmployeeFragment();
        removeEmployeeFragment = new AdminRemoveEmployeeFragment();
        editInfoEmployeeFragment = new AdminEditInfoEmployeeFragment();

        setFragment(addEmployeeFragment);

        editAdminNav.setOnNavigationItemSelectedListener(item -> {
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
        });



    }


    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.admin_employees_frame, fragment);
        fragmentTransaction.commit();
    }

//    public Context getContext(){
//        return context;
//    }



}