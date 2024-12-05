package com.sahil.solutions.hackerRank.medium;

import java.util.*;

public class CanAccess {
    private static class Inner {
        private static class Private {
            private static boolean powerOf2(int num) {
                return num > 0 && ((num & (num - 1)) == 0);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        scanner.close();
        if (Inner.Private.powerOf2(num)) {
            System.out.println(num + " is power of 2");
            System.out.println("An instance of class: Solution.Inner.Private has been created");
        } else {
            System.out.println(num + " is not a power of 2");
            System.out.println("An instance of class: Solution.Inner.Private has been created");
        }
    }
}
