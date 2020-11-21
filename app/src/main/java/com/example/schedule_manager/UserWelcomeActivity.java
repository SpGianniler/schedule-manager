package com.example.schedule_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserWelcomeActivity extends AppCompatActivity {
    /*private Button userScheduleButton;
    private Button userEditButton;*/

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private UserHomeFragment homeFragment;
    private UserNotificationsFragment notificationsFragment;
    private UserProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome);

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

/*


        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);

        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new UserHomeFragment()).commit();*/




       /* userScheduleButton =(Button) findViewById(R.id.userScheduleButton);
        userScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityUSA();
            }
        });
        userEditButton =(Button) findViewById(R.id.userEditButton);
        userEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityUEA();
            }
        });*/
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

   /* public void openActivityUSA(){
        Intent intent = new Intent(this,UserScheduleActivity.class);
        startActivity(intent);
    }

    public void openActivityUEA(){
        Intent intent = new Intent(this,UserEditActivity.class);
        startActivity(intent);
    }*/
}