package com.example.schedule_manager;

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

public class MainActivity extends AppCompatActivity {

    public EditText name;
    public Button query_button;
    public TextView result_dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        query_button = findViewById(R.id.query_button);
        result_dob = findViewById(R.id.result);

        //setting onclicklistener

        query_button.setOnClickListener(v -> {
            //create the instance of database access class and open database connection
            DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getApplicationContext());
            dataBaseAccess.openDB();

            //getting string value from editText

            String n = name.getText().toString();
            String dob = dataBaseAccess.getDoB(n);

            //setting text to result
            result_dob.setText(dob);

            dataBaseAccess.closeDB();

            //datase connection closed

        });

    }
}
