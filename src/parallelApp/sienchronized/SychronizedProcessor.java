package parallelApp.sienchronized;

import parallelApp.model.DNA;

import java.util.ArrayList;

/**
 * Processor with functionality that will be run on one thread. Does not need to be runnable since were only using one
 * instance of this object. The main purpose of the SynchonizedProcessor is synchonizing the populations and performing
 * functions that need to be run on one "main" population.
 */
public class SychronizedProcessor {
    private ArrayList<DNA> mainPopulation; //The synchronized population
    private ArrayList<DNA> matingPool; //Array holds best specimen.
    private int generations; //Number of generations.
    private String target; //Target String
    private boolean finished; //Did we find the phrase?
    private String best; //Best phrase found in a generation.
    private double perfectScore = 1.0;

    public void getAverageFitness() {}
    public void naturalSelection() {
        //p5jsMap();
        }
    public void evaluate() {}


    public double p5jsMap(double n, double start1, double stop1, double start2, double stop2, boolean withinBounds) {
        //constrain();
        return 0;
    }
    public double constrain(double n, double low, double high) {
        return Math.max(Math.min(n, high), low);
    }




}
