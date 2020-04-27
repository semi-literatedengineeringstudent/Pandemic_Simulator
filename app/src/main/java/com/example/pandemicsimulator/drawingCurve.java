package com.example.pandemicsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pandemicsimulator.Math_job.SIR_model_handler;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class drawingCurve extends AppCompatActivity {

    private LineChart SIRcurve;

    private Button toMainPage;

    private Button replugParameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing_curve);

        SIRcurve = (LineChart) findViewById(R.id.drawingCurve);
        float[] timeArray =  getIntent().getFloatArrayExtra("timeArray");
        float[] susceptibleArray = getIntent().getFloatArrayExtra("susceptibleArray");
        float[] infectedArray = getIntent().getFloatArrayExtra("infectedArray");
        float[] recoveredArray = getIntent().getFloatArrayExtra("recoveredArray");
        float[] totalPopulationArray = getIntent().getFloatArrayExtra("totalPopulationArray");
        float[] fatalityArray = getIntent().getFloatArrayExtra("fatalityArray");

        String[] timeStringArray = new String[timeArray.length];
        for (int i = 0; i < timeArray.length; i++) {
            timeStringArray[i] = String.valueOf(timeArray[i]);
        }

        ArrayList<Entry> susceptibleList = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            susceptibleList.add(new Entry((float) timeArray[i] * 50,(float)  susceptibleArray[i]));
        }
        ArrayList<Entry> infectedList = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            infectedList.add(new Entry((float) timeArray[i] * 50,(float)  infectedArray[i]));
        }
        ArrayList<Entry> recoveredList = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            recoveredList.add(new Entry((float) timeArray[i] * 50,(float)  recoveredArray[i]));
        }
        ArrayList<Entry> totalPopulationList = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            totalPopulationList.add(new Entry((float) timeArray[i] * 50,(float)  totalPopulationArray[i]));
        }
        ArrayList<Entry> fatalityList = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            fatalityList.add(new Entry((float) timeArray[i] * 50,(float)  fatalityArray[i]));
        }


        ArrayList<ILineDataSet>  SIRdataset = new ArrayList<>();

        LineDataSet susuceptibleDataset = new LineDataSet(susceptibleList, "susceptible");
        susuceptibleDataset.setDrawCircles(false);
        susuceptibleDataset.setColor(Color.BLACK);

        LineDataSet infectedDataset = new LineDataSet(infectedList, "infected");
        infectedDataset.setDrawCircles(false);
        infectedDataset.setColor(Color.RED);

        LineDataSet recoveredDataset = new LineDataSet(recoveredList, "recovered");
        recoveredDataset.setDrawCircles(false);
        recoveredDataset.setColor(Color.GREEN);

        LineDataSet totalPopulationDataset = new LineDataSet(totalPopulationList, "total population");
        totalPopulationDataset.setDrawCircles(false);
        totalPopulationDataset.setColor(Color.BLUE);

        LineDataSet fatalityDataset = new LineDataSet(fatalityList, "fatality");
        fatalityDataset.setDrawCircles(false);
        fatalityDataset.setColor(Color.GRAY);

        SIRdataset.add(susuceptibleDataset);
        SIRdataset.add(infectedDataset);
        SIRdataset.add(recoveredDataset);
        SIRdataset.add(totalPopulationDataset);
        SIRdataset.add(fatalityDataset);

        SIRcurve.setData(new LineData(SIRdataset));
        SIRcurve.invalidate();


        toMainPage = (Button) findViewById(R.id.backtoMain);
        toMainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                goingBacktoMainpage();
            }
        });

        replugParameter = (Button) findViewById(R.id.replugParameter);
        replugParameter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                toreplugParameter();
            }
        });
    }

    private void goingBacktoMainpage() {
        Intent backtoMainpage = new Intent(drawingCurve.this, MainActivity.class);
        startActivity(backtoMainpage);
    }

    private void toreplugParameter() {
        Intent backtoPlug = new Intent(drawingCurve.this, set_up_curve_data.class);
        startActivity(backtoPlug);
    }



}
