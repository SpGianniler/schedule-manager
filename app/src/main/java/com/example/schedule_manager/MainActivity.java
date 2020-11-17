package com.example.schedule_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends BaseActivity {
    private Button userLoginButton;
    private Button adminLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Ergazomenoi> ergazomenoiList = new ArrayList<>();
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

        //code to test that the db creation works
        Ergazomenoi ergazomenoi;
        ergazomenoi = new Ergazomenoi(1,"aaa","bbb","ccc",8,"ddd",false);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this, DataBaseHelper.DATABASE_NAME,null,DataBaseHelper.DATABASE_VERSION);
        boolean success = dataBaseHelper.addOne(ergazomenoi);
        Toast.makeText(MainActivity.this,"Success = "+ success, Toast.LENGTH_SHORT).show();
        //end of db testing code

        //ergazomenoiList = dataBaseHelper.getEveryone();
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


