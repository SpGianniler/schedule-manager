package com.example.schedule_manager.adminUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.schedule_manager.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class AdminSheduleEdit extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd;
    Button buttonSubmit;
    int i=0;

    List<String> spinnerList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_shedule_edit_activity);

        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.new_vardia_btn);
        buttonSubmit = findViewById(R.id.ypovolh_vardia_btn);
        buttonAdd.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);

        spinnerList.add("1");
        spinnerList.add("2");
        spinnerList.add("3");
        spinnerList.add("4");
        spinnerList.add("5");
        spinnerList.add("6");


    }

    @Override
    public void onClick(View v) {
        addView();
    }


    private void addView() {
        View newAdeiaView = getLayoutInflater().inflate(R.layout.admin_vardies_form,null,false);
        EditText editText = (EditText)newAdeiaView.findViewById(R.id.edit_vardia_name);
        AppCompatSpinner spinner = (AppCompatSpinner)newAdeiaView.findViewById(R.id.spinnervardies);
        ImageView close = (ImageView)newAdeiaView.findViewById(R.id.delete_vardia);
        TextView textvardia = (TextView)newAdeiaView.findViewById(R.id.vardies_count);
        i=i+1;
        textvardia.setText("Βάρδια "+i);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,spinnerList);
        spinner.setAdapter(arrayAdapter);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(newAdeiaView);
            }
        });
        layoutList.addView(newAdeiaView);
    }

    private void removeView(View view){
        i=i-1;
        layoutList.removeView(view);
    }


}