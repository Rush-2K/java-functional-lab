import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class codeFinity {
    public static void main(String[] args) {

//        _q1();
//        _q2();
//        _q3();
        _q4();
    }

    static void _q1() {
        // Use Stream API to square each number in the list and collect the result into a new list.
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> sqrt = numbers.stream()
                .map(num -> num * num)
                .collect(Collectors.toList());

        System.out.println(sqrt);
    }

    static void _q2() {
        // Use Stream API to find the length of the longest name in the list
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eva");

        int longestName = names.stream()
                .map(data -> data.length())
                .max((a, b) -> a.compareTo(b))
                        .orElseThrow(null);

        System.out.println(longestName);
    }

    static void _q3() {
        // Use Stream API to count the total number of distinct words (case-insensitive) in all the sentences
        List<String> sentences = Arrays.asList(
                "Java Stream API provides a fluent interface for processing sequences of elements.",
                "It supports functional-style operations on streams of elements, such as map-reduce transformations.",
                "In this exercise, you need to count the total number of words in all sentences."
        );

        long data = sentences.stream()
                .flatMap(e -> Arrays.stream(e.split(" ")))
                .distinct()
                        .count();

        System.out.println(data);
    }

    static void _q4() {
        // Use Stream API to find the sum of the squares of even numbers in the list.
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        long result = numbers.stream()
                .filter(s -> s % 2 == 0)
                .mapToInt(s -> s.intValue() * s)
                .sum();

        System.out.println(result);

    }
}
