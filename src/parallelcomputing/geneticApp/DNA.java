package parallelcomputing.geneticApp;

import java.util.ArrayList;
import java.util.Random;

/**
 * Holds all dna data.
 */
public class DNA {

    //Holds data for population. Each element in genes is an array of characters forming the random string.
    private ArrayList<Character> genes = new ArrayList<>();
    private double fitness;
    private int length;

    public DNA(int length) {
        this.length = length;
        this.fitness = 0;

        for (int i = 0; i < length; i++) {
            genes.add(getRandomChar());
        }

    }

    private char getRandomChar() {
        Random random = new Random();
        String alphabet = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789[];'./, -=!@#$%^&*()";
        char character = alphabet.charAt(random.nextInt(alphabet.length()));
        return character;
    }

    public void calcFitness(String target) {
        double score = 0.0;
        for (int i = 0; i < this.genes.size(); i++) {
            char targetChar = target.charAt(i);
            if (this.genes.get(i).equals(targetChar) ){
                score++;
            }
        }
        this.fitness = score / target.length();
    }

    public void mutate() {

    }

    public double getFitness() {
        return this.fitness;
    }

    public String printDNA() {
        String genes = "";
        for (Character gene : this.genes) {
            if (genes.equals("")) {
                genes = String.valueOf(gene);
            } else {
                genes += String.valueOf(gene);
            }
        }
        return genes;
    }


}
