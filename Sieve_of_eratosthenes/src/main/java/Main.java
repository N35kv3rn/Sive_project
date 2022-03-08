import java.util.Arrays;

public class Main {
    private static final int SIZE = 1000;
    private static final int THRESHOLD = 500;

    public static void main(String[] args) {
        boolean[] primeArray = new boolean[SIZE];
        Arrays.fill(primeArray, true);


        // TODO: Write sequential

        boolean[] serialArray = Serial.getSerial(primeArray);
        debugPrint(serialArray);

        // TODO: Write parallel

        boolean[] parallelArray = Parallel.getParallel(primeArray, THRESHOLD);
        debugPrint(parallelArray);

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
