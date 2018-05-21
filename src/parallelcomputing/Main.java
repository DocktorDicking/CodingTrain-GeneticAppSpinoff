package parallelcomputing;

import parallelcomputing.geneticApp.Population;
import parallelcomputing.geneticApp.Processor;


/**
 * @Author: Tom Scholten & Jim van Wieringen
 * @Project Name: Main
 * @Function: Main
 * @Date: May 17, 2018 2:55:39 PM
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        String target = "To be or not to be, that is the question.";
        double mutationRate = 0.01;
        int maxPopulation = 10000;

        //Test population and DNA.
        Processor processor = new Processor();
        processor.setUp(target, mutationRate, maxPopulation);
        Population population = processor.getPopArray();

        for (int i = 0; i < population.size(); i++) {
            if (population.getDNA(i).getFitness() > 0.0) {
                System.out.println("Index: " + i + "   " + population.getDNA(i).printDNA() +
                        "  Fitness: " + population.getDNA(i).getFitness());
            }
        }


        /**
         * To Do List:
         *
         * DONE: setUp()
         * - Initialize: Create a population of N elements, each with randomly generated DNA
         *
         * TODO: Draw()
         * - Selection: Evaluate the fitness of each element of the population and build a mating pool.
         * - Reproduction: Repeat N times:
         *
         * a) Pick two parents with probability according to relative fitness.
         * b) Crossover - create a "child" by combining the DNA of these two parents.
         * c) Mutation - mutate the child's DNA based on a given probability.
         * d) Add the new child to a new population.
         *
         * Finally replace old population with the new population and return to step 2.
         *
         */


    }
}
