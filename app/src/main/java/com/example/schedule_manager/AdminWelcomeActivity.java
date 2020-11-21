package com.example.schedule_manager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminWelcomeActivity extends BaseActivity {

  /*  private Button adminEmployeesButton;
    private Button adminScheduleButton;*/

    private BottomNavigationView adminMainNav;
    private FrameLayout adminMainFrame;

    private AdminEmployeesFragment employeesFragment;
    private AdminNotificationsFragment notificationsFragment;
    private AdminSheduleFragment sheduleFragment;
    private AdminProfileFragment adminProfileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);

        adminMainFrame = (FrameLayout) findViewById(R.id.main_admin_frame);
        adminMainNav = (BottomNavigationView) findViewById(R.id.bottomAdminNavigationView);

        employeesFragment = new AdminEmployeesFragment();
        notificationsFragment = new AdminNotificationsFragment();
        sheduleFragment = new AdminSheduleFragment();
        adminProfileFragment = new AdminProfileFragment();


        adminMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.miAdminShedule :
                        setFragment(sheduleFragment);
                        return true;
                    case R.id.miAdminEmployees :
                        setFragment(employeesFragment);
                        return true;
                    case R.id.miAdminNotifications :
                        setFragment(notificationsFragment);
                        return true;
                    case R.id.miAdminProfile :
                        setFragment(adminProfileFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });

        //setFragment();




        /*adminEmployeesButton=(Button) findViewById(R.id.AdminEmployeesButton);
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
        });*/
    }


    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_admin_frame, fragment);
        fragmentTransaction.commit();
    }


    /*public void openActivityAEA(){

        Intent intent = new Intent (this,AdminEmployeesActivity.class);
        startActivity(intent);

    }


    public void openActivityASA(){
        Intent intent = new Intent (this,AdminScheduleActivity.class);
        startActivity(intent);
    }*/

}