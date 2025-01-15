package hackerRank.medium;

import java.util.Scanner;

public class PrimeChecker {
    public static class Prime {
        public void checkPrime(int... nums) {
            for (int n : nums) {
                if (isPrime(n)) {
                    System.out.print(n + " ");
                }
            }
            System.out.println();
        }

        public boolean isPrime(int n) {
            if (n <= 1)
                return false; // Primes start from 2.
            for (int i = 2; i <= Math.sqrt(n); i++) {
                // Not a prime if divisible by any num between 2 to √n (inclusive).
                if (n % i == 0)
                    return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n1 = Integer.parseInt(scanner.nextLine());
        int n2 = Integer.parseInt(scanner.nextLine());
        int n3 = Integer.parseInt(scanner.nextLine());
        int n4 = Integer.parseInt(scanner.nextLine());
        int n5 = Integer.parseInt(scanner.nextLine());
        scanner.close();
        Prime prime = new Prime();
        prime.checkPrime(n1);
        prime.checkPrime(n1, n2);
        prime.checkPrime(n1, n2, n3);
        prime.checkPrime(n1, n2, n3, n4, n5);
    }
}
