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

        // Lokalt array med primtall til roten av array lengden
        int lengthSquared = (int)Math.sqrt(primeArray.length);
        boolean[] localPrimeArray = new boolean[lengthSquared + 1];
        Arrays.fill(localPrimeArray, true);

        for (int i = 3; i < lengthSquared; i++) {
            if (localPrimeArray[i]) {
                for (int j = i * i; j < lengthSquared; j += i) {
                    localPrimeArray[j] = false;
                    primeArray[j] = false;
                }
            }
        }
        this.localPrimeArray = localPrimeArray;
    }

    @Override
    public void run() {
        try {
            // Sjekker om vi starter på et oddetall, og setter start til et partall om vi gjør det.
            if (start % 2 != 0) {
                start--;
            }
            // Setter alle 2er til false
            for (int i = start + 2; i < end; i+=2) {
                if(i == 2) {
                    continue;
                }
                primeArray[i] = false;
            }

            // Setter alle ikke primtall til false fra 3
            for (int i = 3; i < Math.sqrt(primeArray.length); i++) {
                // TODO: Skrive om logikken her.
                if(end % i == 0 && end < primeArray.length) {
                    primeArray[end] = false;
                }
                // TODO: Skrive om logikken her. Tror denne løkka haler ut tiden.
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
