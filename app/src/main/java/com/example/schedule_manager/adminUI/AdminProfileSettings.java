package com.example.schedule_manager.adminUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schedule_manager.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import org.w3c.dom.ls.LSOutput;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.widget.Toast.LENGTH_SHORT;

public class AdminProfileSettings extends AppCompatActivity {

    private Button pickButton;
    private TextView dobText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile_settings);

        pickButton = findViewById(R.id.AdminSettingsChooseDoBButton);
        dobText=findViewById(R.id.AdminSettingsChosenDOBText);
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select date");
        MaterialDatePicker materialDatePicker = builder.build();





        pickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                materialDatePicker.show(getSupportFragmentManager(),"DATE_PICKER");
            }
        });
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {

                SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");

                dobText.setText(simpleFormat.format(materialDatePicker.getSelection()));
            }

        });
    }
}