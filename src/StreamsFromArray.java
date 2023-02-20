import model.Person;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamsFromArray {
    public static void main(String[] args) {
        Person p01 = new Person("Paul", 25);
        Person p02 = new Person("Sarah", 27);
        Person p03 = new Person("James", 31);
        Person p04 = new Person("Julie", 25);
        Person p05 = new Person("charles", 22);

        Person[] people = {p01, p02, p03, p04, p05};

        long count = Stream.of(people).count();
        System.out.println("count = " + count);

        Arrays.stream(people).forEach(System.out::println);
    }
}
