package MPI;

import java.util.Arrays;

public class Main {

    private static final int SIZE = 876533765;

    public static void main(String[] args) {
        boolean[] primeArray = new boolean[SIZE];
        boolean[] primeArray2 = new boolean[SIZE];
        Arrays.fill(primeArray, true);
        Arrays.fill(primeArray2, true);

        long startTime;
        long endTime;


        // TODO: Write sequential
        startTime = System.currentTimeMillis();
        boolean[] serialArray = Serial.get(primeArray);
        endTime = System.currentTimeMillis();
        System.out.println("Serial time: " + (endTime - startTime));

        // TODO: Write parallel

        startTime = System.currentTimeMillis();
        boolean[] parallelArray = Parallel.get(primeArray2);
        endTime = System.currentTimeMillis();
        System.out.println("Parallel time: " + (endTime - startTime));
        System.out.println("");

        for (int i = 0; i < SIZE; i++) {
            if(parallelArray[i] != serialArray[i]) {
                System.out.println("Error at index " + i);
            }
        }

        System.out.println("\r\n\r\nEqual: " + Arrays.equals(serialArray, parallelArray));

    }

}
