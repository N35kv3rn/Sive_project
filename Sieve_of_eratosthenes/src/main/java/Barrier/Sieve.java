package Barrier;

import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

public class Sieve implements Runnable {
    private final boolean[] localPrimeArray;
    private boolean[] primeArray;
    private int start;
    private int end;
    private final CyclicBarrier barrier;


    public Sieve(boolean[] primeArray, int start, int end, CyclicBarrier barrier) {
        this.primeArray = primeArray;

        this.start = start;
        this.end = end;
        this.barrier = barrier;

        int lengthSquared = (int)Math.sqrt(primeArray.length);
        boolean[] localPrimeArray = new boolean[lengthSquared + 1];
        Arrays.fill(localPrimeArray, true);

        for (int i = 3; i < lengthSquared; i++) {
            if (localPrimeArray[i]) {
                for (int j = i * i; j < lengthSquared; j += i) {
                    localPrimeArray[j] = false;
                }
            }
        }
        this.localPrimeArray = localPrimeArray;
    }

    @Override
    public void run() {
        try {
            for (int i = 2; i < Math.sqrt(primeArray.length); i++) {
                if(end % i == 0 && end < primeArray.length) {
                    primeArray[end] = false;
                }

                if (start % i != 0) {
                    while(true) {
                        if(start % i == 0) {
                            break;
                        }
                        start--;
                    }
                }

                if(localPrimeArray[i] || primeArray[i]) {
                    for (int j = start + i; j < end; j += i) {
                        if(j==2 || j == i) {
                            continue;
                        }
                        primeArray[j] = false;
                    }
                }
            }
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
