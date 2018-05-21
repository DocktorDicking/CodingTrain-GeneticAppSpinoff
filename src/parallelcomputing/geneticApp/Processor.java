package parallelcomputing.geneticApp;

/**
 * Main geneticApp file. Calls, setUp, draw etc...
 */
public class Processor {
    private Population population;

    public void setUp(String target, double mutationRate, int maximumPopulation) {
        this.population = new Population(target,mutationRate,maximumPopulation);
    }

    /**
     * For testing purposes.
     * @return
     */
    public Population getPopArray() {
        return population;
    }
}
