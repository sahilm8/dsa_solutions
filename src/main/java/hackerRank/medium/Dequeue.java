package hackerRank.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dequeue {
    public static int maxUnique(int subArrayLength, int[] array) {
        int max = 0;
        Deque<Integer> subArray = new ArrayDeque<>(); // new LinkedList<>();
        Map<Integer, Integer> uniqueElements = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            subArray.addLast(array[i]);
            uniqueElements.put(array[i], uniqueElements.getOrDefault(array[i], 0) + 1);
            while (subArray.size() > subArrayLength) {
                int removed = subArray.removeFirst();
                uniqueElements.put(removed, uniqueElements.get(removed) - 1);
                if (uniqueElements.get(removed) <= 0) {
                    uniqueElements.remove(removed);
                }
            }
            max = Math.max(max, uniqueElements.size());
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrayLength = scanner.nextInt();
        int[] array = new int[arrayLength];
        int subArrayLength = scanner.nextInt();
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        scanner.close();
        System.out.println(maxUnique(subArrayLength, array));
    }

}
