package parallelcomputing.geneticApp;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Author: Jim van Wieringen
 * @Project Name: ParallelComputing
 * @Date: May 21, 2018
 * @version: 1.0
 */


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
    private double perfectScore = 1.0;

    /**
     * Default population constructor. Population holds an array with DNA entities.
     * Population is used to manage DNA entities.
     *
     * @param target            Target string.
     * @param mutationRate      Rate of mutation. 0.01 = 1%
     * @param maximumPopulation The maximum number of DNA entities a population can hold.
     * @param initPopulation    Create a populated population (for initialization)
     */
    public Population(String target, double mutationRate, int maximumPopulation, boolean initPopulation) {
        this.target = target;
        this.mutationRate = mutationRate;
        this.maximumPopulation = maximumPopulation;
        this.population = new ArrayList<>();
        this.generations = 0;
        this.best = "";

        if (initPopulation) {
            for (int i = 0; i < maximumPopulation; i++) {
                population.add(new DNA(this.target.length()));
            }
            this.calcFitness();
        }
    }

    public void calcFitness() {
        for (int i = 0; i < this.population.size() - 1; i++) {
            this.population.get(i).calcFitness(this.target);
        }
    }

    public double getAverageFitness() {
        double total = 0.0;
        for (int i = 0; i < this.population.size(); i++) {
            total += this.population.get(i).getFitness();
        }
        total = total * 100 / this.population.size();
        return Math.floor(total);
    }

    public void naturalSelection() {
        this.matingPool = new ArrayList<>(); //clears matingPool.
        double topFitness = 0;
        for (int i = 0; i < this.population.size(); i++) {
            if (this.population.get(i).getFitness() > topFitness) {
                topFitness = this.population.get(i).getFitness();
            }
        }

        // Based on fitness, each member will get added to the mating pool a certain number of times
        // a higher fitness = more entries to the mating pool = more likely to be picked as a parent
        // a lower fitness = fewer entries to the mating pool = less likely to be picked as a parent
        for (int i = 0; i < this.population.size(); i++) {
            double fitness;
            if (this.population.get(i).getFitness() > 0.001) {
                fitness = this.p5jsMap(this.population.get(i).getFitness(), 0, topFitness, 0, 1, false);
            } else {
                fitness = 0.01; //Minimal fitness to be added once to mating pool.
            }
            double n = Math.floor(fitness * 100);
            for (int j = 0; j < n; j++) {
                this.matingPool.add(this.population.get(i));
            }

        }
    }

    public void generate() {
        Random random = new Random();

        //Refill the population with children from the mating pool.
        for (int i = 0; i < this.population.size(); i++) {
            int a = random.nextInt(this.matingPool.size());
            int b = random.nextInt(this.matingPool.size());
            DNA partnerA = this.matingPool.get(a);
            DNA partnerB = this.matingPool.get(b);
            DNA child = partnerA.crossover(partnerB);
            child.mutate(this.mutationRate);
            this.population.remove(i);
            this.population.add(i, child);
        }
        this.generations++;
    }

    /**
     * Function used in p5.js. Could nog find a equal java function.
     * This function is based upon the j5.js sourcecode.
     *
     * @return
     */
    public double p5jsMap(double n, double start1, double stop1, double start2, double stop2, boolean withinBounds) {
        //TODO Review formula. After some calculations it feels kind of useless.
        double newDouble = (n - start1) / (stop1 - start1) * (stop2 - start2) + start2;
        if (!withinBounds) {
            return newDouble;
        }
        if (start2 < stop2) {
            return this.constrain(newDouble, start2, stop2);
        } else {
            return this.constrain(newDouble, stop2, start2);
        }
    }

    public double constrain(double n, double low, double high) {
        return Math.max(Math.min(n, high), low);
    }

    public void evaluate() {
        double record = 0.0;
        int index = 0;
        for (int i = 0; i < this.population.size(); i++) {
            if (this.population.get(i).getFitness() > record) {
                index = i;
                record = this.population.get(i).getFitness();
            }
        }

        this.best = this.population.get(index).printDNA();
        if (record == this.perfectScore) {
            this.finished = true;
        }
    }

    /**
     * Returns Populations combined into one population. Method made to merge multiple thread outputs.
     * Three implementations of mergePopulation. 2, 3, 4.
     * TODO This is still a concept. Still drawing ideas.
     *
     * @return
     */
    public void mergePopulation(Population a, Population b, Population c) {
        for (int i = 0; i < maximumPopulation; i++) {
            this.population.add(a.getDNA(i));
            if (b != null) {
                this.population.add(b.getDNA(i));
            }
            if (c != null) {
                this.population.add(c.getDNA(i));
            }
        }
    }

    public String getBest() {
        return best;
    }

    public int getGenerations() {
        return generations;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getMaximumPopulation() {
        return maximumPopulation;
    }

    public double getMutationRate() {
        return mutationRate * 100;
    }

    public DNA getDNA(int index) {
        return this.population.get(index);
    }

    //METHODS USED FOR TESTING
    public int size() {
        return this.population.size();
    }

}
