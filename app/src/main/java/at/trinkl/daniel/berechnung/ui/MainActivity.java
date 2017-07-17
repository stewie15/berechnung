package at.trinkl.daniel.berechnung.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import at.trinkl.daniel.berechnung.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityLog";

    //TODO: Give all views better names (you will have more than one editText later)
    @BindView(R.id.textViewOutput)
    TextView textViewOutput;

    @BindView(R.id.editText)
    TextView editText;

    @BindView(R.id.spinner)
    Spinner spinner;

    private Calculation calculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "oncreate called");

        ButterKnife.bind(this);

        calculation = new Calculation();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.kinder_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @OnClick
    public void buttonClick() {
        double input = Double.parseDouble(editText.getText().toString());
        double fa = Double.parseDouble(spinner.getSelectedItem().toString());

        double dole = calculation.calculate(input, fa);
        textViewOutput.setText(getString(R.string.daily_amount, dole));
    }
}

