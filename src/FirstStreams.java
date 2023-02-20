import model.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FirstStreams {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Paul", 25),
                new Person("Sarah", 27),
                new Person("James", 31),
                new Person("Julie", 25),
                new Person("charles", 22),
                new Person("Charlotte", 31),
                new Person("Ann", 27),
                new Person("Boris", 29),
                new Person("Emily", 34),
                new Person("", 34)
        );

        /*
        java stream:
        - an implementation of map / filter / reduce
        - does not carry any data
        - intermediate operations (return new streams)
        - terminal operations (must have)
         */

        Stream<Person> stream = people.stream();
        Stream<String> nameStream = stream.map(p -> p.getName()); // a stream contains no data, no person object has been mapped by the map operation
        Stream<String> emptyNamesStream = nameStream.filter(name -> name.isEmpty()); // another intermediate operation
        long count = emptyNamesStream.count(); // process of this stream is triggered by this terminal operation

        System.out.println("Empty names = " + count);

        // you are not allowed to call the same stream twice
        // the right way to write streams
        count = people.stream()
                .map(p -> p.getName())
                .filter(name -> !name.isEmpty())
                .count();

        System.out.println("NonEmpty names = " + count);
    }
}
