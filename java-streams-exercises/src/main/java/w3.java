import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class w3 {
    // each answer has its own function
    public static void main(String[] args) {

//        _q1();
//        _q2();
//        _q3();
//        _q4();
//        _q5();
//        _q6();
//        _q7();
//        _q8();
//        _q9();
//        _q10();
//        _q11();
//        _q12();
//        _q13();
//        _q14();
//        _q15();
//        _q16();
//        _q17();
        _q18();
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

        double average = numbers.stream()
                .mapToDouble(num -> num.doubleValue())
                .average()
                        .orElse(0.0);

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

    static void _q9 () {
        int [] numbers = {1,2,3,4,5};

        List<Integer> list = new ArrayList<>(Arrays.stream(numbers).boxed().toList());

//        List<Integer> streamInt = Arrays.stream(numbers)
//                .boxed()
//                .collect(Collectors.toCollection(ArrayList::new));

        list.add(6);

        System.out.println(list);
    }

    //Output the highest frequency
    static void _q10() {
        List<Integer> numbers = Arrays.asList(1,2,2,2,4,2,1,5,6,6);

        int max = 0;
        int storeKey = 0;

        Map<Integer, Long> result = numbers.stream()
                .collect(Collectors.groupingBy(num -> num,
                        Collectors.counting()));

        for(Map.Entry<Integer, Long> entry : result.entrySet()) {
            if(entry.getValue() > max) {
                max = entry.getValue().intValue();
                storeKey = entry.getKey();
            }
        }

        System.out.println(storeKey);
    }

    // find the top 3 most frequent numbers
    static void _q11() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 4, 4, 3, 5, 7, 1, 4, 8, 8, 4);

        Map<Integer, Long> result = numbers.stream()
                .collect(Collectors.groupingBy(num -> num,
                        Collectors.counting()));

        List<Integer> collect = result.entrySet().stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .limit(3)
                .map(n -> n.getKey())
                .collect(Collectors.toList());

        System.out.println(collect);
    }

//    Given a list: ["apple", "bat", "cherry", "date", "egg", "fig"]
//
//    Create a Map where the Key is the length of the word and the Value is a List of words with that length.
//
//    Target Output: {3=[bat, egg, fig], 4=[date], 5=[apple], 6=[cherry]}

    static void _q12() {
        List<String> fruits = Arrays.asList("apple", "bat", "cherry", "date", "egg", "fig");

        Map<Integer, List<String>> collect = fruits.stream()
                .collect(Collectors.groupingBy(f -> f.length()));

        System.out.println(collect);
    }

//    find the most frequent number, but ignore all even numbers.
    static void _q13() {
        List<Integer> numbers = Arrays.asList(1,2,2,2,4,2,1,5,6,6);

        Map<Integer, Long> collect = numbers.stream()
                .filter(n -> n % 2 != 0)
                .collect(Collectors.groupingBy(n -> n,
                        Collectors.counting()));

        Integer i = collect.entrySet().stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .map(n -> n.getKey())
                .findFirst()
                .orElseThrow(null);

        System.out.println(i);

    }

//    Given a list of sentences, find the total count of every unique
//    word used across all sentences.
//Expected Output: {Java=2, is=2, fun=1, powerful=2, Streams=1, are=1}
    static void _q14() {
        List<String> sentences = Arrays.asList(
                "Java is fun",
                "Java is powerful",
                "Streams are powerful"
        );

//        Map<String, Long> result = sentences.stream()
//                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
//                .collect(Collectors.groupingBy(sentence -> sentence,
//                        Collectors.counting()));

//        String result = sentences.stream()
//                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
//                .reduce((a, b) -> a + " " + b)
//                .orElse(" ");

        String result = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }


    // find the product (multiply them all together) of all even numbers using reduce
    static void _q15() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 10);

        Integer reduce = numbers.stream()
                .filter(n -> n % 2 == 0)
                .reduce(1, (a, b) -> a * b);

        System.out.println(reduce);
    }

    // list of every unique character used across all words, sorted alphabetically
    // Expected Output: [a, b, c, e, h, l, n, p, r, y]
    static void _q16() {
        List<String> words = Arrays.asList("apple", "banana", "cherry");

        List<String> list = words.stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(list);
    }

    // You have a List<User>. Each User has a List<String> roles.
    // Can you get a unique, sorted list of all roles across all users?
    // Result should be ["Admin", "Editor", "Viewer"]
    static void _q17() {
        class User {
            String name;
            List<String> roles;
            // getter: getRoles()

            public User(String name, List<String> roles) {
                this.name = name;
                this.roles = roles;
            }

            public List<String> getRoles() {
                return roles;
            }
        }

        List<User> users = Arrays.asList(
                new User("Alice", Arrays.asList("Admin", "Editor")),
                new User("Bob", Arrays.asList("Editor", "Viewer"))
        );

        List<String> result = users.stream()
                .flatMap(user -> user.getRoles().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(result);
    }

    // final set should be [Laptop, Mouse, Keyboard]
    // filter age >= 18
    static void _q18() {
        class Order {
            String productName;

            Order(String productName) {
                this.productName = productName;
            }

            public String getProductName() {
                return productName;
            }
        }

        class User {
            String name;
            int age;
            List<Order> orders;

            public User(String name, int age, List<Order> orders) {
                this.name = name;
                this.age = age;
                this.orders = orders;
            }

            public int getAge() {
                return age;
            }

            public List<Order> getOrders() {
                return orders;
            }
        }

        List<User> users = Arrays.asList(
                new User("Alice", 25, Arrays.asList(new Order("Laptop"), new Order("Mouse"))),
                new User("Bob", 15, Arrays.asList(new Order("Phone"))), // Should be filtered out
                new User("Charlie", 30, Arrays.asList(new Order("Mouse"), new Order("Keyboard")))
        );

        List<String> result = users.stream()
                .filter(user -> user.getAge() >= 18)
                .flatMap(user -> user.getOrders().stream())
                .map(user -> user.getProductName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
