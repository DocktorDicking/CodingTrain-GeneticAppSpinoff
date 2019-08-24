package geneticRmi;

/**
 * @Author: Jim van Wieringen
 * @Project Name: ParallelComputing
 * @Date: Juli 21, 2019
 * @version: 1.0
 */


import geneticRmi.config.Config;
import geneticRmi.rmi.GappRmi;
import geneticRmi.rmi.GappRmiClass;

import java.util.concurrent.TimeUnit;

/**
 * Main geneticApp file. Calls, setUp, draw etc...
 */
public class Processor {
    private String name = "";
    private Population population;
    long elapsedTime;

    public void setUp(String target, double mutationRate, int maximumPopulation, String name) {
        this.population = new Population(target, mutationRate, maximumPopulation, true);
        this.name = name;
        this.population.evaluate();
    }

    public void draw() {
        long startTime = System.nanoTime();
        int oldGen = population.getGenerations();
        while (!population.isFinished()) {
            //Generate mating pool
            population.naturalSelection();
            //Create next generation
            population.generate();
            //Calculate fitness
            population.calcFitness();

            population.evaluate();

            //only print every x gens.
            if ((population.getGenerations() - 100) == oldGen) {
                this.displayInfo();
                oldGen = population.getGenerations();
            }
        }
        if (population.getRecord() == Config.perfectScore) {
            long endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            String finalOutput = this.name + " finished!\n"
                    + "Generated: " + population.getGenerations() + " Generations\n"
                    + "Generated: " + population.getGenerations() * population.getMaximumPopulation() + " Phrases\n"
                    + "Best phrase: " + population.getBest() + "\n"
                    + "Elapsed time in milliseconds: " + TimeUnit.NANOSECONDS.toMillis(elapsedTime) + "\n"
                    + "Elapsed time in seconds: " + TimeUnit.NANOSECONDS.toSeconds(elapsedTime);
            GappRmiClass.finalOutput = finalOutput;
            System.out.println(finalOutput);
        }
    }

    public void displayInfo() {
        String core = this.name + " status:";
        String answer = "Best phrase: " + population.getBest();
        String stats =
                "Total generations: " + population.getGenerations() + "\n" +
                        "Average fitness: " + (int) population.getAverageFitness() + "%\n" +
                        "Population limit: " + population.getMaximumPopulation() + "\n" +
                        "Mutation rate: " + (int) population.getMutationRate() + "%";
        System.out.print("\r" + core + "\n" + answer + "\n" + stats + "\n\n");
    }
}
