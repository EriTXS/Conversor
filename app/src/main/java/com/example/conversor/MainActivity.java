package com.example.conversor;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerInputUnit;
    private Spinner spinnerOutputUnit;
    private EditText editTextInput;
    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerInputUnit = findViewById(R.id.spinnerInputUnit);
        spinnerOutputUnit = findViewById(R.id.spinnerOutputUnit);
        editTextInput = findViewById(R.id.editTextInput);
        textViewResultado = findViewById(R.id.textViewResultado);
        Button buttonConverter = findViewById(R.id.buttonConverter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerInputUnit.setAdapter(adapter);
        spinnerOutputUnit.setAdapter(adapter);

        buttonConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        String inputUnit = spinnerInputUnit.getSelectedItem().toString();
        String outputUnit = spinnerOutputUnit.getSelectedItem().toString();
        String inputText = editTextInput.getText().toString();

        if (inputText.isEmpty()) {
            Toast.makeText(this, "Escreva o valor", Toast.LENGTH_SHORT).show();
            return;
        }

        double inputValue = Double.parseDouble(inputText);
        double resultValue = convert(inputValue, inputUnit, outputUnit);

        textViewResultado.setText(String.valueOf(resultValue));
    }

    private double convert(double value, String inputUnit, String outputUnit) {
        double valueInMeters;
        switch (inputUnit) {
            case "Centimetros":
                valueInMeters = value / 100;
                break;
            case "Metros":
                valueInMeters = value;
                break;
            case "Kilometros":
                valueInMeters = value * 1000;
                break;
            case "Milhas":
                valueInMeters = value * 1609.34;
                break;
            default:
                valueInMeters = 0;
        }


        double resultValue;
        switch (outputUnit) {
            case "Centimetros":
                resultValue = valueInMeters * 100;
                break;
            case "Metros":
                resultValue = valueInMeters;
                break;
            case "Kilometros":
                resultValue = valueInMeters / 1000;
                break;
            case "Milhas":
                resultValue = valueInMeters / 1609.34;
                break;
            default:
                resultValue = 0;
        }

        return resultValue;
    }
}
