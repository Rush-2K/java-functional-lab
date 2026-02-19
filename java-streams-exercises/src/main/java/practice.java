import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class practice {
    public static void main(String[] args) {
        String name = "Hamilton";
        String newName = reverse(name);
//        System.out.println("Original Name: " + name);
//        System.out.println("Reversed: " + newName);

//        loopString();
        streamBenchMark();
    }

    static String reverse (String name) {
        if (name == null) return null;
        StringBuilder reverseName = new StringBuilder(name);
        return reverseName.reverse().toString();

//        for(int i = name.length() - 1; i >= 0; i--) {
//            reverseName.append(name.charAt(i));
//        }
//
//        return reverseName.toString();
    }

    static void loopString() {
        String[] fruits = {"apple", "orange", "blueberry"};

        List<String> collect = Arrays.stream(fruits)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(collect);
    }

    static void loopInt() {
        int[] numbers = {98, 23, 44, 22, 65};

        Arrays.stream(numbers)
                .sorted()
                .boxed()
                .collect(Collectors.toList());
    }

    static void streamBenchMark() {
        // setup two list with 50,000 integers
        List<Integer> list1 = IntStream.range(0, 50000).boxed().collect(Collectors.toList());
        List<Integer> list2 = IntStream.range(25000, 75000).boxed().collect(Collectors.toList());
        Collections.shuffle(list1); // randomize the list so it's a real search

        // --- TEST 1: The "Slow" List Way O(n*m) ---
        long start1 = System.currentTimeMillis();
        List<Integer> result1 = list1.stream()
                .filter(list2::contains)
                .collect(Collectors.toList());
        long end1 = System.currentTimeMillis();
        System.out.println("List .contains() took: " + (end1 - start1) + " ms");

        // --- TEST 2: The "Fast" Set Way O(n+m) ---
        long start2 = System.currentTimeMillis();
        Set<Integer> list2Set = new HashSet<>(list2); // One-time cost to build hash map
        List<Integer> result2 = list1.stream()
                .filter(list2Set::contains)
                .collect(Collectors.toList());
        long end2 = System.currentTimeMillis();
        System.out.println("Set .contains() took: " + (end2 - start2) + " ms");
    }
}
