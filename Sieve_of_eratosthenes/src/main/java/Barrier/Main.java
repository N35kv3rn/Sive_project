package Barrier;

import java.util.Arrays;

public class Main {

    private static final int SIZE = 1_000_000_000; // N
    private static final int NUMBER_OF_PROCESSORS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        boolean[] primeArray = new boolean[SIZE];
        boolean[] primeArray2 = new boolean[SIZE];
        Arrays.fill(primeArray, true);
        Arrays.fill(primeArray2, true);

        long startTimeSerial, endTimeSerial, startTimeParallel, endTimeParallel, runTimeSerial, runTimeParallel;

        System.out.printf("\nSIZE: %,d\n",SIZE);
        System.out.println("NUMBER OF PROCESSORS: " + NUMBER_OF_PROCESSORS);
        System.out.println("---------------------\n");

        System.out.println("Serial running...");
        startTimeSerial = System.currentTimeMillis();
        boolean[] serialArray = new Serial(primeArray).getPrimeArray();
        endTimeSerial = System.currentTimeMillis();
        System.out.println("Serial finished");

        System.out.println("Parallel running...");
        startTimeParallel = System.currentTimeMillis();
        boolean[] parallelArray = new Parallel(primeArray2, NUMBER_OF_PROCESSORS).getPrimeArray();
        endTimeParallel = System.currentTimeMillis();
        System.out.println("Parallel finished");

        // Running time, speedup, efficiency
        runTimeSerial = endTimeSerial - startTimeSerial;
        runTimeParallel = endTimeParallel - startTimeParallel;
        double speedup = (double) runTimeSerial/ runTimeParallel;
        double efficiency = speedup / NUMBER_OF_PROCESSORS;

        // Prints results
        System.out.println("\n\n*** Serial ***");
        System.out.println("Running time: " + (runTimeSerial)/ 1000.0 + " seconds");
        System.out.println("---------------------\n");
        System.out.println("*** Parallel ***");
        System.out.println("Running time: " + (runTimeParallel) / 1000.0 + " seconds");
        System.out.println("---------------------\n");
        System.out.println("Speedup: " + speedup);
        System.out.println("Efficiency: " + efficiency);

        System.out.println("\nArrays are equal: " + Arrays.equals(serialArray, parallelArray));
    }
}
