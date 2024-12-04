package com.sahil.solutions.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class Medium {
     // 1041. Robot Bounded in Circle (Medium) [T = O(n), S = O(1)]
    public static boolean isRobotBounded(String instructions) {
        int x = 0, y = 0;
        int facing = 0; // N E S W
        int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // N E S W
        for (int i = 0; i < instructions.length(); i++) {
            if (instructions.charAt(i) == 'L') {
                facing = (facing + 3) % 4; // Modulo wrap-around arithmetic: x % n in range (0, n-1)
            } else if (instructions.charAt(i) == 'R') {
                facing = (facing + 1) % 4;
            } else {
                x += directions[facing][0];
                y += directions[facing][1];
            }
        }
        if ((x == 0 && y == 0) || facing != 0)
            return true; // Bounded circle
        return false;
    }

    // 91. Decode Ways (Medium) [T = O(n), S = O(n)]
    public static int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;
        if (s.length() == 1) return 1;
        // DP
        int[] array = new int[s.length() + 1];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            int singleDigit = Integer.parseInt(s.substring(i - 1, i));
            int doubleDigit = Integer.parseInt(s.substring(i - 2, i));
            if (singleDigit >= 1 && singleDigit <= 9) {
                array[i] += array[i - 1];
            }
            if (doubleDigit >= 10 && doubleDigit <= 26) {
                array[i] += array[i - 2];
            }
        }
        return array[s.length()];
    }

    // 166. Fraction to Recurring Decimal (Medium) [T = O(log(n)), S = O(n)]
    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        // XOR: when both have diff signs
        if ((numerator < 0) ^ (denominator < 0))
            result.append("-");
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        result.append(num / den);
        // Manual long division to find repeating remainder
        HashMap<Long, Integer> map = new HashMap<>();
        long remainder = num % den;
        if (remainder != 0)
            result.append(".");
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                // For repeating remainder,
                // get the index and insert "(" and append ")"
                result.insert(map.get(remainder), "(");
                result.append(")");
                break;
            }
            // Put last index + 1 (result.length()) into the map
            map.put(remainder, result.length());
            remainder *= 10;
            result.append(remainder / den);
            remainder %= den;
        }
        return result.toString();
    }

    // 15. 3Sum (Medium) [T = O(n^2), S = O(1)]
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> triplets = new ArrayList<>();
        // Counter and two pointers
        // index (i), left (i + 1), right (nums.length - 1)
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    triplets.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1])
                        left++;
                    while (left < right && nums[right] == nums[right - 1])
                        right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return triplets;
    }
    
    // 1823. Find the Winner of the Circular Game (Medium) [T = O(n * k), S = O(n)]
    public static int findTheWinner(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int index = 0;
        while (list.size() > 1) {
            // Modulo arithmetic: i % n in range (0, n - 1)
            index = (index + k - 1) % list.size();
            list.remove(index);
        }
        return list.get(0);
    }

    // Josephus Problem (Josephus Permutation) [T = O(n), S = O(1)]
    public static int _findTheWinner(int n, int k) {
        int winner = 1;
        for (int i = 2; i <= n; i++) {
            // + 1 to shift from 0-based to 1-based indexing
            winner = (winner + k - 1) % i + 1;
        }
        return winner;
    }

    // 3167. Better Compression of String (Medium) [T = O(n^2), S = O(n)]
    public static String betterCompression(String compressed) {
        Map<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < compressed.length(); i++) {
            if (Character.isAlphabetic(compressed.charAt(i))) {
                StringBuilder sb = new StringBuilder();
                for (int j = i + 1; j < compressed.length(); j++) {
                    if (Character.isDigit(compressed.charAt(j))) {
                        sb.append(compressed.charAt(j));
                    } else {
                        break;
                    }
                }
                map.put(compressed.charAt(i),
                        map.getOrDefault(compressed.charAt(i), 0) + Integer.parseInt(sb.toString()));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append(entry.getValue());
        }
        return sb.toString();
    }

    // 3. Longest Substring Without Repeating Characters (Medium) [T = O(n), S = O(n)]
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        // Set & TP
        Set<Character> set = new HashSet<>();
        int left = 0;
        int maxSize = 0;
        set.add(s.charAt(left));
        for (int right = 1; right < s.length(); right++) {
            if (set.contains(s.charAt(right))) {
                while (set.contains(s.charAt(right))) {
                    set.remove(s.charAt(left));
                    left++;
                }
            }
            set.add(s.charAt(right));
            maxSize = Math.max(maxSize, set.size());
        }
        return maxSize;
    }

    // 49. Group Anagrams (Medium) [T = O(n * m * log(m)), S = O(n * m)]
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(strs[i]);
            if (!result.contains(map.get(sorted))) {
                result.add(map.get(sorted));
            }
        }
        return result;
    }

    // 198. House Robber (Medium) [T = O(n), S = O(n)]
    public static int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);
        // DP
        int[] array = new int[nums.length];
        array[0] = nums[0];
        array[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            array[i] += Math.max(array[i - 1], nums[i] + array[i - 2]);
        }
        return array[nums.length - 1];
    }

    // 647. Palindromic Substrings (Medium) [T = O(n^2), S = O(1)]
    public static int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            // Odd Palindrome (1 center) eg: abc, aaa
            count += expandOutward(s, i, i);
            // Even Palindrome (2 center) eg: abcd, aaaa
            count += expandOutward(s, i, i + 1);
        }
        return count;
    }

    // TP: Expand using left & right
    public static int expandOutward(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    // 2375. Construct Smallest Number From DI String (Medium) [T = O(n), S = O(n)]
    public static String smallestNumber(String pattern) {
        // Stack & SB
        // IIIDIDDD
        // i=0: push(1), see 'I', pop -> sb="1"
        // i=1: push(2), see 'I', pop -> sb="12"
        // i=2: push(3), see 'I', pop -> sb="123"
        // i=3: push(4), see 'D', keep in stack
        // i=4: push(5), see 'I', pop -> sb="12354"
        // i=5: push(6), see 'D', keep in stack
        // i=6: push(7), see 'D', keep in stack
        // i=7: push(8), see 'D', keep in stack
        // i=8: push(9), end reached, pop all -> sb="123549876"
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= pattern.length(); i++) {
            stack.push(i + 1);
            if (i == pattern.length() || pattern.charAt(i) == 'I') {
                while(!stack.isEmpty()) {
                    // Gradually building the lexicographically smallest string based on LIFO
                    sb.append(stack.pop());
                }
            }
        }
        return sb.toString();
    }

    // 11. Container With Most Water (Medium) [T = O(n), S = O(1)]
    public static int maxArea(int[] height) {
        // TP
        int maxArea = 0, left = 0, right = height.length - 1;
        while (left < right) {
            int length = Math.min(height[left], height[right]);
            int breadth = right - left;
            int area = length * breadth;
            maxArea = Math.max(maxArea, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    // 22. Generate Parentheses (Medium) [T = O(2^n), S = O(n)]
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    // Recursive Backtracking
    public static void backtrack(List<String> result, String s, int open, int close, int n) {
        if (s.length() == n * 2) { // each valid compination has n * 2 chars
            result.add(s);
            return;
        }
        // less open brackets than n
        if (open < n) {
            backtrack(result, s + "(", open + 1, close, n);
        }
        // more open brackets than close brackets
        if (open > close) {
            backtrack(result, s + ")", open, close + 1, n);
        }
    }

    // 55. Jump Game (Medium) [T = O(n), S = O(1)]
    public static boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i <= max && i < nums.length; i++) {
            max = Math.max(max, i + nums[i]);
        }
        if (max >= nums.length - 1) {
            return true;
        }
        return false;
    }

    // 122. Best Time to Buy and Sell Stock II (Medium) [T = O(n), S = O(1)]
    public static int maxProfit(int[] prices) {
        int totalProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                totalProfit += prices[i] - prices[i - 1];
            }
        }
        return totalProfit;
    }

    // 152. Maximum Product Subarray (Medium) [T = O(n), S = O(1)]
    // Variant of Kadane's Algorithm
    public static int maxProduct(int[] nums) {
        // Track both max & min since minimum (-ve) product
        // become maximum (+ve) when multiplied by a negative number.
        int max = nums[0], min = nums[0], total = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(nums[i], Math.max(nums[i] * max, nums[i] * min));
            min = Math.min(nums[i], Math.min(nums[i] * temp, nums[i] * min));
            total = Math.max(total, max);
        }
        return total;
    }

    // Kadane's Algorithm: Find the max subarray sum [T = O(n), S = O(1)]
    public static int maxSum(int[] nums) {
        int max = nums[0];
        int total = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(nums[i], nums[i] + max);
            total = Math.max(total, max);
        }
        return total;
    }

    // 209. Minimum Size Subarray Sum (Medium) [T = O(n), S = O(1)]
    public static int minSubArrayLen(int target, int[] nums) {
        // TP & SW
        int minLength = Integer.MAX_VALUE; // any min length will be smaller
        int currentSum = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right]; // update sum
            while (currentSum >= target) {
                minLength = Math.min(minLength, right - left + 1); // update min length
                currentSum -= nums[left]; // subtract value at left
                left++; // move left forward
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    // 264. Ugly Number II (Medium) [T = O(n), S = O(n)]
    public static int nthUglyNumber(int n) {
        // DP
        int[] array = new int[n];
        array[0] = 1;
        // Three pointers for 3 prime factors: 2 - p1, 3 - p2, 5 - p3
        int p1 = 0, p2 = 0, p3 = 0;
        for (int i = 1; i < n; i++) {
            // Get the next ugly number by finding the min of the 3 products with the PFs
            array[i] = Math.min(array[p1] * 2, Math.min(array[p2] * 3, array[p3] * 5));
            // Only move the counter for min product
            if (array[i] == array[p1] * 2) p1++;
            if (array[i] == array[p2] * 3) p2++;
            if (array[i] == array[p3] * 5) p3++;
        }
        return array[n - 1];
    }

    // 325. Maximum Size Subarray Sum Equals k (Medium) [T = O(n), S = O(n)]
    public static int maxSubArrayLen(int[] nums, int k) {
        // Map to store:
        // Key = Running sum
        // Value = Index where that running sum was found
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // Base case
        int maxLength = 0;
        int currentSum = 0;
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            // Subarray contains a sum == currentSum - k
            if (map.containsKey(currentSum - k)) {
                // current index - index of that sum gives the maxLength
                maxLength = Math.max(maxLength, i - map.get(currentSum - k));
            }
            // If sum doesn't exist, add it
            map.putIfAbsent(currentSum, i);
        }
        return maxLength;
    }

    // 443. String Compression (Medium) [T = O(n), S = O(1)]
    public static int compress(char[] chars) {
        int count = 1; // To count repeating chars
        int writeIndex = 0; // Index to rewrite chars
        for (int i = 1; i <= chars.length; i++) {
            if (i == chars.length || chars[i] != chars[i - 1]) {
                chars[writeIndex] = chars[i - 1]; // Rewrite
                writeIndex++; // Move forward
                if (count > 1) {
                    char[] countChars = String.valueOf(count).toCharArray();
                    for (char c : countChars) {
                        chars[writeIndex] = c; // Insert count chars
                        writeIndex++; // Move forward
                    }
                }
                count = 1; // Reset count
            } else {
                count++; // Increase count if same as last char
            }
        }
        return writeIndex;
    }

    public static void main(String[] args) {
        System.out.println(isRobotBounded("GGLLGGLLGG"));
        System.out.println(numDecodings("226"));
        System.out.println(fractionToDecimal(-1, -2147483648));
        System.out.println(threeSum(new int[] {-1,0,1,2,-1,-4}));
        System.out.println(_findTheWinner(5, 2));
        System.out.println(betterCompression("i10g6u6"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(groupAnagrams(new String[] {"eat","tea","tan","ate","nat","bat"}));
        System.out.println(rob(new int[] {2,1,1,2}));
        System.out.println(countSubstrings("aaa"));
        System.out.println(smallestNumber("IIIDIDDD"));
        System.out.println(maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
        System.out.println(generateParenthesis(3));
        System.out.println(canJump(new int[] {2,3,1,1,4}));
        System.out.println(maxProfit(new int[] {7,1,5,3,6,4}));
        System.out.println(maxProduct(new int[] {2,3,-2,4}));
        System.out.println(maxSum(new int[] {2,3,-2,4}));
        System.out.println(minSubArrayLen(7, new int[] {2,3,1,2,4,3}));
        System.out.println(nthUglyNumber(10));
        System.out.println(maxSubArrayLen(new int[] {1,-1,5,-2,3}, 3));
        System.out.println(compress(new char[] {'a','b','b','b','b','b','b','b','b','b','b','b','b'}));
    }
}