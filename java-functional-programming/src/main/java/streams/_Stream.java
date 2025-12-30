package streams;

import imperative.Main;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class _Stream {

    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("John", Gender.MALE),
                new Person("Alice", Gender.FEMALE),
                new Person("Elizabeth", Gender.FEMALE),
                new Person("Mike", Gender.MALE),
                new Person("Max", Gender.MALE)
        );

//        people.stream()
//                .map(person -> person.name)
//                .mapToInt(name -> name.length())
//                .forEach(x -> System.out.println(x));

        // only have FEMALE
        Predicate<Person> personPredicate = person -> Gender.FEMALE.equals(person.gender);

        boolean containsOnlyFemales = people.stream()
                        .anyMatch(personPredicate);

        System.out.println(containsOnlyFemales);

//        people.stream()
//                .allMatch(person -> Gender.FEMALE.equals(person.gender));
    }

    static class Person {
        private final String name;
        private final Gender gender;

        public Person(String name, Gender gender) {
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }
    }

    enum Gender {
        MALE, FEMALE
    }
}
