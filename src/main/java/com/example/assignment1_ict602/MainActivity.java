package com.example.assignment1_ict602;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar myToolbar;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        continueButton = (Button) findViewById(R.id.buttonContinuehome);
        continueButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_info) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "\"Experience effortless Zakat calculations with our Gold Calculator app! " +
                    "Download now for ultimate convenience and take charge of your zakat journey. " +
                    "Tap the link for a seamless financial endeavor. " +
                    "Download Zakat Gold now!\" via http://t.co/app");
            startActivity(Intent.createChooser(shareIntent, null));
            return true;
        } else if (item.getItemId() == R.id.item_about) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
            return true;
        } else if (item.getItemId() == R.id.item_settings) {
            // Handle the "Settings" menu item
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == continueButton) {
            Intent intent = new Intent(this, CalculateActivity.class);

            startActivity(intent);
        }
    }
}