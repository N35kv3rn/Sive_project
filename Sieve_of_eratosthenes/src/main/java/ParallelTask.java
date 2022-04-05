import java.util.concurrent.RecursiveAction;

public class ParallelTask extends RecursiveAction {

    int first;
    int last;
    int threshold = 1000;
    int index;


    private final boolean[] primeArray;
    public ParallelTask(boolean[] primeArray, int first, int last, int index) {
        this.primeArray = primeArray;
        this.first = first;
        this.last = last;
        this.index = index;
    }


    protected void compute() {
        try {
            if (last - first < threshold) {
                calculate(primeArray);
            } else {
                int middle = (last + first) / 2;
                ParallelTask t1 = new ParallelTask(primeArray, first, middle+1, index);
                ParallelTask t2 = new ParallelTask(primeArray, middle+1, last, index);
                invokeAll(t1,t2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    protected boolean[] calculate(boolean[] primeArray) {
        try {
            for (int i = index; i < last; i++) {
                if(primeArray[i] && i*i < primeArray.length) {
                    for(int j = i*i; j < last; j+=i) {
                        primeArray[j] = false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return primeArray;
    }
}
