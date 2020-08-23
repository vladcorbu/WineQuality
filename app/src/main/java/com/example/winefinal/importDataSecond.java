package com.example.winefinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class importDataSecond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_data_second);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    public void sentData(View view) {
        Boolean[] range = new Boolean[6];
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        String[] csv_data = extras.getStringArray("csv_data");
        float[] features = new float[6];

        TextInputEditText tfdioxide = (TextInputEditText) findViewById(R.id.tsdioxide_edit);
        features[0] = Float.parseFloat(tfdioxide.getText().toString());
        TextInputLayout tsdioxide_layout = (TextInputLayout) findViewById(R.id.tsdioxide);
        range[0] = features[0] >= 0 && features[0] <= 500;
        set_error(tsdioxide_layout, range[0], "must be between 0 and 500 g/L");

        TextInputEditText density = (TextInputEditText)findViewById(R.id.density_edit);
        features[1] = Float.parseFloat(density.getText().toString());
        TextInputLayout density_layout = (TextInputLayout) findViewById(R.id.density);
        range[1] = features[1] > 0.9 && features[1] <= 1.1;
        set_error(density_layout, range[1], "must be between 0.9 and 1.1");

        TextInputEditText ph = (TextInputEditText)findViewById(R.id.ph_edit);
        features[2] = Float.parseFloat(ph.getText().toString());
        TextInputLayout ph_layout = (TextInputLayout) findViewById(R.id.ph);
        range[2] = features[2] >= 2.5 && features[2] <= 4;
        set_error(ph_layout, range[2], "must be between 2.5 and 4 g/L");

        TextInputEditText sulph = (TextInputEditText)findViewById(R.id.sulph_edit);
        features[3] = Float.parseFloat(sulph.getText().toString());
        TextInputLayout sulph_layout = (TextInputLayout) findViewById(R.id.sulph);
        range[3] = features[3] > 0 && features[3] <= 1.5;
        set_error(sulph_layout, range[3], "must be between 0 and 1.5 g/L");

        TextInputEditText alcohol = (TextInputEditText)findViewById(R.id.alcohol_edit);
        features[4] = Float.parseFloat(alcohol.getText().toString());
        TextInputLayout alcohol_layout = (TextInputLayout) findViewById(R.id.alcohol);
        range[4] = features[4] >= 8 && features[4] <= 15;
        set_error(alcohol_layout, range[4], "must be between 8% and 15%");

        TextInputEditText quality = (TextInputEditText)findViewById(R.id.quality_edit);
        features[5] = Float.parseFloat(quality.getText().toString());
        TextInputLayout quality_layout = (TextInputLayout) findViewById(R.id.quality);
        range[5] = features[5] >= 3 && features[5] <= 9;
        set_error(quality_layout, range[5], "must be between 3 and 9");

        Boolean all_set = true;
        for (Boolean r: range
        ) {
            all_set = all_set & r;
        }

        if(all_set) {
            for(int i = 0; i < 6; ++i){
                assert csv_data != null;
                csv_data[i+6] = String.valueOf(features[i]);
            }
            Intent intent = new Intent(this, sendingActivity.class);
            intent.putExtra("csv_data", csv_data);
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
