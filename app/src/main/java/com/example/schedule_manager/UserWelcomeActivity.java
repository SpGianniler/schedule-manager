package com.example.schedule_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.BreakIterator;

public class UserWelcomeActivity extends BaseActivity {
    public EditText showedText;
    private Button userScheduleButton;
    private Button userEditButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome);

        showedText= (EditText) findViewById((R.id.userEditTextUserName));
        userScheduleButton =(Button) findViewById(R.id.userScheduleButton);
        showedText.setText(UserLoginActivity.getUsername());
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
        });
    }


    public void openActivityUSA(){
        Intent intent = new Intent(this,UserScheduleActivity.class);
        startActivity(intent);
    }

    public void openActivityUEA(){
        Intent intent = new Intent(this,UserEditActivity.class);
        startActivity(intent);
    }
}