package hackerRank.medium;

import java.util.*;
import java.util.function.Predicate;

public class LambdaExpression {
    public static boolean validate(int type, int num) {
        if (type == 1) {
            Predicate<Integer> isEven = n -> n % 2 == 0;
            System.out.println(isEven.test(num) ? "EVEN" : "ODD");
        }

        if (type == 2) {
            Predicate<Integer> isPrime = n -> {
                if (n <= 1)
                    return false;
                for (int j = 2; j <= Math.sqrt(n); j++) {
                    if (n % j == 0)
                        return false;
                }
                return true;
            };
            System.out.println(isPrime.test(num) ? "PRIME" : "COMPOSITE");
        }

        if (type == 3) {
            Predicate<Integer> isPalindrome = n -> {
                String normal = String.valueOf(n);
                return normal.equals(new StringBuilder(normal).reverse().toString());
            };
            System.out.println(isPalindrome.test(num) ? "PALINDROME" : "NOT PALINDROME");
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTests = scanner.nextInt();

        for (int i = 0; i < numberOfTests; i++) {
            validate(scanner.nextInt(), scanner.nextInt());
        }
        scanner.close();
    }
}
