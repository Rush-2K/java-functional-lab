import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class medium {
    public static void main(String[] args) {
        // source: https://medium.com/@bhangalekunal2631996/100-java-streams-interview-questions-with-solutions-and-outputs-2afb0713ceec

        // Basic Level Questions (1–20)
//        _q1();
//        _q2();
//        _q3();
    }

    static void _q1() {
        // Find the Sum of All Elements in a List
        List<Integer> numbers = List.of(1,2,3,4,5);

        int sum = numbers.stream()
                .mapToInt(n -> n.intValue())
                .sum();

        System.out.println("Q1: " + sum);
    }

    static void _q2() {
//        Find the Product of All Elements in a List
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        System.out.println("Expected: 120");
        int reduce = numbers.stream()
                .mapToInt(n -> n.intValue())
                .reduce(1, (a, b) -> a * b);

        System.out.println("Q2: " + reduce);
    }

    static void _q3() {
        // Find the Average of All Elements in a List
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        System.out.println("Expected: 3.0");

        double average = numbers.stream()
                .mapToDouble(n -> n.doubleValue())
                .average()
                .orElse(0.0);

        System.out.println("Q3: " + average);
    }
}
