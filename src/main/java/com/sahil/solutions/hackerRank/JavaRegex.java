package com.sahil.solutions.hackerRank;

import java.util.Scanner;

public class JavaRegex {
    public static String pattern = "^((25[0-5]|2[0-4]\\d|1\\d{2}|0?\\d{1,2})\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|0?\\d{1,2})$";

    public static boolean validate(String ip) {
        return ip.matches(pattern);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            validate(scanner.nextLine());
        }
        scanner.close();
    }
}
