package MPI;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;

public class Parallel {

    private static final int numThreads = Runtime.getRuntime().availableProcessors();

    public static final CyclicBarrier barrier = new CyclicBarrier(numThreads + 1);

    public static boolean[] get(boolean[] primeArray) {
        try {
            int partitionSize = primeArray.length / numThreads;
            int modPartitionSize = primeArray.length % numThreads;
            //Thread[] threads = new Thread[numThreads];

            ExecutorService executor = java.util.concurrent.Executors.newFixedThreadPool(numThreads);

            for (int i = 2; i < Math.sqrt(primeArray.length); i++) {
                if(primeArray[i]) {
                    for (int j = 0; j < numThreads; j++) {
                        int start = j * partitionSize;
                        int end = (j + 1) * partitionSize;
                        if (j == numThreads - 1) {
                            end += modPartitionSize;
                        }
                        executor.execute(new Sieve(primeArray, start, end, i));
                        //threads[j] = new Thread(new Sieve(primeArray, start, end, i));
                        //threads[j].start();
                    }

                barrier.await();
                barrier.reset();
                }
            }

            executor.shutdown();

        } catch (Exception e) {
            System.out.println(e);
        }


        return primeArray;
    }

}
