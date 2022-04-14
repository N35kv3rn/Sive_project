
import java.util.Arrays;

public class Main {
    private static final int SIZE = 46349;
    private static final int THRESHOLD = 500;

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

        System.out.println("\r\n\r\nEqual: " + Arrays.equals(serialArray, parallelArray));



    }

    private static void debugPrint(boolean [] arr) {
        for (int i = 2; i < arr.length; i++) {
            if (arr[i]) {
                System.out.print(i + " ");
            }

            if (i % 100 == 0) {
                System.out.println(" ");
            }

        }
    }
}
