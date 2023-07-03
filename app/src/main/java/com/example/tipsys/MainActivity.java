package com.example.tipsys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText totalEditText;private EditText taxEditText;private RadioButton zeroButton;private RadioButton fiveButton;
    private RadioButton tenButton;private RadioButton twentyButton;private TextView tipTextView;private TextView grandTotalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalEditText = findViewById(R.id.total_amount_edit_text);
        taxEditText = findViewById(R.id.tax_amount_edit_text);
        zeroButton = findViewById(R.id.zero_percent_radio_button);
        fiveButton = findViewById(R.id.five_percent_radio_button);
        tenButton = findViewById(R.id.ten_percent_radio_button);
        twentyButton = findViewById(R.id.twenty_percent_radio_button);
        tipTextView = findViewById(R.id.tip_amount_text_view);
        grandTotalTextView = findViewById(R.id.grand_total_text_view);

        // Set initial values
        totalEditText.setHint(getString(R.string.total_amount_hint));
        taxEditText.setHint(getString(R.string.tax_amount_hint));
        zeroButton.setChecked(true);
        tipTextView.setText(getString(R.string.tip_amount_default));
        grandTotalTextView.setText(getString(R.string.grand_total_default));
    }

    public void onClearButtonClick(View view) {
        totalEditText.setText("");
        taxEditText.setText("");
        zeroButton.setChecked(true);
        tipTextView.setText(getString(R.string.tip_amount_default));
        grandTotalTextView.setText(getString(R.string.grand_total_default));
    }

    public void onCalculateButtonClick(View view) {
        double totalAmount = 0.0; double taxAmount = 0.0; double tipPercentage = 0.0;

        try {
            totalAmount = Double.parseDouble(totalEditText.getText().toString());
        } catch (NumberFormatException e) {
            totalAmount = 0.0;
            Toast.makeText(this, "please enter all fields", Toast.LENGTH_SHORT).show();
        }try {
            taxAmount = Double.parseDouble(taxEditText.getText().toString());
        } catch (NumberFormatException e) {
            taxAmount = 0.0;
            Toast.makeText(this, "please enter all fields", Toast.LENGTH_SHORT).show();
        }

        if (fiveButton.isChecked()) {
            tipPercentage = 0.05;
        } else if (tenButton.isChecked()) {
            tipPercentage = 0.1;
        }

        else if (twentyButton.isChecked()) {
            tipPercentage = 0.2;
        }

        double tipsysAmount = totalAmount * tipPercentage;
        double grandTotal = totalAmount + taxAmount + tipsysAmount;

        DecimalFormat decimalFormat = new DecimalFormat("$0.00");
        String formattedTipAmount = decimalFormat.format(tipsysAmount);
        String formattedGrandTotal = decimalFormat.format(grandTotal);

        tipTextView.setText(formattedTipAmount);
        grandTotalTextView.setText(formattedGrandTotal);
    }
}
