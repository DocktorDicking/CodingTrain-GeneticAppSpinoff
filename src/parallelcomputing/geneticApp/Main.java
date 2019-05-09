package parallelcomputing.geneticApp;


/**
 * @Author: Jim van Wieringen
 * @Project Name: geneticApp
 * @Function: Main
 * @Date: May 1, 2019 2:55:39 PM
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        String target = "To be or not to be.";
        double mutationRate = 0.01;
        int maxPopulation = 1000;
        int threads = Runtime.getRuntime().availableProcessors();


        SingleProcessor processor = new SingleProcessor();
        processor.setUp(target, mutationRate, maxPopulation);
        processor.draw();
    }
}
