package parallelcomputing;

import parallelcomputing.geneticApp.SingleProcessor;
import parallelcomputing.parallelComputed.Processor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
        int threads = 4; //Replace with: Runtime.getRuntime().availableProcessors()

//        runSingleApp(target, mutationRate, maxPopulation);
//        testMerge(target,mutationRate,maxPopulation);

        Processor processor = new Processor();
        processor.setUp();


    }

    public static void runSingleApp(String target, double mutationRate, int maxPopulation) {
        SingleProcessor processor = new SingleProcessor();
        processor.setUp(target, mutationRate, maxPopulation);
        processor.draw();
    }

    public static void testMerge(String target, double mutationRate, int maxPupulation) {
        SingleProcessor processor = new SingleProcessor();
        processor.setUp(target,mutationRate,maxPupulation);
        processor.testPopulationMerge(target, mutationRate, maxPupulation);
    }
}
