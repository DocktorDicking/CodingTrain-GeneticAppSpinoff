package geneticTandL;


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
        processor.setUp();
        processor.draw();

        //TODO: Documenteren poging tot parallelizatie. Gehele proces parallelizeren in main.

        //testing
//        ArrayList<Population> list = processor.getPopulations();
//        Population population = processor.getPopulation();
//        ArrayList<Population> splitList = population.splitPopulation(population);
//
//        System.out.println("MERGED POPULATION: ");
//        System.out.println(population.size());
//        System.out.println(population.getBest() + " " + population.getAverageFitness());
//
//
//        System.out.println("SPLIT POPULATION: ");
//        System.out.println(splitList.size());
//
//        for (int i = 0; i < splitList.size(); i++) {
//            System.out.println(splitList.get(i).size());
//        }

    }
}
