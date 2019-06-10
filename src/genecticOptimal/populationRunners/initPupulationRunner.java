package genecticOptimal.populationRunners;

import genecticOptimal.Population;

import java.util.ArrayList;

public class initPupulationRunner implements Runnable {
    private int id;
    private ArrayList<Population> populations;


    /**
     * Generate new populations from existing populations list.
     * @param id
     * @param populations
     */
    public initPupulationRunner(int id,ArrayList<Population> populations){
        this.id = id;
        this.populations = populations;
    }

    /**
     * Init populations from scratch. Used at start of program.
     * @param id
     */
    public initPupulationRunner(int id){
        this.id = id;
    }

    public void run() {

    }
}
