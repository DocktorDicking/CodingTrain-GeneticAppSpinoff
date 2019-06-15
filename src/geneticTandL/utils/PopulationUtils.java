package geneticTandL.utils;

import geneticTandL.DNA;
import geneticTandL.Population;

import java.util.ArrayList;
import java.util.Collections;

public class PopulationUtils {

    /**
     * Returns Populations combined into one population. Method made to merge multiple thread outputs.
     * Three implementations of mergePopulation. 2, 3, 4.
     * TODO This is still a concept. Still drawing ideas.
     *
     * @return ArrayList
     */
    public static ArrayList mergePopulation(ArrayList<Population> populations) {
        ArrayList<DNA> population = new ArrayList<>();
        for (int i = 0; i < populations.size(); i++) {
            Population cursor = populations.get(i);
            for (int j = 0; j < cursor.size(); j++) {
                population.add(cursor.getDNA(i));
            }
        }
        Collections.shuffle(population);
        return population;
    }

}
