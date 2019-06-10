package genecticOptimal.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class executor {
    private int maxThreads;
    private ExecutorService executor;

    public executor(int maxThreads) {
        this.maxThreads = maxThreads;
        this.executor = Executors.newFixedThreadPool(maxThreads);
    }

    public void submit(Runnable task) {
        executor.submit(task);
    }
}
