package com.example.schedule_manager;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;



public class MainActivity extends BaseActivity {
    private Button userLoginButton;
    private Button adminLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String URL = "http://192.168.56.1:8080/jobs";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                (URL+"/job/1"),
                null,
                response -> Log.e("Rest Response",response.toString()),
                error -> Log.e("Rest Response",error.toString())
        );

        requestQueue.add(objectRequest);

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


