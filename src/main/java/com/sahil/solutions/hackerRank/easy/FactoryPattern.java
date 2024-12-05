package com.sahil.solutions.hackerRank.easy;

import java.util.*;

public class FactoryPattern {
    public static interface Food {
        String getType();
    }
    
    public static class Pizza implements Food {
        public String getType() {
            return "Someone ordered a Fast Food!";
        }
    }
    
    public static class Cake implements Food {
        public String getType() {
            return "Someone ordered a Dessert!";
        }
    }
    
    public static class FoodFactory {
        public Food getFood(String s) {
            if (s.equalsIgnoreCase("cake")) {
                return new Cake();
            } else if (s.equalsIgnoreCase("pizza")) {
                return new Pizza();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String food = scanner.nextLine();
        scanner.close();

        FoodFactory foodFactory = new FoodFactory();
        Food instance = foodFactory.getFood(food);
        
        if (instance instanceof Pizza) {
            System.out.println("The factory returned class Pizza");
            System.out.println(instance.getType());
        } else if (instance instanceof Cake) {
            System.out.println("The factory returned class Cake");
            System.out.println(instance.getType());
        }
    }
}
