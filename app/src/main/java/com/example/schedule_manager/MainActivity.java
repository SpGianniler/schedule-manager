package com.example.schedule_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //code to test that the db creation works
        Ergazomenoi ergazomenoi;
        ergazomenoi= new Ergazomenoi(1,"aaa","bbb","ccc",8,"ddd");

        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this, DataBaseHelper.DATABASE_NAME,null,DataBaseHelper.DATABASE_VERSION);
        boolean success = dataBaseHelper.addOne(ergazomenoi);
        Toast.makeText(MainActivity.this,"Success= "+ success, Toast.LENGTH_SHORT).show();
        //end of db testing code
    }
}