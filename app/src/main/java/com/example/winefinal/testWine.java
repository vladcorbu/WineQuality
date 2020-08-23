package com.example.winefinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class testWine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_wine);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    public void nextProperties(View view) {
        float[] features = new float[11];
        Boolean[] range = new Boolean[5];

        TextInputEditText facidity = (TextInputEditText) findViewById(R.id.facidity);
        features[0] = Float.parseFloat(facidity.getText().toString());
        TextInputLayout facidity_layout = (TextInputLayout) findViewById(R.id.fsdioxide);
        range[0] = features[0] >= 3 && features[0] <= 14.2;
        set_error(facidity_layout, range[0], "must be between 3 and 14.2 g/L");

        TextInputEditText vacidity = (TextInputEditText) findViewById(R.id.vacidity);
        features[1] = Float.parseFloat(vacidity.getText().toString());
        TextInputLayout vacidity_layout = (TextInputLayout) findViewById(R.id.tsdioxide);
        range[1] = features[1] > 0 && features[1] < 2;
        set_error(vacidity_layout, range[1], "must be between 0 and 2 g/L");

        TextInputEditText cacid = (TextInputEditText) findViewById(R.id.cacid);
        features[2] = Float.parseFloat(cacid.getText().toString());
        TextInputLayout cacid_layout = (TextInputLayout) findViewById(R.id.density);
        range[2] = features[2] > 0 && features[2] < 2;
        set_error(cacid_layout, range[2], "must be between 0 and 2 g/L");

        TextInputEditText sugar = (TextInputEditText) findViewById(R.id.sugar);
        features[3] = Float.parseFloat(sugar.getText().toString());
        TextInputLayout sugar_layout = (TextInputLayout) findViewById(R.id.ph);
        range[3] = features[3] > 0 && features[3] < 150;
        set_error(sugar_layout, range[3], "must be between 0 and 150 g/L");

        TextInputEditText chlor = (TextInputEditText) findViewById(R.id.chlor);
        features[4] = Float.parseFloat(chlor.getText().toString());
        TextInputLayout chlor_layout = (TextInputLayout) findViewById(R.id.sulph);
        range[4] = features[4] > 0 && features[4] < 2;
        set_error(chlor_layout, range[4], "must be between 0 and 2 g/L");

        Boolean all_set = true;
        for (Boolean r: range
             ) {
            all_set = all_set & r;
        }

        if(all_set) {
            Intent intent = new Intent(this, testWineSecond.class);
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
