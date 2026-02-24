import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class medium {
    public static void main(String[] args) {
        // source: https://medium.com/@bhangalekunal2631996/100-java-streams-interview-questions-with-solutions-and-outputs-2afb0713ceec

        // Basic Level Questions (1–20)
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
//        _q1(numbers);
//        _q2();
//        _q3();
//        _q4();
//        _q5();
//        _q6();
//        _q7();
//        _q8(numbers);
//        _q9(numbers);
//        _q10();
//        _q11(numbers);
//        _q12(numbers);
//        _q13(numbers);
//        _q14(numbers);
//        _q15(numbers);
//        _q16();
//        _q17();
//        _q18();
//        _q19();
//        _q20();

        // Intermediate Level Questions (21-40)
//        _q21();
//        _q22();
//        _q23();
//        _q24();
//        _q25();
//        _q26();
//        _q27();
//        _q28();
//        _q29();
//        _q30();
//        _q31();
//        _q32();
//        _q33();
//        _q34();
//        _q35();
//        _q36();
//        _q37();
//        _q38();
//        _q39();
//        _q40();
    }

    static void _q1(List<Integer> numbers) {
        // Find the Sum of All Elements in a List

//        int sum = numbers.stream()
//                .mapToInt(n -> n.intValue())
//                .sum();

        int sum = numbers.stream()
                        .reduce((a,b) -> a + b)
                                .orElse(0);

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

    static void _q4() {
//        Find the Maximum Element in a List
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        System.out.println("Expected: 5");
        int max = numbers.stream()
                .max((a,b) -> a.compareTo(b))
                .orElse(0);

        System.out.println("Q4: " + max);
    }

    static void _q5() {
        // Find the Minimum Element in a List
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        System.out.println("Expected: 1");
        int i = numbers.stream()
                .min((a, b) -> a.compareTo(b))
                .orElse(0);

        System.out.println("Q5: " + i);
    }

    static void _q6() {
//        Count the Number of Elements in a List
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        long count = numbers.stream()
                .count();

        System.out.println("Q6: " + count);

    }

    static void _q7() {
//        Check if a List Contains a Specific Element
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        boolean b = numbers.stream()
                .anyMatch(n -> n.equals(6));

        System.out.println("Contains 6: " + b);
    }

    static void _q8(List<Integer> numbers) {
        // Filter Out Even Numbers from a List
        List<Integer> collect = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Q8: " + collect);

    }

    static void _q9(List<Integer> numbers) {
        // Filter Out Odd Numbers from a List
        List<Integer> collect = numbers.stream()
                .filter(n -> n % 2 != 0)
                .collect(Collectors.toList());

        System.out.println("Odd Numbers: " + collect);

    }

    static void _q10() {
        // Convert a List of Strings to Uppercase
        List<String> word = Arrays.asList("hello", "haha", "apple", "orange", "torchlight");

        List<String> collect = word.stream()
                .map(n -> n.toUpperCase())
                .collect(Collectors.toList());

        System.out.println("Uppercase Words: " + collect);

    }

    static void _q11(List<Integer> numbers) {
        //  Convert a List of Integers to Their Squares
        List<Integer> collect = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());

        System.out.println("Their Squares: " + collect);

    }

    static void _q12(List<Integer> numbers) {
        // Find the First Element in a List
        int n = numbers.stream()
                .findFirst()
                .orElse(0);

        System.out.println("First Element in " + numbers + " is " + n);
    }

    static void _q13(List<Integer> numbers) {
        // Find the Last Element in a List
        int skip = numbers.stream()
                .skip(numbers.toArray().length - 1)
                .findAny()
                .orElse(0);

        System.out.println("Last Element in " + numbers + " is " + skip);
    }

    static void _q14(List<Integer> numbers) {
        // Check if All Elements in a List Satisfy a Condition
        boolean check = numbers.stream()
                .allMatch(n -> n % 2 == 0);

        System.out.println("All Even: " + check);
    }

    static void _q15(List<Integer> numbers) {
        // Check if All Elements in a List Satisfy a Condition
        boolean check = numbers.stream()
                .anyMatch(n -> n % 2 == 0);

        System.out.println("Any Even: " + check);
    }

    static void _q16() {
        // Remove Duplicate Elements from a List
        List<Integer> numbers = List.of(1, 2, 2, 3, 4, 4, 5);
        System.out.println("Original List: " + numbers);

        List<Integer> collect = numbers.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Removed duplicate elements list: " + collect);
    }

    static void _q17() {
        // Sort a List of Integers in Ascending Order
        List<Integer> numbers = List.of(5, 3, 1, 4, 2);
        System.out.println("Original List: " + numbers);

        List<Integer> collect = numbers.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Sorted List: " + collect);
    }

    static void _q18() {
        // Sort a List of Integers in Descending Order
        List<Integer> numbers = List.of(5, 3, 1, 4, 2);
        System.out.println("Original List: " + numbers);

        List<Integer> collect = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println("Sorted List: " + collect);
    }

    static void _q19() {
        // Sort a List of Strings in Alphabetical Order
        List<String> words = Arrays.asList("banana", "apple", "cherry");
        System.out.println("Original List: " + words);

        List<String> collect = words.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Sorted List: " + collect);
    }

    static void _q20() {
        // Sort a List of Strings by Their Length
        List<String> words = List.of("apple", "banana", "kiwi");

        List<String> collect = words.stream()
                .sorted(Comparator.comparingInt(n -> n.length()))
                .collect(Collectors.toList());

        System.out.println("Sorted List by their length: " + collect);
    }

    static void _q21() {
        // Find the Sum of Digits of a Number

        int number = 12345;
        // convert integer to a string first
        String s = String.valueOf(number);

        // get an IntStream of character ASCII values
        IntStream intStream = s.chars();

        // Process the digits (e.g., convert ASCII to integer value
        int sum = intStream.map(c -> c - '0') // Convert character ASCII to integer digit
                .sum();

        System.out.println("Sum of Digits: " + number + " is " + sum);
    }

    static void _q22() {
        // Find the Factorial of a Number
        int number = 5;
        int sum = IntStream.rangeClosed(1, number)
                        .reduce(1, (a,b) -> a * b);

        System.out.println("Factorial of " + number + " is " + sum);
    }

    static void _q23() {
        // Find the Second-Largest Element in a List
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int i = numbers.stream()
                .sorted((a, b) -> b.compareTo(a))
                .skip(1)
                .findFirst()
                .orElse(0);

        System.out.println("Second largest of " + numbers + " is " + i);
    }

    static void _q24() {
//        Find the Second-Smallest Element in a List
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int i = numbers.stream()
                .sorted()
                .skip(1)
                .findFirst()
                .orElse(0);

        System.out.println("Second smallest of " + numbers + " is " + i);
    }

    static void _q25() {
        // Find the Longest String in a List
        List<String> words = List.of("apple", "banana", "kiwi");
        String s = words.stream()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .findFirst()
                .orElse("");

        System.out.println("Longest String in " + words + " is " + s);
    }

    static void _q26() {
        // Find the Shortest String in a List

        List<String> words = List.of("apple", "banana", "kiwi");
        String shortest = words.stream()
                .sorted(Comparator.comparingInt(s -> s.length()))
                .findFirst()
                .orElse("");

        System.out.println("Shortest String in " + words + " is " + shortest);
    }

    static void _q27() {
//        Group a List of Strings by Their Length
//        output: Grouped by Length: {4=[kiwi], 5=[apple], 6=[banana]}
        List<String> words = List.of("apple", "banana", "kiwi");
        Map<Integer, List<String>> collect = words.stream()
                .collect(Collectors.groupingBy(s -> s.length()));

        System.out.println("Grouped by length: " + collect);

    }

    static void _q28() {
        // Group a List of Objects by a Specific Attribute
        // Output: Grouped by Age: {25=[Alice, Charlie], 30=[Bob]}

        class Person {
            String name;
            int age;

            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }
        }

        List<Person> people = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 25)
        );

        Map<Integer, List<String>> collect = people.stream()
                .collect(Collectors.groupingBy(p -> p.getAge(),
                        Collectors.mapping(e -> e.getName(),
                                Collectors.toList())));
        System.out.println("Grouped by Age: " + collect);

    }

    static void _q29() {
        // Partition a List of Integers into Even and Odd Numbers
        // Output: Partitioned: {false=[1, 3, 5], true=[2, 4]}

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        Map<Boolean, List<Integer>> collect = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        System.out.println("Partitioned: " + collect);

    }

    static void _q30() {
        // Merge Two Lists into a Single List
        List<Integer> list1 = List.of(1, 2, 3);
        List<Integer> list2 = List.of(4, 5, 6);

        List<Integer> collect = Stream.of(list1, list2)
                .flatMap(s -> s.stream())
                .collect(Collectors.toList());

        System.out.println("Merged List: " + collect);
    }

    static void _q31() {
        // Find the Intersection of Two Lists
        // Output: Intersection: [3, 4]

        List<Integer> list1 = List.of(1, 2, 3, 4);
        List<Integer> list2 = List.of(3, 4, 5, 6);

        List<Integer> collect = list1.stream()
                .filter(s -> list2.contains(s))
                .collect(Collectors.toList());

        System.out.println("Intersection: " + collect);
    }

    static void _q32() {
        // Find the Union of Two Lists
        // Output: Union: [1, 2, 3, 4, 5]
        List<Integer> list1 = List.of(1, 2, 3);
        List<Integer> list2 = List.of(3, 4, 5);

        List<Integer> collect = Stream.of(list1, list2)
                .flatMap(s -> s.stream())
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Union: " + collect);
    }

    static void _q33() {
        // Find the Difference Between Two Lists
        // Output: Difference: [1, 2]
        List<Integer> list1 = List.of(1, 2, 3, 4);
        List<Integer> list2 = List.of(3, 4, 5, 6, 7);

//        List<Integer> collect = list1.stream()
//                .filter(s -> !list2.contains(s))
//                .collect(Collectors.toList());

//        System.out.println("Difference: " + collect);

        Stream<Integer> list1Stream = list1.stream()
                .filter(s -> !list2.contains(s));

        Stream<Integer> list2Stream = list2.stream()
                .filter(s -> !list1.contains(s));

        List<Integer> collect = Stream.concat(list1Stream, list2Stream)
                .collect(Collectors.toList());

        System.out.println("Full difference: " + collect);

    }

    static void _q34() {
        // Count the Occurrences of Each Element in a List
        // Word Counts: {orange=1, banana=1, apple=2}
        List<String> words = List.of("apple", "banana", "apple", "orange");

        Map<String, Long> collect = words.stream()
                .collect(Collectors.groupingBy(word -> word,
                        Collectors.counting()));

        System.out.println("Word Counts: " + collect);
    }

    static void _q35() {
        // Count the Occurrences of Each Character in a String
        // Output: Character Counts: {e=1, h=1, l=2, o=1}
        String input = "hello";
        Map<Character, Long> collect = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(character -> character,
                        Collectors.counting()));

        System.out.println("Character Counts: " + collect);
    }

    static void _q36() {
        // Count the Occurrences of Each Word in a String
        // Word Counts: {hello=2, world=1}
        String input = "hello world hello";
        String[] s = input.split(" ");
        Map<String, Long> collect = Arrays.stream(s)
                .collect(Collectors.groupingBy(word -> word,
                        Collectors.counting()));

        System.out.println("Word Counts: " + collect);
    }

    static void _q37() {
        // Count the Occurrences of Each Vowel in a String
        // Vowel Counts: {e=1, o=2}
        String input = "hello world";
        Map<Character, Long> collect = input.chars()
                .mapToObj(s -> (char) s)
                .filter(c -> "aeiou".contains(String.valueOf(c)))
                .collect(Collectors.groupingBy(character -> character,
                        Collectors.counting()));

        System.out.println("Vowel Counts: " + collect);
    }

    static void _q38() {
        // Count the Occurrences of Each Digit in a String
        // Output: Digit Counts: {1=1, 2=1, 3=1, 4=1, 5=1, 6=1}
        String input = "hello 123 world 456";
        Map<Character, Long> collect = input.chars()
                .mapToObj(c -> (char) c)
                .filter(character -> Character.isDigit(character))
                .collect(Collectors.groupingBy(digit -> digit,
                        Collectors.counting()));

        System.out.println("Digit Counts: " + collect);
    }

    static void _q39() {
        // Reverse a List Using Streams
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<Integer> reversed = new ArrayList<>(numbers);

//        List<Integer> collect = IntStream.range(0, numbers.size()) // stream of 0, 1, 2, 3, 4 (range)
//                .map(i -> numbers.size() - 1 - i) // transforming the index (position) used to grab the numbers
//                .mapToObj(n -> numbers.get(n))
//                .collect(Collectors.toList());

        Collections.reverse(reversed);

        System.out.println(reversed);
    }

    static void _q40() {
        // Reverse a String Using Streams
        // Output: Reversed String: olleh
        String input = "hello";

//        String collect = input.chars()
//                .map(i -> input.length() - 1 - i)
//                .mapToObj(c -> (char) c)
//                .map(String::valueOf)
//                .collect(Collectors.joining());

        String reduce = input.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .reduce("", (a, b) -> b + a);

//        String reversedWithPerformance = new StringBuilder(input).reverse().toString();

        System.out.println("Reversed String: " + reduce);
    }
}
