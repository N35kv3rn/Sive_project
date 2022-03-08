import java.util.concurrent.ForkJoinPool;

public class Parallel {
    public static boolean[] get(boolean[] primeArray) {
        int numProcessors = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = ForkJoinPool.commonPool();
        ParallelTask task = new ParallelTask(primeArray, 0, primeArray.length, 2);
        pool.invoke(task);

        return primeArray;
    }
}
