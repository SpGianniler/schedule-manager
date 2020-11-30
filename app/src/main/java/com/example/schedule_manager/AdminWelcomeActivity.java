package com.example.schedule_manager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminWelcomeActivity extends BaseActivity {

    private BottomNavigationView adminMainNav;
    private FrameLayout adminMainFrame;
    private AdminEmployeesFragment employeesFragment;
    private AdminNotificationsFragment notificationsFragment;
    private AdminSheduleFragment sheduleFragment;
    private AdminProfileFragment adminProfileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_welcome_activity);

        adminMainFrame = (FrameLayout) findViewById(R.id.admin_frame);
        adminMainNav = (BottomNavigationView) findViewById(R.id.bottomAdminNavigationView);

        employeesFragment = new AdminEmployeesFragment();
        notificationsFragment = new AdminNotificationsFragment();
        sheduleFragment = new AdminSheduleFragment();
        adminProfileFragment = new AdminProfileFragment();

        setFragment(sheduleFragment);


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

    }


    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.admin_frame, fragment);
        fragmentTransaction.commit();
    }

}