package com.example.pandemicsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button plugParameter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        plugParameter = (Button) findViewById(R.id.plugVirusParameter);
        plugParameter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startPlugParameter();
            }
        });

    }
    public void startPlugParameter() {
        Intent toplugParameter = new Intent(MainActivity.this, set_up_curve_data.class);
        startActivity(toplugParameter);
    }




}
