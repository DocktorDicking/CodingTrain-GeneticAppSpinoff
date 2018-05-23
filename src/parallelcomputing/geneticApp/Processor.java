package parallelcomputing.geneticApp;

/**
 * @Author: Jim van Wieringen
 * @Project Name: ParallelComputing
 * @Date: May 21, 2018
 * @version: 1.0
 */


/**
 * Main geneticApp file. Calls, setUp, draw etc...
 */
public class Processor {
    private Population population;

    public void setUp(String target, double mutationRate, int maximumPopulation) {
        this.population = new Population(target,mutationRate,maximumPopulation);
    }

    public void draw() {
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
    }

    public void displayInfo() {
        String answer = "Best phrase: " + population.getBest();
        String stats =
                "Total generations: " + population.getGenerations() + "\n" +
                        "Average fitness: " + (int)population.getAverageFitness() + "%\n" +
                        "Total population: " + population.getMaximumPopulation() + "\n" +
                        "Mutation rate: " + (int)population.getMutationRate() + "%";
        System.out.print("\r" + answer + "\n" + stats + "\n\n");
    }

    /**
     * For testing purposes.
     * @return
     */
    public Population getPopArray() {
        return population;
    }
}
