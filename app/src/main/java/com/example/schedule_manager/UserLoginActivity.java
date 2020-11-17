package com.example.schedule_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserLoginActivity extends BaseActivity {
    private Button userLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        userLoginButton = (Button) findViewById(R.id.userLoginButton);
        userLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityUWS();
            }
        });
    }



    public void openActivityUWS(){
        Intent intent = new Intent(this, UserWelcomeActivity.class);
        startActivity(intent);
    }
}