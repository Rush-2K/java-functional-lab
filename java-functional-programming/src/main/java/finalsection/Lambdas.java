package finalsection;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Lambdas {
    public static void main(String[] args) {

        Integer number = null;
//        int counter = null; (invalid bcs it's primitive of object type Integer)
        // with primitive you cannot pass null
        // static can only be use or works inside a static

        // if no logic
        Function<String, String> upperCaseName = name -> name.toUpperCase();

        // with logic
        BiFunction<String, Integer, String> upperCaseName2 = (name, age) -> {
            if (name.isBlank()) throw new IllegalArgumentException("");
            System.out.println(age);
            return name.toUpperCase();
        };

        String upperCasedName2 = upperCaseName2.apply("Alex", 20);

        System.out.println(upperCasedName2);
    }
}
