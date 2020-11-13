package com.example.schedule_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;





public class MainActivity extends BaseActivity {
    private Button userLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLoginButton = (Button) findViewById(R.id.userButton);
        userLoginButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openActivityULS();
           }
         });
    }

    public void openActivityULS(){
        Intent intent = new Intent(this,UserLoginScreen.class);
        startActivity(intent);
    }





}