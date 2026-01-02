import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class w3 {
    // each answer has its own function
    public static void main(String[] args) {

//        _q1();
//        _q2();
//        _q3();
//        _q4();
//        _q5();
//        _q6();
        _q7();
//        _q8();
    }

    static void _q1 () {
        // Write a Java program to calculate the average of a list of integers using streams.

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("numbers: " + numbers);
        System.out.println("numbers2: " + numbers2);
        int sumOfNumbers = 0;
        int counter = 0;
        for (Integer number: numbers) {
            sumOfNumbers = sumOfNumbers + number;
            counter++;
        }
//        System.out.println(sumOfNumbers % counter);

        // Java stream
//        List<Double> average = numbers.stream()
//                .mapToDouble(num -> num.doubleValue())
//                        .boxed()
//                                .collect(Collectors.toList());

        List<Double> average = numbers.stream()
                .mapToDouble(num -> num.doubleValue())
                        .boxed()
                                .collect(Collectors.toList());

        System.out.println(average);

    }

    static void _q2() {
        // Write a Java program to convert a list of strings to uppercase or lowercase using streams

        List<String> data = Arrays.asList("John", "Nina", "Citta Mall", "Zoro", "O'neil");

        List<String> dataTransform = data.stream()
                .map(s -> s.toLowerCase())
//                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());

        System.out.println(dataTransform);
    }

    static void _q3() {
        // Write a Java program to calculate the sum of all even, odd numbers in a list using streams.

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // just checking
//        List<Double> result =  numbers.stream()
//                .mapToDouble(number -> number.doubleValue())
//                .filter(num -> checkEven(num))
//                .boxed()
//                .collect(Collectors.toList());

        Double sumEven = numbers.stream()
                        .mapToDouble(num -> num.doubleValue())
                                .filter(num -> num % 2 == 0)
                                        .sum();

        System.out.println("Sum of even numbers: " + sumEven);

        Double sumOdd = numbers.stream()
                .mapToDouble(num -> num.doubleValue())
                .filter(num -> num % 2 != 0)
                .sum();

        System.out.println("Sum of odd numbers: " + sumOdd);
    }

//    static boolean checkEven(double num) {
//        return num % 2 == 0;
//    }
//
//    static boolean checkOdd(double num) {
//        return num % 2 != 0;
//    }

    static void _q4() {
        // Write a Java program to remove all duplicate elements from a list using streams.

        List<Integer> numbers = Arrays.asList(10, 2, 3, 4, 4, 5, 7, 2, 3, 10);
        System.out.println("Original list of numbers: " + numbers);

        List<Integer> result = numbers.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("After removing duplicates: " + result);

    }

    static void _q5() {
        // Write a Java program to count the number of strings in a list that start with a specific letter using streams.

        List<String> fruits = Arrays.asList("Apple", "Banana", "Apricot", "Cherry", "Ant");

        long counter = fruits.stream()
                .filter(data -> data.startsWith("A"))
                .count();

        System.out.println("Number of strings that starts with letter A: " + counter);
    }

    static void _q6() {
        // Write a Java program to sort a list of strings in alphabetical order, ascending and descending using streams.
        List<String> listOfNames = Arrays.asList("Max", "Stroll", "Albon", "Bortoleto", "Charles");

        List<String> ascOrder = listOfNames.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("List in ascending order: " + ascOrder);

        List<String> descOrder = listOfNames.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println("List in descending order: " + descOrder);
    }

    static void _q7() {
        List<Integer> numbers = Arrays.asList(10, 2, 3, 4, 4, 5, 7, 2, 3, 10);

        Integer maxValue = numbers.stream()
                .max((a,b) -> a.compareTo(b))
                .orElse(null);

        Integer minValue = numbers.stream()
                .min((a,b) -> a.compareTo(b))
                .orElse(null);

        System.out.println("Max value is: " + maxValue);
        System.out.println("Min value is: " + minValue);
    }

    static void _q8() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("List of numbers: " + numbers);

        Integer secondLargest = numbers.stream()
                .distinct()
                .sorted()
                .skip(2)
                .findFirst()
                .orElse(null);

        System.out.println("Second largest number is: " + secondLargest);

        Integer secondSmallest = numbers.stream()
                .distinct()
                .sorted((a,b) -> b.compareTo(a))
                .skip(1)
                .findFirst()
                .orElse(null);

        System.out.println("Second smallest number is: " + secondSmallest);
    }
}
