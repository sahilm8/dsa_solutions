package com.sahil.solutions.hackerRank.easy;

import java.util.Scanner;

public class InputOutput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            System.out.println(scanner.nextInt());
        }
        scanner.close();
    }
}
