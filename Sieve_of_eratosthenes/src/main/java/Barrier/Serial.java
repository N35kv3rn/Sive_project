package Barrier;

public class Serial {
    private boolean[] primeArray;
    public Serial(boolean[] primeArray) {
        for (int i = 2; i < Math.sqrt(primeArray.length); i++) {
            if(primeArray[i]) {
                for(int j = i*i; j < primeArray.length; j+=i) {
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
