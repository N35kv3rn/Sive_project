public class Serial {
    public static boolean[] get(boolean[] primeArray) {
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
