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
        this.population = new Population(target, mutationRate, maximumPopulation, true);
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

    /**
     * Simple method to test merging method in population entity.
     *
     * @param target
     * @param mutationRate
     * @param maximumPopulation
     */
    public void testPopulationMerge(String target, double mutationRate, int maximumPopulation) {
        System.out.println(this.population.size());
        Population population2 = new Population(target, mutationRate, maximumPopulation, true);
        Population population3 = new Population(target, mutationRate, maximumPopulation, true);
        Population population4 = new Population(target, mutationRate, maximumPopulation, true);
        this.population.mergePopulation(population2, population3, population4);
        System.out.println(this.population.size());
    }
}
