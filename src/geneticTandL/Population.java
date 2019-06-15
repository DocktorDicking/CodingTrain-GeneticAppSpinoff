package geneticTandL;

import geneticTandL.config.Config;
import geneticTandL.populationRunners.GenerateRunner;
import geneticTandL.utils.PopulationUtils;

import java.util.ArrayList;

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

    public ArrayList<DNA> population; //Array to hold the current population
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
     * @param initPopulation Create a populated population (for initialization)
     */
    public Population(boolean initPopulation) {
        this.target = Config.target;
        this.mutationRate = Config.mutationRate;
        this.maximumPopulation = Config.maxPopulation;
        this.population = new ArrayList<>();
        this.generations = 0;
        this.best = "";

        if (initPopulation) {
            for (int i = 0; i < this.maximumPopulation; i++) {
                this.population.add(new DNA(this.target.length()));
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
                fitness = 0.01; //Minimal fitness to be added once to mating pool. Maybe remove?
            }
            double n = Math.floor(fitness * 100);
            for (int j = 0; j < n; j++) {
                this.matingPool.add(this.population.get(i));
            }

        }
    }

    public void generate(Population population) {
        //Refill the population with children from the mating pool.
        ArrayList<Population> populations = splitPopulation(population);
        ArrayList<Population> newPopulations = new ArrayList<>();

        if (populations.size() == Config.maxThreads) {
            for (int i = 0; i < Config.maxThreads; i++) {
                GenerateRunner runnable = new GenerateRunner(populations.get(i), matingPool);
                Thread thread = new Thread(runnable);
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                newPopulations.add(runnable.getPopulation());
            }
            this.population = PopulationUtils.mergePopulation(newPopulations);
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
     * Splits population object into smaller population objects and adds them to an ArrayList.
     * Before checking it checks if population is dividable by 2. (even)
     * <p>
     * Theoretically is the population size always dividable by 2 since the size depends on the CPU cores and CPU
     * cores normally have a even number of cores.
     *
     * @param population
     * @return ArrayList
     */
    public ArrayList<Population> splitPopulation(Population population) {
        ArrayList<Population> populations = new ArrayList<>();
        int newArraySize = (population.size() / Config.maxThreads);
        int pointer = 0;
        if (population.size() % 2 == 0) {
            for (int i = 0; i < Config.maxThreads; i++) {
                if (!(pointer > population.size())) {
                    Population subPopulation = new Population(false);
                    for (int j = 0; j < newArraySize; j++) {
                        subPopulation.population.add(population.getDNA(j + pointer));
                    }
                    populations.add(subPopulation);
                    pointer += newArraySize;
                } else {
                    break;
                }
            }
        }
        return populations;
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
        return this.maximumPopulation;
    }

    public double getMutationRate() {
        return mutationRate * 100;
    }

    public DNA getDNA(int index) {
        return this.population.get(index);
    }

    public void setPopulation(ArrayList<DNA> population) {
        this.population = population;
    }

    //METHODS USED FOR TESTING
    public int size() {
        return this.population.size();
    }

    public void remove(int i) {
        this.population.remove(i);
    }

    public void add(int i, DNA child) {
        this.population.add(i, child);
    }
}
