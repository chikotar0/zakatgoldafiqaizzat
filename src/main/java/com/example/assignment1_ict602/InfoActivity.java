package com.example.assignment1_ict602;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

public class InfoActivity extends AppCompatActivity {

    Toolbar infoToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        infoToolbar = findViewById(R.id.info_toolbar);
        setSupportActionBar(infoToolbar);
        getSupportActionBar().setTitle("Instruction");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home){
            super.onBackPressed();
        }
        return true;
    }
}