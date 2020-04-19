package com.example.pandemicsimulator.Math_job;

public class SIR_model_handler {
    private double initialPopulation;
    private double infectionRate;
    private double deathRate;
    private double healingRate;
    private int infectionPeriod;
    private double[] timeArray;
    private final double timeStep = 0.01;
    private final double initialS = 1;

    SIR_model_handler(final double initialPopulationInput,
                      final double infectionRateInput,
                      final double deathRateInput,
                      final double healingRateInput,
                      final int infectionPeriodInput) {
        initialPopulation = initialPopulationInput;
        infectionRate = infectionRateInput;
        deathRate = deathRateInput;
        healingRate = healingRateInput;
        infectionPeriod = infectionPeriodInput;
        timeArray = new double[(int) (infectionPeriod / timeStep)];
        timeArray[0] = 0;
        for (int i = 1; i < timeArray.length; i++) {
            timeArray[i] = timeArray[i - 1] + timeStep;
        }

    }
}
