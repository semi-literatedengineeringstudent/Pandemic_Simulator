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
    private float[] timeArray;
    /**
     * to endure good accuracy, we estimate usinh Euler estimation for every 0.1 days.
     */
    private final float timeStep = 10/100;
    /**
     * array storing number of susceptible population that could be infected by infected people.
     */
    private float[] susceptible;
    /**
     * initial number of susceptible people, which is total population - infected initially.
     */
    private float initialSusceptible;
    /**
     * array storing number of infected people at each time step.
     */
    private float[] infected;
    /**
     * initial number of infected people.
     */
    private float initialInfected;
    /**
     * array storing number of recovered people that are impervious of the virus.
     */
    private float[] recovered;
    /**
     * initial recovered population which is always 0 as no one is recovered at day 1.
     */
    private final float initialRecovered = 0;
    /**
     * an array storing number of total population each day, which changes as people dead.
     */
    private float[] totalPopulation;

    private float[] fatality;



    SIR_model_handler(final float initialtotalPopulationInput,
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
        dataEntrysetup();

    }
    private void arraySetup() {
        timeArray = new float[(int) (pandemicPeriod / timeStep)];
        timeArray[0] = 0;
        for (int i = 1; i < timeArray.length; i++) {
            timeArray[i] = timeArray[i - 1] + timeStep;
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
            susceptible[i + 1] = susceptible[i] + dSdt * timeStep;
            infected[i + 1] = infected[i] + dIdt * timeStep;
            recovered[i + 1] = recovered[i] + dRdt * timeStep;
            totalPopulation[i + 1] = totalPopulation[i] + dPdt;
            fatality[i + 1] = fatality[i] + dDdt;
        }
    }

    private void dataEntrysetup() {
        ArrayList<Entry> susceptibleList = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            susceptibleList.add(new Entry((float) timeArray[i],(float)  susceptible[i]));
        }
        ArrayList<Entry> infectedList = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            susceptibleList.add(new Entry((float) timeArray[i],(float)  infected[i]));
        }
        ArrayList<Entry> recoveredList = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            recoveredList.add(new Entry((float) timeArray[i],(float)  recovered[i]));
        }
        ArrayList<Entry> totolpopulationList = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            totolpopulationList.add(new Entry((float) timeArray[i],(float)  totalPopulation[i]));
        }
        ArrayList<Entry> fatalityList = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            totolpopulationList.add(new Entry((float) timeArray[i],(float)  fatality[i]));
        }
    }

}


