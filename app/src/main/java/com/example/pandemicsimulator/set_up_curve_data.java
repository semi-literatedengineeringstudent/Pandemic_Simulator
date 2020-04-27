package com.example.pandemicsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pandemicsimulator.Math_job.SIR_model_handler;

public class set_up_curve_data extends AppCompatActivity {
    Button drawCurve ;
    Button backtoMain;
    float initianPopulation;
    float initialInfected;
    float infectedContact;
    float infectionRatio;
    float deathRatio;
    float recoverRatio;
    float pandemicDuration;

    EditText initianPopulationInput;
    EditText initialInfectedInput;
    EditText infectedContactInput;
    EditText infectionRatioInput;
    EditText deathRatioInput;
    EditText recoverRatioInput;
    EditText pandemicDurationInput;

    public SIR_model_handler toDraw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_curve_data);
        initianPopulationInput = (EditText) findViewById(R.id.initialPopulation);
        initialInfectedInput = (EditText) findViewById(R.id.initialInfected);
        infectedContactInput = (EditText) findViewById(R.id.infectionContact);
        infectionRatioInput = (EditText) findViewById(R.id.infectionRatio);
        deathRatioInput = (EditText) findViewById(R.id.deathRatio);
        recoverRatioInput = (EditText) findViewById(R.id.recoverRatio);
        pandemicDurationInput = (EditText) findViewById(R.id.pandemicDuration);

        drawCurve = (Button) findViewById(R.id.todrawCurve);
        drawCurve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                initianPopulation = Float.valueOf(initianPopulationInput.getText().toString());
                initialInfected = Float.valueOf(initialInfectedInput.getText().toString());
                infectedContact = Float.valueOf(infectedContactInput.getText().toString());
                infectionRatio = Float.valueOf(infectionRatioInput.getText().toString());
                deathRatio= Float.valueOf(deathRatioInput.getText().toString());
                recoverRatio = Float.valueOf(recoverRatioInput.getText().toString());
                pandemicDuration = (Float.valueOf(pandemicDurationInput.getText().toString()) / 50);
                showToast(String.valueOf(initianPopulation));
                showToast(String.valueOf(initialInfected));
                showToast(String.valueOf(infectedContact));
                showToast(String.valueOf(infectionRatio));
                showToast(String.valueOf(deathRatio));
                showToast(String.valueOf(recoverRatio));
                showToast(String.valueOf(pandemicDuration));
                toDraw = new SIR_model_handler(initianPopulation, initialInfected, infectedContact,
                        infectionRatio, deathRatio, recoverRatio, pandemicDuration);
                Intent curveInfo = new Intent(set_up_curve_data.this, drawingCurve.class);
                curveInfo.putExtra("timeArray", getTimeArray());
                curveInfo.putExtra("susceptibleArray", getSusceptibleArray());
                curveInfo.putExtra("infectedArray", getInfectedArray());
                curveInfo.putExtra("recoveredArray", getRecoveredArray());
                curveInfo.putExtra("totalPopulationArray", getTotalPopulationArray());
                curveInfo.putExtra("fatalityArray", getFatalityArray());
                startActivity(curveInfo);





            }
        });
        backtoMain = (Button) findViewById(R.id.backtoMainpage);
        backtoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                goingBacktoMainpage();
            }
        });

    }

    private void showToast(String text) {
        Toast.makeText(set_up_curve_data.this, text, Toast.LENGTH_SHORT).show();
    }
    private void goingBacktoMainpage() {
        Intent backtoMainpage = new Intent(this, MainActivity.class);
        startActivity(backtoMainpage);
    }
    public float[] getTimeArray() {
        return toDraw.timeArray;
    }
    public  float[] getSusceptibleArray() {
        return toDraw.susceptible;
    }
    public float[] getInfectedArray() {
        return toDraw.infected;
    }
    public float[] getRecoveredArray() {
        return toDraw.recovered;
    }
    public float[] getTotalPopulationArray() {
        return toDraw.totalPopulation;
    }
    public float[] getFatalityArray() {
        return toDraw.fatality;
    }
}
