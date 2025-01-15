package hackerRank.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class CharStack {
    public static boolean validate(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsValue(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                if (!stack.isEmpty() && stack.peek() == map.get(s.charAt(i))) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        if (stack.isEmpty())
            return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            if (validate(scanner.nextLine())) {
                System.out.println(true);
            } else {
                System.out.println(false);
            }
        }
        scanner.close();
    }
}
