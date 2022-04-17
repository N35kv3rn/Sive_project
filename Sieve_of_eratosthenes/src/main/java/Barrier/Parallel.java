package Barrier;

import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Parallel {
    private boolean[] primeArray;
    public static CyclicBarrier barrier;

    public Parallel(boolean[] primeArray, int numThreads) {

        barrier = new CyclicBarrier(numThreads + 1);
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        try {
            int partitionSize = primeArray.length / numThreads;
            int modPartitionSize = primeArray.length % numThreads;

            for (int j = 0; j < numThreads; j++) {
                int start = j * partitionSize;
                int end = (j + 1) * partitionSize + modPartitionSize;
                executor.execute(new Sieve(primeArray, start, end, barrier));
            }

            executor.shutdown();
            // Wait for all threads to finish
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
