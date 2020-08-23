package com.example.winefinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class importData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_data);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    public void nextData(View view) {
        String[] csv_data = new String[12];
        float[] features = new float[11];
        Boolean[] range = new Boolean[6];

        TextInputEditText facidity = (TextInputEditText) findViewById(R.id.facidity_edit);
        features[0] = Float.parseFloat(facidity.getText().toString());
        TextInputLayout facidity_layout = (TextInputLayout) findViewById(R.id.facidity);
        range[0] = features[0] >= 3 && features[0] <= 14.2;
        set_error(facidity_layout, range[0], "must be between 3 and 14.2 g/L");

        TextInputEditText vacidity = (TextInputEditText) findViewById(R.id.vacidity_edit);
        features[1] = Float.parseFloat(vacidity.getText().toString());
        TextInputLayout vacidity_layout = (TextInputLayout) findViewById(R.id.vacidity);
        range[1] = features[1] > 0 && features[1] < 2;
        set_error(vacidity_layout, range[1], "must be between 0 and 2 g/L");

        TextInputEditText cacid = (TextInputEditText) findViewById(R.id.cacid_edit);
        features[2] = Float.parseFloat(cacid.getText().toString());
        TextInputLayout cacid_layout = (TextInputLayout) findViewById(R.id.cacid);
        range[2] = features[2] > 0 && features[2] < 2;
        set_error(cacid_layout, range[2], "must be between 0 and 2 g/L");

        TextInputEditText sugar = (TextInputEditText) findViewById(R.id.sugar_edit);
        features[3] = Float.parseFloat(sugar.getText().toString());
        TextInputLayout sugar_layout = (TextInputLayout) findViewById(R.id.sugar);
        range[3] = features[3] > 0 && features[3] < 150;
        set_error(sugar_layout, range[3], "must be between 0 and 150 g/L");

        TextInputEditText chlor = (TextInputEditText) findViewById(R.id.chlor_edit);
        features[4] = Float.parseFloat(chlor.getText().toString());
        TextInputLayout chlor_layout = (TextInputLayout) findViewById(R.id.chlor);
        range[4] = features[4] > 0 && features[4] < 2;
        set_error(chlor_layout, range[4], "must be between 0 and 2 g/L");

        TextInputEditText fsdioxide = (TextInputEditText) findViewById(R.id.fsdioxide_edit);
        features[5] = Float.parseFloat(fsdioxide.getText().toString());
        TextInputLayout fsdioxide_layout = (TextInputLayout) findViewById(R.id.fsdioxide);
        range[5] = features[5] > 0 && features[5] < 300;
        set_error(fsdioxide_layout, range[5], "must be between 0 and 300 g/L");

        Boolean all_set = true;
        for (Boolean r: range
        ) {
            all_set = all_set & r;
        }

        if(all_set){
            for(int i = 0; i < 6; ++i){
                csv_data[i] = String.valueOf(features[i]);
            }
            Intent intent = new Intent(this, importDataSecond.class);
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
