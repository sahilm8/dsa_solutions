package com.sahil.solutions.hackerRank.medium;

import java.util.*;

public class PlayerComparator {
    public static class Player {
        public String name;
        public int score;        
        
        public Player(String name, int score) {
            this.name = name;
            this.score = score;
        }
        
        
        public String getName() {
            return this.name;
        }
        
        public int getScore() {
            return this.score;
        }
    }
    
    public static class Checker implements Comparator<Player> {
        @Override
        public int compare(Player p1, Player p2) {
            if (Integer.compare(p1.getScore(), p2.getScore()) != 0) {
                return Integer.compare(p2.getScore(), p1.getScore());
            }
            return p1.getName().compareTo(p2.getName());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        Player[] players = new Player[count];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(scanner.next(), Integer.parseInt(scanner.next()));
        }
        scanner.close();
        Checker checker = new Checker();
        Arrays.sort(players, checker);
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].getName() + " " + players[i].getScore());          
        }
    }
}
