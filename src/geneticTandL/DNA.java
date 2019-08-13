package geneticTandL;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Author: Jim van Wieringen
 * @Project Name: geneticApp
 * @Date: Juli 1, 2019
 * @version: 1.0
 */


/**
 * Holds all dna data.
 */
public class DNA {

    //Holds data for population. Each element in genes is an array of characters forming the random string.
    private Random random = new Random();
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
        String alphabet = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789[];'./, -=!@#$%^&*()";
        char character = alphabet.charAt(this.random.nextInt(alphabet.length()));
        return character;
    }

    @SuppressWarnings("Duplicates")
    public void calcFitness(String target) {
        double score = 0.0;
        for (int i = 0; i < this.genes.size(); i++) {
            char targetChar = target.charAt(i);
            if (this.genes.get(i).equals(targetChar)) {
                score++;
            }
        }
        this.fitness = score / target.length();
    }

    /**
     * Generates a new child using a partner/parent.
     *
     * @param partner
     * @return
     */
    @SuppressWarnings("Duplicates")
    public DNA crossover(DNA partner) {
        DNA child = new DNA(this.length);
        int midPoint = this.random.nextInt(this.genes.size());

        for (int i = 0; i < this.genes.size(); i++) {
            if (i > midPoint) {
                child.genes.remove(i);
                child.genes.add(i, this.genes.get(i));
            } else {
                child.genes.remove(i);
                child.genes.add(i, partner.genes.get(i));
            }
        }
        return child;
    }

    // Based on a probability a character gets replaced with a new random character.
    @SuppressWarnings("Duplicates")
    public void mutate(double mutationRate) {
        for (int i = 0; i < this.genes.size(); i++) {
            double randomD = this.random.nextDouble();
            if (randomD < mutationRate) {
                this.genes.remove(i);
                this.genes.add(i, this.getRandomChar());
            }
        }
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
