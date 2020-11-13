package com.example.schedule_manager;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class SecondaryActivity extends BaseActivity {
    /**
     *Creates menu bar at the top of the app.
     * @param menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.app_secondary_bar_menu, menu);
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

        switch (item.getItemId()) {
            case R.id.miBack:
                Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
                finish();
                break;

        }
        return true;

    }
}
