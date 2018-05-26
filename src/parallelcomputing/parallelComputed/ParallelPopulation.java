package parallelcomputing.parallelComputed;

import java.util.ArrayList;

public class ParallelPopulation implements Runnable {
    private boolean init = true;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    public void run() {
        if (init) {
            list.add("Kek");
            list.add("Kek");
        }

        if (!init){
            list.add("NewKek");
        }
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public ArrayList<String> getList() {
        return list;
    }
}
