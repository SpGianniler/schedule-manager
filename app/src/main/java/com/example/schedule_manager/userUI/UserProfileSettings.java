package com.example.schedule_manager.userUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schedule_manager.Credentials;
import com.example.schedule_manager.Ergazomenoi;
import com.example.schedule_manager.MainActivity;
import com.example.schedule_manager.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import org.w3c.dom.ls.LSOutput;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.widget.Toast.LENGTH_SHORT;

public class UserProfileSettings extends AppCompatActivity {

    private Button pickButton;
    private TextView dobText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_settings_activity);

        pickButton = findViewById(R.id.UserSettingsChooseDoBButton);
        dobText=findViewById(R.id.UserSettingsChosenDOBText);
        EditText onoma, epitheto, username, password;
        onoma = (EditText) findViewById(R.id.UserSettingsFirstNameEditText);
        epitheto = (EditText) findViewById(R.id.UserSettingsLastNameEditText);
        username = (EditText) findViewById(R.id.UserSettingsUsernameEditText);
        password = (EditText) findViewById(R.id.UserSettingsPasswordEditText);

        for(Ergazomenoi erg : MainActivity.getErgazomenoiArrayList()){
            if(erg.getErg_id() == UserLoginActivity.getEid()){
                onoma.setText(erg.getOnoma());
                epitheto.setText(erg.getEpitheto());
                for(Credentials crd : MainActivity.getCredentialsArrayList()){
                    if(erg.getErg_id() == crd.getEid()) {
                        username.setText(crd.getUsername());
                        password.setText(crd.getPassword());
                    }
                }
            }
        }

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