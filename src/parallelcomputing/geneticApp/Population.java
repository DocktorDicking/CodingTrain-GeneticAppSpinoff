package parallelcomputing.geneticApp;

import java.util.ArrayList;

/**
 * Manages the array of all elements in the population.
 */
public class Population {

    private ArrayList<DNA> population; //Array to hold the current population
    private ArrayList<DNA> matingPool; //Array holds best specimen.
    private int generations; //Number of generations.
    private boolean finished; //Did we find the phrase?
    private String target; //Target String
    private double mutationRate;
    private String best;
    private int maximumPopulation;

    public Population(String target, double mutationRate, int maximumPopulation) {
        this.target = target;
        this.mutationRate = mutationRate;
        this.maximumPopulation = maximumPopulation;
        this.population = new ArrayList<>();

        for (int i = 0; i < maximumPopulation ; i++) {
            population.add(new DNA(this.target.length()));
        }
        this.matingPool = new ArrayList<>();
        this.calcFitness();
    }

    public void calcFitness() {
        for (int i = 0; i < this.population.size() ; i++) {
            this.population.get(i).calcFitness(this.target);
        }
    }

    public void naturalSelection() {
        //TODO: Generate mating pool
    }

    public DNA getDNA(int index) {
        return this.population.get(index);
    }

    public int size() {
        return this.population.size();
    }





    //TODO: Add function to retrieve data from arraylist pupulation.


}
