package com.example.pandemicsimulator.Math_job;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SIR_model_handler {
    /**
     * initial population of the modeled group.
     */
    private float initialtotalPopulation;
    /**
     * average number of people an infected people can infect each day.
     */
    private float infectionContact;
    /**
     * ratio of people got infected after contacting an infected people.
     */
    private float infectionRatio;
    /**
     * ratio of death among infected.
     */

    private float deathRatio;
    /**
     * ratio of recover among infected.
     */
    private float recoverRatio;
    /**
     * duration of pandemic viewers want to see in days.
     */
    private float pandemicPeriod;
    /**
     * array of time as horizontal axis of the graph.
     */
    public float[] timeArray;
    /**
     * to endure good accuracy, we estimate usinh Euler estimation for every 0.1 days.
     */
    public final double timeStep = 0.1;
    /**
     * array storing number of susceptible population that could be infected by infected people.
     */
    public float[] susceptible;
    /**
     * initial number of susceptible people, which is total population - infected initially.
     */
    private float initialSusceptible;
    /**
     * array storing number of infected people at each time step.
     */
    public float[] infected;
    /**
     * initial number of infected people.
     */
    public float initialInfected;
    /**
     * array storing number of recovered people that are impervious of the virus.
     */
    public float[] recovered;
    /**
     * initial recovered population which is always 0 as no one is recovered at day 1.
     */
    public final float initialRecovered = 0;
    /**
     * an array storing number of total population each day, which changes as people dead.
     */
    public float[] totalPopulation;

    public float[] fatality;

    //public ArrayList<String> timeArrayList;

    //public ArrayList<Entry> susceptibleList;

    //public ArrayList<Entry> infectedList;

    //public ArrayList<Entry> recoveredList;

    //public ArrayList<Entry> totalPopulationList;

    //public ArrayList<Entry> fatelityList;



    public SIR_model_handler(final float initialtotalPopulationInput,
                      final float initialInfectedInput,
                      final float infectionContactInput,
                      final float infectionRatioInput,
                      final float deathRatioInput,
                      final float recoverRatioInput,
                      final float pandemicPeriodInput) {
        initialtotalPopulation = initialtotalPopulationInput;
        initialInfected = initialInfectedInput;
        infectionContact = infectionContactInput;
        infectionRatio = infectionRatioInput;
        deathRatio = deathRatioInput;
        recoverRatio = recoverRatioInput;
        pandemicPeriod = pandemicPeriodInput;

        arraySetup();
        //dataEntrysetup();

    }
    private void arraySetup() {
        timeArray = new float[(int) (pandemicPeriod / timeStep)];
        timeArray[0] = 0;
        for (int i = 1; i < timeArray.length; i++) {
            timeArray[i] = timeArray[i - 1] + (float) timeStep;
        }
        susceptible = new float[timeArray.length];
        initialSusceptible = (initialtotalPopulation - initialInfected);
        infected = new float[timeArray.length];
        recovered = new float[timeArray.length];
        totalPopulation = new float[timeArray.length];
        fatality = new float[timeArray.length];

        //initialize susceptible population on day 1.
        susceptible[0] = initialSusceptible;
        // initialize infected population on day 1.
        infected[0] = initialInfected;
        // initialize recovered population on day 1.
        recovered[0] = initialRecovered;
        //initialize total population on day 1.
        totalPopulation[0] = initialtotalPopulation;
        //initialize number of people who did not make it.
        fatality[0] = 0;
        // a for loop that compute quantity we defined above in each time interval based on SIR model.
        for (int i = 0; i < timeArray.length - 1; i++) {
            // rate of change of susceptible population per day.
            // number of people having contact with infected people times ratio
            // of susceptible poeple om total population.
            float dSdt = -infectionContact * infected[i] * infectionRatio
                    * (susceptible[i] / totalPopulation[i]);
            // rate of change of infected people/
            // change of susceptible - number of recovered people - number of death.
            float dIdt = infectionContact * infected[i] * infectionRatio
                    * (susceptible[i] / totalPopulation[i])
                    - recoverRatio * infected[i]
                    - deathRatio * infected[i];
            // rate of change of recovered people per day.
            // ratio of recover times number of infected people,
            float dRdt = recoverRatio * infected[i];
            // rate of change of total population, which is number of death each day.
            float dPdt = -deathRatio * infected[i];

            float dDdt = deathRatio * infected[i];

            // euler's estimation. x(t) + dx(t)/dt * dt for next day.
            susceptible[i + 1] = susceptible[i] + dSdt * (float) timeStep;
            infected[i + 1] = infected[i] + dIdt * (float) timeStep;
            recovered[i + 1] = recovered[i] + dRdt * (float) timeStep;
            totalPopulation[i + 1] = totalPopulation[i] + dPdt * (float) timeStep;
            fatality[i + 1] = fatality[i] + dDdt * (float) timeStep;
        }
    }

    /*private void dataEntrysetup() {
        timeArrayList = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            timeArrayList.add(String.valueOf(timeArray[i]));
        }

        susceptibleList = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            susceptibleList.add(new Entry((float) timeArray[i],(float)  susceptible[i]));
        }
        infectedList = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            infectedList.add(new Entry((float) timeArray[i],(float)  infected[i]));
        }
        recoveredList = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            recoveredList.add(new Entry((float) timeArray[i],(float)  recovered[i]));
        }
        totalPopulationList = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            totalPopulationList.add(new Entry((float) timeArray[i],(float)  totalPopulation[i]));
        }
        fatelityList = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            fatelityList.add(new Entry((float) timeArray[i],(float)  fatality[i]));
        }
    }*/

}


