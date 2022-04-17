package Barrier;

public class Serial {
    private boolean[] primeArray;
    public Serial(boolean[] primeArray) {
        // For each number between 2 and the square root of N
        for (int i = 2; i < Math.sqrt(primeArray.length); i++) {
            // If the number of index i is either a prime or has not yet been iterated over
            if(primeArray[i]) {
                // For each number divisible by the number of index i
                for(int j = i*i; j < primeArray.length; j+=i) {
                    // Set this number as not a prime
                    primeArray[j] = false;
                }
            }
        }
        this.primeArray = primeArray;
    }

    public boolean[] getPrimeArray() {
        return primeArray;
    }
}
