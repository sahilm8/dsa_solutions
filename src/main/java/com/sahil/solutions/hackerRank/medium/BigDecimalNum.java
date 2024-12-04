package com.sahil.solutions.hackerRank.medium;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class BigDecimalNum {
    public static String[] sort(String[] nums) {
        Arrays.sort(nums, (String a, String b) -> {
            BigDecimal bd1 = new BigDecimal(a);
            BigDecimal bd2 = new BigDecimal(b);  
            return bd2.compareTo(bd1);          
        });
        
        /*
        // Bubble Sort
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                BigDecimal bd1 = new BigDecimal(nums[j]);
                BigDecimal bd2 = new BigDecimal(nums[j - 1]);
                if (bd2.compareTo(bd1) < 0) {
                    // Swap
                    String temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        */
        
        return nums;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());
        String[] nums = new String[length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = scanner.nextLine();
        }
        scanner.close();
        String[] sortedNums = sort(nums);
        for (int i = 0; i < sortedNums.length; i++) {
            System.out.println(sortedNums[i]);
        }
    }
}
