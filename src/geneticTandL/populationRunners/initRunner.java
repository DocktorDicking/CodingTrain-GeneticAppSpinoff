package geneticTandL.populationRunners;

import geneticTandL.Population;
import geneticTandL.config.config;

public class initRunner implements Runnable{
    private Population population;

    public void run() {
        population = new Population(true);
        population.evaluate();
    }

    public Population getPopulation() {
        return this.population;
    }
}
