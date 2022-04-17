package Barrier;

import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Parallel {
    private boolean[] primeArray;
    private static final int numThreads = Runtime.getRuntime().availableProcessors();
    public static final CyclicBarrier barrier = new CyclicBarrier(numThreads + 1);
    private static ExecutorService executor = Executors.newFixedThreadPool(numThreads);

    public Parallel(boolean[] primeArray) {
        try {
            int partitionSize = primeArray.length / numThreads;
            int modPartitionSize = primeArray.length % numThreads;



            for (int j = 0; j < numThreads; j++) {
                int start = j * partitionSize;
                int end = (j + 1) * partitionSize + modPartitionSize;
                executor.execute(new Sieve(primeArray, start, end, barrier));
            }

            executor.shutdown();
            barrier.await();
            this.primeArray = primeArray;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean[] getPrimeArray() {
        return primeArray;
    }
}
