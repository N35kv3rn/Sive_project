package Barrier;

public class Sieve implements Runnable {
    private int start;
    private int end;

    private boolean[] primeArray;

    private int index;

    public Sieve(boolean[] primeArray, int start, int end, int index) {
        this.start = start;
        this.end = end;
        this.primeArray = primeArray;
        this.index = index;
    }

    @Override
    public void run() {
        try {
            if(end % index == 0 && end < primeArray.length) {
                primeArray[end] = false;
            }

            if(start%index != 0) {
                while(true) {
                    if(start%index == 0) {
                        break;
                    }
                    start--;
                }
            }

            for (int j = start + index; j < end; j += index) {
                if(j <= 2) {
                    continue;
                }
                if(j == index) {
                    primeArray[j] = true;
                    continue;
                }
                primeArray[j] = false;
            }

            Parallel.barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
