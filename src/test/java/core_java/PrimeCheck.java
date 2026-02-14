package core_java;

public class PrimeCheck {
    public static void main(String[] args) {
        int primeNumber = 1;
        boolean isPrime = false;

        System.out.println(Math.sqrt(primeNumber));

        if (primeNumber <= 1) {
            isPrime = false;
        } else {
            for (int i = 2; i <= Math.sqrt(primeNumber); i++) {
                if (primeNumber % i == 0) {
                    isPrime = true;
                    break;
                }
            }
        }
        System.out.println(isPrime + " ");
    }
}
