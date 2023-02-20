import model.Person;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class AverageAge {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Paul", 12),
                new Person("Sarah", 27),
                new Person("James", 31),
                new Person("Julie", 15),
                new Person("Charles", 22)
        );

        double average = averageAgeWithForLoop(people);
        System.out.println("average = " + average);

        // average age with stream
        average = people.stream()
                .mapToInt(Person::getAge)
                .filter(age -> age > 20)
                .average() // IntStream method
                .orElseThrow(NoSuchElementException::new);

        System.out.println("average = " + average);
    }

    private static double averageAgeWithForLoop(List<Person> people) {
        int sum = 0;
        int count = 0;

        for (Person person: people) {
            if (person.getAge() > 20) {
                count++;
                sum += person.getAge();
            }
        }

        double average = 0d;
        if (count > 0) {
            average = sum / count;
        }

        return average;
    }
}
