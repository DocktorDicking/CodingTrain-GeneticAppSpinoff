package parallelcomputing;

import parallelcomputing.geneticApp.Population;
import parallelcomputing.geneticApp.Processor;

import java.util.Random;


/**
 * @Author: Tom Scholten & Jim van Wieringen
 * @Project Name: ParallelComputing
 * @Function: Main
 * @Date: May 17, 2018 2:55:39 PM
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        String target = "To be or not to be, that is the question.";
        double mutationRate = 0.01;
        int maxPopulation = 1000;

        //Test population and DNA.
//        test1(target, mutationRate, maxPopulation);

        //Test random how random random is.
//        test2(target, mutationRate, maxPopulation);


        /**
         * To Do List:
         *
         * TODO Run application...
         *
         */


    }

    public static void test1(String target, double mutationRate, int maxPopulation) {
        Processor processor = new Processor();
        processor.setUp(target, mutationRate, maxPopulation);
        Population population = processor.getPopArray();

        for (int i = 0; i < population.size(); i++) {
            if (population.getDNA(i).getFitness() > 0.0) {
                System.out.println("Index: " + i + "   " + population.getDNA(i).printDNA() +
                        "  Fitness: " + population.getDNA(i).getFitness());
            }
        }
    }

    public static void test2(String target, double mutationRate, int maxPopulation) {
        Processor processor = new Processor();
        processor.setUp(target, mutationRate, maxPopulation);
        Population population = processor.getPopArray();
        Random random = new Random();

        for (int i = 0; i < population.size(); i++) {
            int a = (int) Math.floor(random.nextDouble() * population.size());
            System.out.println(a);
        }
    }
}
