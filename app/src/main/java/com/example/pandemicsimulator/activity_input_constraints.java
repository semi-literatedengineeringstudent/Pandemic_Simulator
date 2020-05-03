package com.example.pandemicsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class activity_input_constraints extends AppCompatActivity {

    Button backtoMain;
    Button backtoCurvesetup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_constraints);

        backtoMain = findViewById(R.id.backtoMainpage);
        backtoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                goingBacktoMainpage();
            }
        });

        backtoCurvesetup = findViewById(R.id.backtoSetupcurve);
        backtoCurvesetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                goingBacktoSetupcurve();
            }
        });

    }

    private void goingBacktoMainpage() {
        Intent backtoMainpage = new Intent(this, MainActivity.class);
        startActivity(backtoMainpage);
    }
    private void goingBacktoSetupcurve() {
        Intent toCurvesetup = new Intent(this, set_up_curve_data.class);
        startActivity(toCurvesetup);

    }
}
