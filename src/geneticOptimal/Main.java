package geneticOptimal;

import geneticOptimal.config.Config;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Jim van Wieringen
 * @Project Name: geneticApp
 * @Function: Main
 * @Date: August 15, 2019 2:55:39 PM
 * @version: 1.1
 */
public class Main {
    static finishLock lock = new finishLock();

    public static void main(String[] args) {
        for (int i = 0; i < Config.maxThreads; i++) {
            int core = (i + 1);
            Thread t = new Thread(() -> {
                Processor processor = new Processor();
                processor.setUp(Config.target, Config.mutationRate, Config.maxPopulation, core);
                processor.draw();
            });
            t.start();
        }
    }
}

class finishLock {
    private boolean finished = false;
    private final Lock lock = new ReentrantLock();

    public boolean isFinished() {
        lock.lock();
        try {
            return this.finished;
        } finally {
            lock.unlock();
        }
    }

    public void setFinished(boolean finished) {
        lock.lock();
        try {
            this.finished = finished;
        } finally {
            lock.unlock();
        }
        this.finished = finished;
    }
}
