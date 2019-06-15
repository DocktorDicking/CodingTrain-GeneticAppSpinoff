package geneticTandL.populationRunners;

import geneticTandL.Population;

public class InitRunner implements Runnable{
    private Population population;

    @Override
    public void run() {
        population = new Population(true);
    }

    public Population getPopulation() {
        return this.population;
    }
}
