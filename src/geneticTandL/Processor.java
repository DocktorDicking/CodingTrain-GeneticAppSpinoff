package geneticTandL;

/**
 * @Author: Jim van Wieringen
 * @Project Name: ParallelComputing
 * @Date: May 21, 2018
 * @version: 1.0
 */

import geneticTandL.config.Config;
import geneticTandL.populationRunners.InitRunner;
import geneticTandL.utils.PopulationUtils;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Main geneticApp file. Calls, setUp, draw etc...
 */
public class Processor {
    private ArrayList<Population> populations = new ArrayList<>();
    private Population population = new Population(false);
    long elapsedTime;

    public void setUp() {
        for (int i = 0; i < Config.maxThreads; i++) {
            InitRunner runnable = new InitRunner();
            Thread thread = new Thread(runnable);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.populations.add(runnable.getPopulation());
        }
        this.population.setPopulation(PopulationUtils.mergePopulation(populations));
        this.population.evaluate();
    }

    public void draw() {
        long startTime = System.nanoTime();
        int oldGen = population.getGenerations();
        while (!population.isFinished()) {
            //Generate mating pool
            population.naturalSelection();
            //Create next generation
            population.generate(population);
            //Calculate fitness
            population.calcFitness();

            population.evaluate();

            //only print every x gens.
            if ((population.getGenerations() - 100) == oldGen) {
                this.displayInfo();
                oldGen = population.getGenerations();
            }
        }
        long endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("Generated: " + population.getGenerations() + " Generations");
        System.out.println("Generated: " + population.getGenerations() * population.getMaximumPopulation() + " Phrases");
        System.out.println("Best phrase: " + population.getBest());
        System.out.println("Elapsed time in milliseconds: " + TimeUnit.NANOSECONDS.toMillis(elapsedTime));
        System.out.println("Elapsed time in seconds: " + TimeUnit.NANOSECONDS.toSeconds(elapsedTime));
    }

    public void displayInfo() {
        String answer = "Best phrase: " + population.getBest();
        String stats =
                "Total generations: " + population.getGenerations() + "\n" +
                        "Average fitness: " + (int) population.getAverageFitness() + "%\n" +
                        "Population limit: " + population.getMaximumPopulation() + "\n" +
                        "Mutation rate: " + (int) population.getMutationRate() + "%";
        System.out.print("\r" + answer + "\n" + stats + "\n\n");
    }

    //For testing.
    public ArrayList<Population> getPopulations() {
        return populations;
    }

    public Population getPopulation() {
        return population;
    }
}
