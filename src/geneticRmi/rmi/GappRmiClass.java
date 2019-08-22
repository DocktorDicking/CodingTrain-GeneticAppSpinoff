package geneticRmi.rmi;

import geneticRmi.Processor;
import geneticRmi.config.Config;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

public class GappRmiClass implements GappRmi {
    public static AtomicBoolean abool = new AtomicBoolean(false);
    private static ThreadPoolExecutor executor;

    @Override
    public void runGapp() {
        System.out.println("Starting genetic-app..");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Config.maxThreads);
        for (int i = 0; i < Config.maxThreads; i++) {
            int coreNum = i + 1;
            Task task = new Task("Task " + coreNum);
            System.out.println("New task added to executor: " + task.getName());
            executor.execute(task);
        }

        //When one thread finishes, reset atomic bool for new tasks.
        while(true) {
            if (!abool.get()) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                abool.set(false);
                break;
            }
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
