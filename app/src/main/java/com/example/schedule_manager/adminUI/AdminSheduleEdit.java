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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schedule_manager.R;
import com.example.schedule_manager.Vardies;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class AdminSheduleEdit extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd;
    Button buttonSubmit;
    int i=0;

    List<Integer> spinnerList = new ArrayList<>();
    ArrayList<Vardies> vardies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_shedule_edit_activity);

        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.new_vardia_btn);
        buttonSubmit = findViewById(R.id.ypovolh_vardia_btn);

        buttonAdd.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);

        spinnerList.add(1);
        spinnerList.add(2);
        spinnerList.add(3);
        spinnerList.add(4);
        spinnerList.add(5);
        spinnerList.add(6);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.new_vardia_btn:
                addView();
                break;
            case R.id.ypovolh_vardia_btn:
                submitView();
                break;
        }
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
        System.out.println(layoutList.getChildCount());
    }

    private void removeView(View view){
        i=i-1;
        layoutList.removeView(view);
    }

    private void submitView(){

        vardies.clear();
        boolean result = true;
        String name="";
       // String eidikothta="";
        int employeesNum=0;
        int sid=-1;
        int jid=-1;

        for(int i=0;i<layoutList.getChildCount();i++){
            View newAdeiaView = layoutList.getChildAt(i);
            EditText editTextVardiaName = (EditText)newAdeiaView.findViewById(R.id.edit_vardia_name);
            AppCompatSpinner spinnerSidVardia = (AppCompatSpinner)newAdeiaView.findViewById(R.id.spinnervardies);
            CheckBox mhxanikos = (CheckBox)newAdeiaView.findViewById(R.id.checkMhxanikos);
            CheckBox grammateia = (CheckBox)newAdeiaView.findViewById(R.id.checkGrammateia);
            CheckBox grmParagwgis = (CheckBox)newAdeiaView.findViewById(R.id.checkGrmParagwgis);
            CheckBox katharisths = (CheckBox)newAdeiaView.findViewById(R.id.checkKatharisths);
            CheckBox epivlepon = (CheckBox)newAdeiaView.findViewById(R.id.checkEpivlepon);

            Vardies vardia = new Vardies(name,employeesNum,sid,jid);


            if(!editTextVardiaName.getText().toString().equals("")){
                vardia.setOnoma(editTextVardiaName.getText().toString());
            }else{
                result=false;
                Toast.makeText(this, "Δώσε Όνομα Βάρδιας", Toast.LENGTH_SHORT).show();
                break;
            }


            if(spinnerSidVardia.getSelectedItemPosition()!=-1){
                vardia.setSid(spinnerList.get(spinnerSidVardia.getSelectedItemPosition()));
            }else{
                result=false;
                Toast.makeText(this, "Δώσε Κωδικό Βάρδιας", Toast.LENGTH_SHORT).show();
                break;
            }

            if(mhxanikos.isChecked()){
                EditText editTextMhxanikos = (EditText)newAdeiaView.findViewById(R.id.editTextMhxanikos);
                vardia.setEmploeesNo(Integer.parseInt(editTextMhxanikos.getText().toString()));
                vardia.setJid(1);
                vardies.add(vardia);
            }

            if(grammateia.isChecked()){
                EditText editTextGrammateia = (EditText)newAdeiaView.findViewById(R.id.editTextGrammateia);
                vardia.setEmploeesNo(Integer.parseInt(editTextGrammateia.getText().toString()));
                vardia.setJid(2);
                vardies.add(vardia);
            }

            if(grmParagwgis.isChecked()){
                EditText editTextGrmParagwgis = (EditText)newAdeiaView.findViewById(R.id.editTextGrmParagwgis);
                vardia.setEmploeesNo(Integer.parseInt(editTextGrmParagwgis.getText().toString()));
                vardia.setJid(3);
                vardies.add(vardia);
            }

            if(katharisths.isChecked()){
                EditText editTextKatharisths = (EditText)newAdeiaView.findViewById(R.id.editTextKatharisths);
                vardia.setEmploeesNo(Integer.parseInt(editTextKatharisths.getText().toString()));
                vardia.setJid(4);
                vardies.add(vardia);
            }

            if(epivlepon.isChecked()){
                EditText editTextEpivlepon = (EditText)newAdeiaView.findViewById(R.id.editTextEpivlepon);
                vardia.setEmploeesNo(Integer.parseInt(editTextEpivlepon.getText().toString()));
                vardia.setJid(5);
                vardies.add(vardia);
            }


           // vardies.add(vardia);
            if(vardies.contains(vardia)){System.out.println("true");}
            else System.out.print("false");
            System.out.println(vardies);
        }

        if(vardies.size()==0){
            result = false;
            Toast.makeText(this, "Δώσε Νέα Βάρδια!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(this, "Συμπλήρωσε Όλα Τα Πεδία!", Toast.LENGTH_SHORT).show();
        }

        System.out.println(vardies);
        System.out.println("SAKTA");

    }



}