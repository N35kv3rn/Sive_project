import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ForkJoinPool;

public class Parallel {
    public static boolean[] get(boolean[] primeArray) {
        int numProcessors = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = ForkJoinPool.commonPool();
        //CyclicBarrier barrier = new CyclicBarrier(Runtime.getRuntime().availableProcessors(), new )

        for (int i = 2; i < primeArray.length; i++) {
            if(primeArray[i]) {
                ParallelTask task = new ParallelTask(primeArray, i, primeArray.length, i);
                pool.invoke(task);

            }
        }
        return primeArray;
    }
}
