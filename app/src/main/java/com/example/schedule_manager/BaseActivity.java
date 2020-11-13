package com.example.schedule_manager;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    /**
     *Creates menu bar at the top of the app.
     * @param menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;

    }

    /**
     * Adds functionality to menu bar items.
     *
     * Add new case to switch statement for new item
     *
     * @param item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;

        switch (item.getItemId()) {
            case R.id.miSettings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.miInfo:
                Toast.makeText(this, "Info", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,InfoActivity.class);
                startActivity(intent);
                break;
        }
        return true;

    }
}
