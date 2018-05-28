package parallelApp.sienchronized;

import parallelApp.model.DNA;

import java.util.ArrayList;

/**
 * Processor with functionality that will be run on one thread. Does not need to be runnable since were only using one
 * instance of this object. The main purpose of the SynchonizedProcessor is synchonizing the populations and performing
 * functions that need to be run on one "main" population.
 */
public class SychronizedProcessor {
    private ArrayList<DNA> matingPool; //Array holds best specimen.
    private int generations; //Number of generations.
    private String target; //Target String
    private boolean finished; //Did we find the phrase?
    private String best; //Best phrase found in a generation.



}
