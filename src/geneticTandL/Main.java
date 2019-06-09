package geneticTandL;


import parallelcomputing.geneticApp.SingleProcessor;

/**
 * @Author: Jim van Wieringen
 * @Project Name: geneticApp
 * @Function: Main
 * @Date: May 1, 2019 2:55:39 PM
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        String target = "To be or not to be. That is the question.";
        double mutationRate = 0.01;
        int maxPopulation = 2500;

        SingleProcessor processor = new SingleProcessor();
        processor.setUp(target, mutationRate, maxPopulation);
        processor.draw();

        //int threads = Runtime.getRuntime().availableProcessors();
    }
}
