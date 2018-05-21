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
    }

    public void calcFitness() {
        //TODO: Add method to calculate fitness of each element in population.
    }

    public void selection() {
        //TODO: Add method to select fittest elements.
    }

    public String printPopulation() {
        String population = "";
        for (int i = 0; i < this.population.size() ; i++) {
            if (population.equals("")) {
                population = this.population.get(i).toString();
            } else {
                population += "/n" + this.population.get(i).toString();
            }
        }
        return population;
    }



    //TODO: Add function to populate the population list radomly.
    //TODO: Add function to retrieve data from arraylist pupulation.


}
