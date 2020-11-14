package com.example.schedule_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;





public class MainActivity extends BaseActivity {
    private Button userLoginButton;
    private Button adminLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLoginButton = (Button) findViewById(R.id.userButton);
        userLoginButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openActivityULA();
           }
         });


        adminLoginButton = (Button) findViewById(R.id.adminButton);
        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityALA();
            }
        });
    }


    public void openActivityULA(){
        Intent intent = new Intent(this, UserLoginActivity.class);
        startActivity(intent);
    }


    public void openActivityALA(){
        Intent intent = new Intent(this, AdminLoginActivity.class);
        startActivity(intent);
    }





}