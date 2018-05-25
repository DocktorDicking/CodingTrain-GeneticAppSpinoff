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
        String target = "To be or not to be.";
        double mutationRate = 0.01;
        int maxPopulation = 1000;

        runApp(target, mutationRate, maxPopulation);
    }

    public static void runApp(String target, double mutationRate, int maxPopulation) {
        Processor processor = new Processor();
        processor.setUp(target, mutationRate, maxPopulation);
        processor.draw();
    }
}
