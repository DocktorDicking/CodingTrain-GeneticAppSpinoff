package geneticOptimal;

import geneticOptimal.config.Config;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author: Jim van Wieringen
 * @Project Name: geneticApp
 * @Function: Main
 * @Date: August 16, 2019 2:55:39 PM
 * @version: 1.1
 */
public class Main {
    static AtomicBoolean abool = new AtomicBoolean(false);
    static ThreadPoolExecutor executor;

    //TODO: Run stuff core x 2.
    //TODO: RMI (Remote Method Infocation) RMI cleint maken en process uitvoeren. Resultaten terug sturen.
    //Best case RMI situatie is on the fly VPS aanmaken. VPS aanmaken, proces uitvoeren, resultaat binnen hengelen en VPS sluiten.
    public static void main(String[] args) {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Config.maxThreads);
        for (int i = 0; i < Config.maxThreads; i++) {
            int coreNum = i + 1;
            Task task = new Task("Task " + coreNum);
            System.out.println("New task added to executor: " + task.getName());
            executor.execute(task);
        }
    }
}

class Task implements Runnable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Processor processor = new Processor();
        processor.setUp(Config.target, Config.mutationRate, Config.maxPopulation, this.name);
        processor.draw();
    }

    public String getName() {
        return this.name;
    }
}
