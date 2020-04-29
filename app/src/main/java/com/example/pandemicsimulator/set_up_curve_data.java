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

import java.util.Random;

public class set_up_curve_data extends AppCompatActivity {
    Button drawCurve ;
    Button backtoMain;
    Button randomValues;
    Button seeInputConstraints;

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

        /**
        test = findViewById(R.id.tester);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(set_up_curve_data.this);

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
            }
        });
         */
        /**
         * button to draw curve
         * will show error message if inputs not correct.
         */
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

                boolean condition = true;
                if (initianPopulation < 1) {
                    condition = false;
                } else if (initialInfected < 1) {
                    condition = false;
                } else if (infectedContact < 1) {
                    condition = false;
                } else if (infectionRatio > 1 || infectionRatio < 0) {
                    condition = false;
                } else if (deathRatio > 1 || deathRatio < 0) {
                    condition = false;
                } else if (recoverRatio > 1 || recoverRatio < 0) {
                    condition = false;
                } else if (pandemicDuration < 1) {
                    condition = false;
                }

                if (condition == false) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(set_up_curve_data.this);
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
                    Intent curveInfo = new Intent(set_up_curve_data.this, drawingCurve.class);
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


        /**
         * button to see input constraints.
         */
        seeInputConstraints = findViewById(R.id.seeInputConstraints);
        seeInputConstraints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInputConstraints();
            }
        });


        /**
         * random button
         * not finished yet
         * add code to another activity accessed from the start page
         */
        randomValues = findViewById((R.id.todrawCurve));
        randomValues.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random population = new Random();
                int randomPopulation = population.nextInt(1000000 - 2) + 2;

                Random infected = new Random();
                int randomInfected = infected.nextInt(1000000 - 2) + 2;

                Random contact = new Random();
                int randomContact = contact.nextInt(1000000 - 2) + 2;

                Random duration = new Random();
                int randomDuration = duration.nextInt(3000 - 2) + 2;

                Random infection = new Random();
                float randomInfection = infection.nextInt(100 - 1) / 100;

                Random death = new Random();
                float randomDeath = (death.nextInt(100 - 1) / 100);

                Random recover = new Random();
                float randomRecover = (recover.nextInt(100 - 1) / 100);


                initianPopulation = randomPopulation;
                initialInfected = randomInfected;
                infectedContact = randomContact;
                infectionRatio = randomInfection;
                deathRatio= randomDeath;
                recoverRatio = randomRecover;
                pandemicDuration = randomDuration;
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
        }));



        /**
         * button to see input constraints.
         */
        seeInputConstraints = findViewById(R.id.seeInputConstraints);
        seeInputConstraints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInputConstraints();
            }
        });


        /**
         * random button
         * not finished yet
         * add code to another activity accessed from the start page
         */
        randomValues = findViewById((R.id.todrawCurve));
        randomValues.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random population = new Random();
                int randomPopulation = population.nextInt(1000000 - 2) + 2;

                Random infected = new Random();
                int randomInfected = infected.nextInt(1000000 - 2) + 2;

                Random contact = new Random();
                int randomContact = contact.nextInt(1000000 - 2) + 2;

                Random duration = new Random();
                int randomDuration = duration.nextInt(3000 - 2) + 2;

                Random infection = new Random();
                float randomInfection = infection.nextInt(100 - 1) / 100;

                Random death = new Random();
                float randomDeath = (death.nextInt(100 - 1) / 100);

                Random recover = new Random();
                float randomRecover = (recover.nextInt(100 - 1) / 100);


                initianPopulation = randomPopulation;
                initialInfected = randomInfected;
                infectedContact = randomContact;
                infectionRatio = randomInfection;
                deathRatio= randomDeath;
                recoverRatio = randomRecover;
                pandemicDuration = randomDuration;
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
        }));

        backtoMain = findViewById(R.id.backtoMainpage);
        backtoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                goingBacktoMainpage();
            }
        });

    }

    private void toInputConstraints() {
        Intent toInputConstraints = new Intent(this, activity_input_constraints.class);
        startActivity(toInputConstraints);
    }

    private void showToast(String text) {
        Toast.makeText(set_up_curve_data.this, text, Toast.LENGTH_SHORT).show();
    }
    private void goingBacktoMainpage() {
        Intent backtoMainpage = new Intent(this, MainActivity.class);
        startActivity(backtoMainpage);
    }
    private void backToInputs() {
        Intent backToInputs = new Intent(this, set_up_curve_data.class);
        startActivity(backToInputs);
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
