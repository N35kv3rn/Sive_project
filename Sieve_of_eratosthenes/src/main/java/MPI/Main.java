package MPI;

import java.util.Arrays;

public class Main {

    private static final int SIZE = 10000;

    public static void main(String[] args) {
        boolean[] primeArray = new boolean[SIZE];
        boolean[] primeArray2 = new boolean[SIZE];
        Arrays.fill(primeArray, true);
        Arrays.fill(primeArray2, true);


        // TODO: Write sequential

        boolean[] serialArray = Serial.get(primeArray);


        System.out.println("");
        System.out.println("");
        System.out.println("-------------------------");
        System.out.println("");

        // TODO: Write parallel

        Parallel parallel = new Parallel(primeArray2, 2);



       // System.out.println("\r\n\r\nEqual: " + Arrays.equals(serialArray, parallelArray));

    }

}
