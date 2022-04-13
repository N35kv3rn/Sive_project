package MPI;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Parallel {

    CyclicBarrier barrier;
    int numOfProcessors;
    int index;
    boolean[] primeArray;


    public Parallel(boolean[] primeArray, int index) {
        this.numOfProcessors = Runtime.getRuntime().availableProcessors();
        this.primeArray = primeArray;
        this.index = index;


        barrier = new CyclicBarrier(this.numOfProcessors, new Runnable() {
            @Override
            public void run() {

            }
        });

        int partition = primeArray.length / numOfProcessors;

        for (int i = 0; i < numOfProcessors; i++) {
            new Thread(new Worker(primeArray, i, ))
        }

    }

    class Worker implements Runnable {
        int index, first, last;
        boolean[] primeArray;
        private Worker(boolean[] primeArray, int first, int last, int index) {
            this.index = index;
            this.first = first;
            this.last = last;
            this.primeArray = primeArray;
        }
        public void run() {
            calculate(primeArray);

            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        boolean[] calculate(boolean[] primeArray) {
            try {
                for (int i = first; i < last; i++) {
                    if(primeArray[i] && i*i < primeArray.length) {
                        for(int j = index*index; j < last; j+=i) {
                            primeArray[j] = false;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return primeArray;
        }

        public boolean[] get(boolean[] primeArray) {


            return primeArray;
        }
    }




    }
