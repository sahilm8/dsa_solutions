package hackerRank.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class StudentsPriorityQueue {
    public static class Student {
        int id;
        String name;
        double cgpa;

        public Student(int id, String name, double cgpa) {
            this.id = id;
            this.name = name;
            this.cgpa = cgpa;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public double getCgpa() {
            return this.cgpa;
        }
    }

    public static class Priorities {
        public List<String> getStudents(List<String> events) {
            Comparator<Student> customComparator = (Student s1, Student s2) -> {
                if (Double.compare(s2.getCgpa(), s1.getCgpa()) != 0) {
                    return Double.compare(s2.getCgpa(), s1.getCgpa());
                }
                if (s1.getName().compareTo(s2.getName()) != 0) {
                    return s1.getName().compareTo(s2.getName());
                }
                return Integer.compare(s1.getId(), s2.getId());
            };

            PriorityQueue<Student> students = new PriorityQueue<>(customComparator);
            for (String event : events) {
                String[] eventDetails = event.split(" ");
                if (eventDetails[0].equals("ENTER")) {
                    students.add(new Student(
                            Integer.parseInt(eventDetails[3]),
                            eventDetails[1],
                            Double.parseDouble(eventDetails[2])));
                } else if (!students.isEmpty() && eventDetails[0].equals("SERVED")) {
                    students.poll();
                }
            }
            List<String> result = new ArrayList<>();
            while (!students.isEmpty()) {
                result.add(students.poll().getName());
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfEvents = Integer.parseInt(scanner.nextLine());
        List<String> events = new ArrayList<>();
        for (int i = 0; i < numberOfEvents; i++) {
            events.add(scanner.nextLine());
        }
        scanner.close();
        Priorities priorities = new Priorities();
        List<String> result = priorities.getStudents(events);
        if (!result.isEmpty()) {
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }
        } else {
            System.out.println("EMPTY");
        }
    }
}
