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

        // Setting up a local array to store the prime numbers from 3 to the square root of N
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
            // Check if we start on an odd number, and set start to an even number if we do.
            if (start % 2 != 0) {
                start--;
            }

            // Setts all odd numbers to false except for 2
            for (int i = start + 2; i < end; i+=2) {
                if(i == 2) {
                    continue;
                }
                primeArray[i] = false;
            }


            for (int i = 3; i < Math.sqrt(primeArray.length); i++) {
                // This is for handling the last value in the array, which is not always reached by the loop
                if(end % i == 0 && end < primeArray.length) {
                    primeArray[end] = false;
                }

                // Setting start to a number that is divisible by i
                if (start % i != 0) {
                    while(true) {
                        if(start % i == 0) {
                            break;
                        }
                        start--;
                    }
                }

                // Checking the localPrimeArray to see if i is prime
                if(localPrimeArray[i]) {
                    // Eliminating all multiples of i
                    for (int j = start + i; j < end; j += i) {
                        if(j == i || j % 2 == 0) {
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
