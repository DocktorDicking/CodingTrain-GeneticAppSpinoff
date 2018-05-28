package parallelApp.model;

import java.util.ArrayList;

/**
 * Population model. Population object to hold data.
 */
public class Population {
    private ArrayList<DNA> population;
    private int maximumPopulation;

    public Population(int maximumPopulation) {
        this.maximumPopulation = maximumPopulation;
    }



}
