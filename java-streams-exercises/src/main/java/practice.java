import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class practice {
    public static void main(String[] args) {
        String name = "Hamilton";
        String newName = reverse(name);
//        System.out.println("Original Name: " + name);
//        System.out.println("Reversed: " + newName);

        loopString();
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
}
