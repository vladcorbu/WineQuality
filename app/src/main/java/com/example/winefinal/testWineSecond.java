package com.example.winefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class testWineSecond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_wine_second);
    }

    public void Result(View view) {

        Boolean[] range = new Boolean[6];
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        float[] features = extras.getFloatArray("features");

        TextInputEditText fsdioxide = (TextInputEditText) findViewById(R.id.fsdioxide_edit);
        assert features != null;
        features[5] = Float.parseFloat(fsdioxide.getText().toString());
        TextInputLayout fsdioxide_layout = (TextInputLayout) findViewById(R.id.fsdioxide);
        range[0] = features[5] > 0 && features[0] <= 300;
        set_error(fsdioxide_layout, range[0], "must be between 0 and 300 g/L");

        TextInputEditText tfdioxide = (TextInputEditText) findViewById(R.id.tsdioxide_edit);
        features[6] = Float.parseFloat(tfdioxide.getText().toString());
        TextInputLayout tsdioxide_layout = (TextInputLayout) findViewById(R.id.tsdioxide);
        range[1] = features[6] >= 0 && features[6] <= 500;
        set_error(tsdioxide_layout, range[1], "must be between 0 and 500 g/L");

        TextInputEditText density = (TextInputEditText)findViewById(R.id.density_edit);
        features[7] = Float.parseFloat(density.getText().toString());
        TextInputLayout density_layout = (TextInputLayout) findViewById(R.id.density);
        range[2] = features[7] > 0.9 && features[7] <= 1.1;
        set_error(density_layout, range[2], "must be between 0.9 and 1.1");

        TextInputEditText ph = (TextInputEditText)findViewById(R.id.ph_edit);
        features[8] = Float.parseFloat(ph.getText().toString());
        TextInputLayout ph_layout = (TextInputLayout) findViewById(R.id.ph);
        range[3] = features[8] >= 2.5 && features[8] <= 4;
        set_error(ph_layout, range[3], "must be between 2.5 and 4 g/L");

        TextInputEditText sulph = (TextInputEditText)findViewById(R.id.sulph_edit);
        features[9] = Float.parseFloat(sulph.getText().toString());
        TextInputLayout sulph_layout = (TextInputLayout) findViewById(R.id.sulph);
        range[4] = features[9] > 0 && features[9] <= 1.5;
        set_error(sulph_layout, range[4], "must be between 0 and 1.5 g/L");

        TextInputEditText alcohol = (TextInputEditText)findViewById(R.id.alcohol_edit);
        features[10] = Float.parseFloat(alcohol.getText().toString());
        TextInputLayout alcohol_layout = (TextInputLayout) findViewById(R.id.alcohol);
        range[5] = features[10] >= 8 && features[0] <= 15;
        set_error(alcohol_layout, range[5], "must be between 8% and 15%");

        Boolean all_set = true;
        for (Boolean r: range
        ) {
            all_set = all_set & r;
        }

        if(all_set) {
            Intent intent = new Intent(this, Result.class);
            intent.putExtra("features", features);
            startActivity(intent);
        }

    }

    public void set_error(TextInputLayout layout, Boolean range, String error_txt){
        if(!range){
            layout.setErrorEnabled(true);
            layout.setError(error_txt);
        } else {
            layout.setErrorEnabled(false);
        }
    }
}
