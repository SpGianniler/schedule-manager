package com.example.schedule_manager.userUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.schedule_manager.R;
import com.example.schedule_manager.userUI.UserHomeFragment;
import com.example.schedule_manager.userUI.UserNotificationsFragment;
import com.example.schedule_manager.userUI.UserProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserWelcomeActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private UserHomeFragment homeFragment;
    private UserNotificationsFragment notificationsFragment;
    private UserProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_welcome_activity);

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        homeFragment = new UserHomeFragment();
        notificationsFragment = new UserNotificationsFragment();
        profileFragment = new UserProfileFragment();

        setFragment(homeFragment);



        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.miHome :
                        setFragment(homeFragment);
                        return true;
                    case R.id.miNotifications :
                        setFragment(notificationsFragment);
                        return true;
                    case R.id.miProfile :
                        setFragment(profileFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }


}