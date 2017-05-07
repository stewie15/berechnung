package at.trinkl.daniel.berechnung.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import at.trinkl.daniel.berechnung.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.spinner).setOnClickListener(new);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final double input = Double.parseDouble(((EditText)findViewById(R.id.editText)).getText().toString());
                Calculation calculation = new Calculation();
                double dole = calculation.calculate(input);
                ((TextView)findViewById(R.id.textView)).setText("Ihr tägliches Arbeitslosengeld beträgt: " + dole);
            }
        });
    }
}
