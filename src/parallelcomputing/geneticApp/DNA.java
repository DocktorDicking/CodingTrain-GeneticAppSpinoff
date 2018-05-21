package parallelcomputing.geneticApp;

import java.util.ArrayList;
import java.util.Random;

/**
 * Holds all dna data.
 */
public class DNA {

    //Holds data for population. Each element in genes is an array of characters forming the random string.
    private ArrayList<Character> genes = new ArrayList<>();
    private int fitness;
    private int length;

    public DNA(int length) {
        this.length = length;
        this.fitness = 0;

        for (int i = 0; i < length ; i++) {
            genes.add(getRandomChar());
        }

    }

    private char getRandomChar() {
        Random random = new Random();
        String alphabet = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789[];'./, -=!@#$%^&*()";
        char character = alphabet.charAt(random.nextInt(alphabet.length()));
        return character;
    }

    public void mutate() {

    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public String printDNA() {
        String genes = "";
        for (int i = 0; i < this.genes.size() ; i++) {
            if (genes.equals("")) {
                genes = String.valueOf(this.genes.get(i));
            } else {
                genes += String.valueOf(this.genes.get(i));
            }
        }
        return genes;
    }

    //TODO: Add functions to store and retrieve genes.




}
