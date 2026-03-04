import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MediumAdvanced {
    public static void main(String[] args) {
//        _q41();
//        _q42();
//        _q43();
//        _q44();
//        _q45();
//        _q46();
//        _q48();
//        _q49();
//        _q50();
//        _q51();
//        _q52();
//        _q53();
//        _q54();
//        _q55();
        _q56();
    }

    static void _q41() {
        // Find the Most Frequent Element in a List
        // Output: Most Frequent: apple
        List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "apple");
        String s = words.stream()
                .collect(Collectors.groupingBy(n -> n,
                        Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(c -> c.getKey())
                .orElse(null);

        System.out.println("Most Frequent: " + s);
    }

    static void _q42() {
        // Find the Least Frequent Element in a List
        // Output: Least Frequent: orange
        List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "apple");
        String least = words.stream()
                .collect(Collectors.groupingBy(n -> n,
                        Collectors.counting()))
                .entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(n -> n.getKey())
                .orElse(null);

        System.out.println("Least Frequent: " + least);
    }

    static void _q43() {
        // Find the First Non-Repeated Character in a String
        // First Non-Repeated Character: h
        String input = "hello";
        Character c1 = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c,
                        LinkedHashMap::new, //by default java uses HashMap, this is to tell Java to use LinkedHashMap to preserve the order
                        Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .findFirst()
                .map(c -> c.getKey())
                .orElse(null);

        System.out.println("Non-Repeated Character: " + c1);

    }

    static void _q44() {
        // Find the First Repeated Character in a String
        // Output: First Repeated Character: l
        String input = "hello";
        Character c1 = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c,
                        LinkedHashMap::new,
                        Collectors.counting()))
                .entrySet().stream()
                .filter(c -> c.getValue() > 1)
                .findFirst()
                .map(c -> c.getKey())
                .orElse(null);

        System.out.println("First Repeated Character: " + c1);
    }

    static void _q45() {
        // Check if a String is a Palindrome
        // Output: Is Palindrome: true
        String input = "civic";
//        boolean b = IntStream.range(0, input.length() / 2)
//                .allMatch(i -> input.charAt(i) == input.charAt(input.length() - 1 - i));

        boolean equals = input.equals(new StringBuilder(input).reverse().toString());

        System.out.println("Is Palindrome: " + equals);
    }

    static void _q46() {
        // Find All Anagrams of a String from a List
        List<String> words = List.of("listen", "silent", "enlist", "google", "inlets");
        String target = "silent";
        List<String> list = words.stream()
                .filter(word -> Arrays.equals(
                        word.chars().sorted().toArray(),
                        target.chars().sorted().toArray()))
                .toList();

        System.out.println("Anagrams: " + list);
    }

    static void _q48() {
        // Generate a List of Random Numbers Using Streams
        // Output: Random Numbers: [42, 67, 23, 89, 12, 45, 78, 34, 56, 90]
        Random random = new Random();

        List<Integer> list = Stream.generate(() -> random.nextInt(100))
                .limit(10)
                .toList();

        System.out.println("Random Numbers: " + list);
    }

    static void _q49() {
        // Flatten a List of Lists into a Single List
        // Output: Flattened List: [1, 2, 3, 4, 5, 6, 7, 8, 9]

        List<List<Integer>> listOfLists = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );

        List<Integer> collect = listOfLists.stream()
                .flatMap(s -> s.stream())
                .toList();

        System.out.println("Flattened List: " + collect);
    }

    static void _q50() {
        // Find the Sum of All Even Numbers in a Nested List
        // Sum of Even Numbers: 20

        List<List<Integer>> listOfLists = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );

        Integer sumEven = listOfLists.stream()
                .flatMap(s -> s.stream())
                .filter(i -> i % 2 == 0)
                .reduce(0, (a, b) -> a + b);

        System.out.println("Sum of Even Numbers: " + sumEven);

    }

    static void _q51() {
        // Find the Sum of All Odd Numbers in a Nested List
        // Output: Sum of Odd Numbers: 25

        List<List<Integer>> listOfLists = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );

        Integer sumOdd = listOfLists.stream()
                .flatMap(s -> s.stream())
                .filter(i -> i % 2 != 0)
                .reduce(0, (a, b) -> a + b);

        System.out.println("Sum of Odd Numbers: " + sumOdd);

    }

    static void _q52() {
        // Find the Longest Palindrome in a List of Strings
        // Longest Palindrome: racecar

        List<String> words = List.of("madam", "racecar", "apple", "banana", "level");
        String longest = words.stream()
                .filter(word -> word.equals(new StringBuilder(word).reverse().toString()))
                .max(Comparator.comparingInt(word -> word.length()))
                .orElse(null);

        System.out.println("Longest Palindrome: " + longest);
    }

    static void _q53() {
        // Find the Shortest Palindrome in a List of Strings
        // Shortest Palindrome: level

        List<String> words = List.of("madam", "racecar", "apple", "banana", "level");
        String shortest = words.stream()
                .filter(word -> word.equals(new StringBuilder(word).reverse().toString()))
                .min(Comparator.comparingInt(i -> i.length()))
                .orElse(null);

        System.out.println("Shortest Palindrome: " + shortest);
    }

    static void _q54() {
        // Find the Longest Word in a String
        // Output: Longest Word: hello

        String input = "hello world this is a test";
        String longestWord = Arrays.stream(input.split(" "))
                .toList()
                .stream()
                .max(Comparator.comparingInt(i -> i.length()))
                .orElse("no value");

        System.out.println("Longest Word: " + longestWord);
    }

    static void _q55() {
        // Find the Shortest Word in a String
        // Shortest Word: a

        String input = "hello world this is a test";
        String shortestWord = Arrays.stream(input.split(" "))
                .min(Comparator.comparingInt(i -> i.length()))
                .orElse(" ");

        System.out.println("Shortest Word: " + shortestWord);
    }

    static void _q56() {
        // Find the Number of Words in a String
        // Word Count: 6
        String input = "hello world this is a test";
        long count = Arrays.stream(input.split(" ")).count();

        System.out.println("Word Count: " + count);
    }
}
