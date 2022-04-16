package Barrier;

import java.util.Arrays;

public class Main {

    private static final int SIZE = 1_000_000_000; // 10^9 = 1_000_000_000

    public static void main(String[] args) {
        boolean[] primeArray = new boolean[SIZE];
        boolean[] primeArray2 = new boolean[SIZE];
        Arrays.fill(primeArray, true);
        Arrays.fill(primeArray2, true);

        long startTime;
        long endTime;

        System.out.println("SIZE: " + SIZE);
        // TODO: Write sequential
        System.out.println("Sequential");
        startTime = System.currentTimeMillis();
        boolean[] serialArray = new Serial(primeArray).getPrimeArray();
        endTime = System.currentTimeMillis();
        System.out.println("Serial time: " + (endTime - startTime)/ 1000.0 + " seconds");

        // TODO: Write parallel
        System.out.println("Parallel");
        startTime = System.currentTimeMillis();
        boolean[] parallelArray = new Parallel(primeArray2).getPrimeArray();
        endTime = System.currentTimeMillis();
        System.out.println("Parallel time: " + (endTime - startTime) / 1000.0 + " seconds");

        for (int i = 0; i < SIZE; i++) {
            if(parallelArray[i] != serialArray[i]) {
                System.out.println("Error at index " + i);
            }
        }

        System.out.println("\r\nEqual: " + Arrays.equals(serialArray, parallelArray));
    }
}
