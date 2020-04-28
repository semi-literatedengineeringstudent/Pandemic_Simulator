package com.example.pandemicsimulator.Math_job;

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
     * array storing number of susceptible population that could be infected by infected people.
     */
    public float[] susceptible;
    /**
     * array storing number of infected people at each time step.
     */
    public float[] infected;
    /**
     * initial number of infected people.
     */
    private float initialInfected;
    /**
     * array storing number of recovered people that are impervious of the virus.
     */
    public float[] recovered;
    /**
     * an array storing number of total population each day, which changes as people dead.
     */
    public float[] totalPopulation;
    /**
     * an array storing number of people that don't make it each day.
     */

    public float[] fatality;

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
    }

    /**
     * set up array for data curve . This method will be called in constructor as a state of SIR curve object.
     */
    private void arraySetup() {
        double timeStep = 0.1;
        //to endure good accuracy, we estimate using Euler estimation for every 0.1 days.
        timeArray = new float[(int) (pandemicPeriod / timeStep)];
        timeArray[0] = 0;
        for (int i = 1; i < timeArray.length; i++) {
            timeArray[i] = timeArray[i - 1] + (float) timeStep;
        }
        susceptible = new float[timeArray.length];
        float initialSusceptible = (initialtotalPopulation - initialInfected);
        //initial number of susceptible people, which is total population - infected initially.
        infected = new float[timeArray.length];
        recovered = new float[timeArray.length];
        float initialRecovered = 0;
        //initial recovered population which is always 0 as no one is recovered on day 1.
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
            // rate of change of death...
            float dDdt = deathRatio * infected[i];

            // euler's estimation. x(t) + dx(t)/dt * dt for next day.
            // basic calculus. (fns(t+delt)-fns(t))/dt = dfns/dt.
            susceptible[i + 1] = susceptible[i] + dSdt * (float) timeStep;
            infected[i + 1] = infected[i] + dIdt * (float) timeStep;
            recovered[i + 1] = recovered[i] + dRdt * (float) timeStep;
            totalPopulation[i + 1] = totalPopulation[i] + dPdt * (float) timeStep;
            fatality[i + 1] = fatality[i] + dDdt * (float) timeStep;
        }
    }

}


