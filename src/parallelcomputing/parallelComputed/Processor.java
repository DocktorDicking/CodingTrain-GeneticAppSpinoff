package parallelcomputing.parallelComputed;

import java.util.ArrayList;

public class Processor {

    public void setUp() {
        ParallelPopulation pp = new ParallelPopulation();
        Thread t1 = new Thread(pp);
            t1.start();
            pp.setInit(false);
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<String> al = pp.getList();
            for (String a : al) {
                System.out.println(a);

        }




    }

}
