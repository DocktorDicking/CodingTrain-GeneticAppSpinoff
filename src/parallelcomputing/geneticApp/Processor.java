package parallelcomputing.geneticApp;

/**
 * @Author: Jim van Wieringen
 * @Project Name: ParallelComputing
 * @Date: May 21, 2018
 * @version: 1.0
 */


import java.util.concurrent.TimeUnit;

/**
 * Main geneticApp file. Calls, setUp, draw etc...
 */
public class Processor {
    private Population population;
    long elapsedTime;

    public void setUp(String target, double mutationRate, int maximumPopulation) {
        this.population = new Population(target, mutationRate, maximumPopulation);
    }

    public void draw() {
        long startTime = System.nanoTime();
        while (!population.isFinished()) {
            //Generate mating pool
            population.naturalSelection();
            //Create next generation
            population.generate();
            //Calculate fitness
            population.calcFitness();

            population.evaluate();
            this.displayInfo();
        }
        long endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("Generated " + population.getGenerations() * population.getMaximumPopulation() + " Phrases");
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
}
