package com.example.assignment1_ict602;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CalculateActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etWeight;
    EditText etGoldValue;
    Button btnCalculate;
    Button btnClear;
    TextView totalValueTextView;
    TextView zakatPayableTextView;
    TextView zakatTextView;
    Toolbar calculateToolbar;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        calculateToolbar = findViewById(R.id.calculate_toolbar);
        setSupportActionBar(calculateToolbar);
        getSupportActionBar().setTitle("Zakat Gold Calculator");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etWeight = (EditText) findViewById(R.id.editTextNumberDecimal2);
        radioGroup = findViewById(R.id.radioGroup);
        etGoldValue = (EditText) findViewById(R.id.editTextCurrentGold);
        btnCalculate = (Button) findViewById(R.id.buttonCalculate);
        totalValueTextView = (TextView) findViewById((R.id.totalValueTextView));
        zakatPayableTextView = (TextView) findViewById((R.id.zakatPayableTextView));
        zakatTextView = (TextView) findViewById((R.id.zakatTextView));

        btnCalculate.setOnClickListener(this);

        btnClear = findViewById(R.id.buttonClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearInput();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu info) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.info, info);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_info) {
            // Handle the "Info" menu item
            Intent infoIntent = new Intent(this, InfoActivity.class);
            startActivity(infoIntent);
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void checkButton(View vv) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        makeText(this, "Selected Radio Button" + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonCalculate) {
            // Ensure radioButton is initialized
            if (checkButton()) {
                double weight = Double.parseDouble(etWeight.getText().toString());
                String type = radioButton.getText().toString().toLowerCase();
                double value = Double.parseDouble(etGoldValue.getText().toString());

                double keepX = 85.0; // X for keep type
                double wearX = 200.0; // X for wear type

                double totalValue = weight * value;
                double totalGoldWeight;
                double zakatPayable;
                if (radioGroup.getCheckedRadioButtonId() == R.id.radioKeep) {
                    totalGoldWeight = weight - keepX;
                    totalGoldWeight = Math.max(totalGoldWeight, 0);
                    zakatPayable = totalGoldWeight * value;
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioWear) {
                    totalGoldWeight = weight - wearX;
                    totalGoldWeight = Math.max(totalGoldWeight, 0);
                    zakatPayable = totalGoldWeight * value;
                } else {
                    // Handle the case where no radio button is selected
                    Toast.makeText(this, "Please fill in the weight/current value or select a type of gold", Toast.LENGTH_SHORT).show();
                    return; // Stop further execution if no radio button is selected
                }

                double zakat = 0.025 * zakatPayable;

                totalValueTextView.setText("Total Value: " + totalValue);
                zakatPayableTextView.setText("Zakat Payable: " + zakatPayable);
                zakatTextView.setText("Zakat: " + zakat);
            } else {
                // Handle the case where no radio button is selected
                Toast.makeText(this, "Please fill in the weight/current value or select a type of gold", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkButton() {
        int radioId = radioGroup.getCheckedRadioButtonId();
        if (radioId != -1) {
            radioButton = findViewById(radioId);
            return true;
        }
        return false;
    }

    private void clearInput() {
        String weightText = etWeight.getText().toString().trim();
        String valueText = etGoldValue.getText().toString().trim();

        if (weightText.isEmpty() && valueText.isEmpty() && radioGroup.getCheckedRadioButtonId() == -1) {
            // If there are no inputs, display a message
            Toast.makeText(this, "No input to clear", Toast.LENGTH_SHORT).show();
        } else {
            // Clear inputs and reset TextViews
            etWeight.getText().clear();
            etGoldValue.getText().clear();
            totalValueTextView.setText("Total Value: ");
            zakatPayableTextView.setText("Zakat Payable: ");
            zakatTextView.setText("Zakat: ");
            radioGroup.clearCheck();
        }
    }
}
