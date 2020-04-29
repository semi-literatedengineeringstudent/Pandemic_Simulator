package com.example.pandemicsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button existingVirus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         ** to preset virus page
         */
        existingVirus = findViewById(R.id.existingVirus);
        existingVirus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toExistingVirus();
            }
        });

        /**
         * to parameter page
         */
        Button plugParameter = findViewById(R.id.plugVirusParameter);
        plugParameter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startPlugParameter();
            }
        });

    }

    /**
     * to parameter page
     */
    public void startPlugParameter() {
        Intent toplugParameter = new Intent(MainActivity.this, set_up_curve_data.class);
        startActivity(toplugParameter);
    }

    /**
     * to preset virus page
     */
    public void toExistingVirus() {
        Intent toExistingVirus = new Intent(this, activity_existing_virus.class);
        startActivity(toExistingVirus);
    }

}
