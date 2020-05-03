package com.example.pandemicsimulator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pandemicsimulator.Math_job.SIR_model_handler;

public class activity_existing_virus extends AppCompatActivity {

    Button backtoMain;
    Button drawCurve ;
    Button seeInputConstraints;
    Button setEbola;
    Button setSRS;


    float initianPopulation;
    float initialInfected;
    float infectedContact;
    float infectionRatio;
    float deathRatio;
    float recoverRatio;
    float pandemicDuration;


    private final double EbolainfectionRatio = 0.1;
    private final double EboladeathRatio = 0.83;
    private final double EbolarecoverRatio = 0.17;

    private final double SARSinfectionRatio = 0.4;
    private final double SARSdeathRatio = 0.2;
    private final double SARSrecoverRatio = 0.8;

    EditText initianPopulationInput;
    EditText initialInfectedInput;
    EditText infectedContactInput;
    EditText pandemicDurationInput;

    public SIR_model_handler toDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_virus);

        initianPopulationInput = (EditText) findViewById(R.id.initialPopulation);
        initialInfectedInput = (EditText) findViewById(R.id.initialInfected);
        infectedContactInput = (EditText) findViewById(R.id.infectionContact);
        pandemicDurationInput = (EditText) findViewById(R.id.pandemicDuration);

        setEbola = (Button) findViewById(R.id.Ebola);
        setEbola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                infectionRatio = (float) EbolainfectionRatio;
                deathRatio = (float) EboladeathRatio;
                recoverRatio = (float) EbolarecoverRatio;
            }
        });

        setSRS = (Button) findViewById(R.id.SARS);
        setSRS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                infectionRatio = (float) SARSinfectionRatio;
                deathRatio = (float) SARSdeathRatio;
                recoverRatio = (float) SARSrecoverRatio;
            }
        });



        drawCurve = (Button) findViewById(R.id.drawCurve);
        drawCurve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                initianPopulation = Float.valueOf(initianPopulationInput.getText().toString());
                initialInfected = Float.valueOf(initialInfectedInput.getText().toString());
                infectedContact = Float.valueOf(infectedContactInput.getText().toString());
                pandemicDuration = (Float.valueOf(pandemicDurationInput.getText().toString()) / 10);
                showToast(String.valueOf(initianPopulation));
                showToast(String.valueOf(initialInfected));
                showToast(String.valueOf(infectedContact));
                showToast(String.valueOf(pandemicDuration));


                boolean condition = true;
                if (initianPopulation < 1) {
                    condition = false;
                } else if (initialInfected < 1 || initianPopulation < initialInfected) {
                    condition = false;
                } else if (infectedContact < 1 || initianPopulation < infectedContact) {
                    condition = false;
                } else if (infectionRatio > 1 || infectionRatio < 0) {
                    condition = false;
                } else if (pandemicDuration < 1) {
                    condition = false;
                }

                if (!condition) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(activity_existing_virus.this);
                    alert.setCancelable(true);
                    alert.setTitle("One or more inputs are incorrect.");
                    alert.setMessage("Please be sure to enter within constraints.");


                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    });
                    alert.show();

                } else {

                    toDraw = new SIR_model_handler(initianPopulation, initialInfected, infectedContact,
                            infectionRatio, deathRatio, recoverRatio, pandemicDuration);
                    Intent curveInfo = new Intent(activity_existing_virus.this, drawingCurve.class);
                    curveInfo.putExtra("timeArray", getTimeArray());
                    curveInfo.putExtra("susceptibleArray", getSusceptibleArray());
                    curveInfo.putExtra("infectedArray", getInfectedArray());
                    curveInfo.putExtra("recoveredArray", getRecoveredArray());
                    curveInfo.putExtra("totalPopulationArray", getTotalPopulationArray());
                    curveInfo.putExtra("fatalityArray", getFatalityArray());
                    startActivity(curveInfo);
                }


            }
        });

        seeInputConstraints = (Button) findViewById(R.id.toinputConstraint);
        seeInputConstraints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                seeInputConstraints();
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
        Toast.makeText(activity_existing_virus.this, text, Toast.LENGTH_SHORT).show();
    }
    private void goingBacktoMainpage() {
        Intent backtoMainpage = new Intent(this, MainActivity.class);
        startActivity(backtoMainpage);
    }

    private void seeInputConstraints() {
        Intent seeInputConstraints = new Intent(this, activity_input_constraints.class);
        startActivity(seeInputConstraints);
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



