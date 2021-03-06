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
        String target = "To be or not to be. That is the question.";
        double mutationRate = 0.01;
        int maxPopulation = 10000;

        Processor processor = new Processor();
        processor.setUp(target, mutationRate, maxPopulation);
        processor.draw();
    }
}
