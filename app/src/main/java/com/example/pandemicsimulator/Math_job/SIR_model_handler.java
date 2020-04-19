package com.example.pandemicsimulator.Math_job;

public class SIR_model_handler {
    /**
     * initial population of the modeled group.
     */
    private double initialtotalPopulation;
    /**
     * average number of people an infected people can infect each day.
     */
    private double infectionRate;
    /**
     * ratio of death among infected.
     */
    private double deathRate;
    /**
     * ratio of recover among infected.
     */
    private double recoverRate;
    /**
     * duration of pandemic viewers want to see in days.
     */
    private int infectionPeriod;
    /**
     * array of time as horizontal axis of the graph.
     */
    private double[] timeArray;
    /**
     * to endure good accuracy, we estimate usinh Euler estimation for every 0.1 days.
     */
    private final double timeStep = 0.1;
    /**
     * array storing number of susceptible population that could be infected by infected people.
     */
    private double[] susceptible;
    /**
     * initial number of susceptible people, which is total population - infected initially.
     */
    private double initialSusceptible;
    /**
     * array storing number of infected people at each time step.
     */
    private double[] infected;
    /**
     * initial number of infected people.
     */
    private double initialInfected;
    /**
     * array storing number of recovered people that are impervious of the virus.
     */
    private double[] recovered;
    /**
     * initial recovered population which is always 0 as no one is recovered at day 1.
     */
    private final double initialRecovered = 0;
    /**
     * an array storing number of total population each day, which changes as people dead.
     */
    private double[] totalPopulation;


    SIR_model_handler(final double initialtotalPopulationInput,
                      final double initialInfectedInput,
                      final double infectionRateInput,
                      final double deathRateInput,
                      final double recoverRateInput,
                      final int infectionPeriodInput) {
        initialtotalPopulation = initialtotalPopulationInput;
        initialInfected = initialInfectedInput;
        infectionRate = infectionRateInput;
        deathRate = deathRateInput;
        recoverRate = recoverRateInput;
        infectionPeriod = infectionPeriodInput;
        timeArray = new double[(int) (infectionPeriod / timeStep)];
        timeArray[0] = 0;
        for (int i = 1; i < timeArray.length; i++) {
            timeArray[i] = timeArray[i - 1] + timeStep;
        }
        susceptible = new double[timeArray.length];
        initialSusceptible = (initialtotalPopulation - initialInfected);
        infected = new double[timeArray.length];
        recovered = new double[timeArray.length];
        totalPopulation = new double[timeArray.length];
    }
    public void arraySetup() {
        //initialize susceptible population on day 1.
        susceptible[0] = initialSusceptible;
        // initialize infected population on day 1.
        infected[0] = initialInfected;
        // initialize recovered population on day 1.
        recovered[0] = initialRecovered;
        //initialize total population on day 1.
        totalPopulation[0] = initialtotalPopulation;
        // a for loop that compute quantity we defined above in each time interval based on SIR model.
        for(int i = 0; i < timeArray.length - 1; i++) {
            // rate of change of susceptible population per day.
            // number of people having contact with infected people times ratio
            // of susceptible poeple om total population.
            double dSdt = -infectionRate * infected[i]
                    * (susceptible[i] / totalPopulation[i]);
            // rate of change of infected people/
            // change of susceptible - number of recovered people - number of death.
            double dIdt = infectionRate * infected[i] * (susceptible[i] / totalPopulation[i])
                    - recoverRate * infected[i]
                    - deathRate * infected[i];
            // rate of change of recovered people per day.
            // ratio of recover times number of infected people,
            double dRdt = recoverRate * infected[i];
            // rate of change of total population, which is number of death each day.
            double dPdt = -deathRate * infected[i];

            // euler's estimation. x(t) + dx(t)/dt * dt for next day.
            susceptible[i + 1] = susceptible[i] + dSdt * timeStep;
            infected[i + 1] = infected[i] + dIdt * timeStep;
            recovered[i + 1] = recovered[i] + dRdt * timeStep;
            totalPopulation[i + 1] = totalPopulation[i] + dPdt;
        }
    }
}
