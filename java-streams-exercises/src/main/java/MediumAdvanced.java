import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MediumAdvanced {
    public static void main(String[] args) {
//        _q41();
//        _q42();
        _q43();
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
}
