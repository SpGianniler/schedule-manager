package com.example.schedule_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;



public class MainActivity extends BaseActivity {
    private Button userLoginButton;
    private Button adminLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLoginButton = (Button) findViewById(R.id.userButton);
        userLoginButton.setOnClickListener(v -> openActivityULA());


        adminLoginButton = (Button) findViewById(R.id.adminButton);
        adminLoginButton.setOnClickListener(v -> openActivityALA());
    }

    public void openActivityULA () {
        Intent intent = new Intent(this, UserLoginActivity.class);
        startActivity(intent);
    }


    public void openActivityALA () {
        Intent intent = new Intent(this, AdminLoginActivity.class);
        startActivity(intent);
    }
}


