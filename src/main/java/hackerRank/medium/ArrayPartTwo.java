package hackerRank.medium;

import java.util.Scanner;

public class ArrayPartTwo {
    public static boolean canWin(int leap, int[] game) {
        return validate(0, leap, game);
    }

    public static boolean validate(int pos, int leap, int[] game) {
        if (pos >= game.length)
            return true; // >= since pos starts from 0.
        if (pos < 0 || game[pos] == 1)
            return false;
        game[pos] = 1; // Visited, hence setting to 1.
        return validate(pos - 1, leap, game)
                || validate(pos + 1, leap, game)
                || validate(pos + leap, leap, game);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queries = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < queries; i++) {
            int size = scanner.nextInt();
            int leap = scanner.nextInt();
            int[] game = new int[size];
            for (int j = 0; j < size; j++) {
                game[j] = scanner.nextInt();
            }
            if (canWin(leap, game)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
        scanner.close();
    }

}
