public class Main {
    private static final int SIZE = 1000;
    public static void main(String[] args) {

        // TODO: Write sequential

        boolean[] arr = Serial.getSerial(SIZE);
        for (int i = 2; i < arr.length; i++) {
            if(arr[i]) {
                System.out.print(i + " ");
            }

            if(i % 100 == 0) {
                System.out.println(" ");
            }

        }

        // TODO: Write parallel



    }
}
