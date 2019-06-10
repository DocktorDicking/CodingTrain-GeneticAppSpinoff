package geneticTandL.config;

/**
 * Simple configuration class holding all configuration variables as global variables.
 */
public class config {
    public static String target = "To be or not to be. That is the question.";
    public static double mutationRate = 0.01;
    public static int maxPopulation = 10000;
    public static int maxThreads = Runtime.getRuntime().availableProcessors();
}
