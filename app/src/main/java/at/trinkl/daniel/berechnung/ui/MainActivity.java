package at.trinkl.daniel.berechnung.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import at.trinkl.daniel.berechnung.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Some Log", "oncreate called");
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final double input = Double.parseDouble(((EditText)findViewById(R.id.editText)).getText().toString());
                final double fa = Double.parseDouble(((Spinner) findViewById(R.id.spinner)).getAdapter().toString());
                Calculation calculation = new Calculation();
                double dole = calculation.calculate(input, fa);
                ((TextView) findViewById(R.id.textViewOutput)).setText("Ihr tägliches Arbeitslosengeld beträgt: " + dole);
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.kinder_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}

