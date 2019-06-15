package geneticTandL;


import geneticTandL.config.Config;

/**
 * @Author: Jim van Wieringen
 * @Project Name: geneticApp
 * @Function: Main
 * @Date: May 1, 2019 2:55:39 PM
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        Processor processor = new Processor();
        processor.setUp(Config.target, Config.mutationRate, Config.maxPopulation);
        processor.draw();
    }
}
