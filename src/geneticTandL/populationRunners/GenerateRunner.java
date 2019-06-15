package geneticTandL.populationRunners;

import geneticTandL.DNA;
import geneticTandL.Population;

import java.util.ArrayList;
import java.util.Random;

public class GenerateRunner implements Runnable {
    private Population population;
    private ArrayList<DNA> matingPool;
    private Random random = new Random();

    public GenerateRunner(Population population, ArrayList<DNA> matingPool) {
        this.population = population;
        this.matingPool = matingPool;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.population.size(); i++) {
            int a = random.nextInt(this.matingPool.size());
            int b = random.nextInt(this.matingPool.size());
            DNA partnerA = this.matingPool.get(a);
            DNA partnerB = this.matingPool.get(b);
            DNA child = partnerA.crossover(partnerB);
            child.mutate(population.getMutationRate());
            this.population.remove(i);
            this.population.add(i, child);
        }
    }

    public Population getPopulation() {
        return population;
    }
}
