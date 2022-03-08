import java.util.Arrays;

public class Serial {
    static boolean[] primeArray;

    public static boolean[] getSerial(int size) {
        primeArray = new boolean[size];
        Arrays.fill(primeArray, true);

        for (int i = 2; i < Math.sqrt(primeArray.length); i++) {
            if(primeArray[i]) {
                for(int j = i*i; j < primeArray.length; j+=i) {
                    primeArray[j] = false;
                }
            }
        }

        return primeArray;
    }
}
