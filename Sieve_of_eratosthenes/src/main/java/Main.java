import java.util.Arrays;

public class Main {
    private static final int SIZE = 10000;
    private static final int THRESHOLD = 500;

    public static void main(String[] args) {
        boolean[] primeArray = new boolean[SIZE];
        boolean[] primeArray2 = new boolean[SIZE];
        Arrays.fill(primeArray, true);
        Arrays.fill(primeArray2, true);


        // TODO: Write sequential

        boolean[] serialArray = Serial.get(primeArray);
        debugPrint(serialArray);

        System.out.println("");
        System.out.println("");
        System.out.println("-------------------------");
        System.out.println("");

        // TODO: Write parallel

        boolean[] parallelArray = Parallel.get(primeArray2);
        debugPrint(parallelArray);

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
