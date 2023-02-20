import model.City;
import model.Person;

import java.util.Arrays;
import java.util.List;

public class PlayWithFlatMap {
    public static void main(String[] args) {
        Person p01 = new Person("Paul", 25);
        Person p02 = new Person("Sarah", 27);
        Person p03 = new Person("James", 31);
        Person p04 = new Person("Julie", 25);
        Person p05 = new Person("charles", 22);
        Person p06 = new Person("Charlotte", 31);
        Person p07 = new Person("Ann", 27);
        Person p08 = new Person("Boris", 29);
        Person p09 = new Person("Emily", 34);

        City newYork = new City("New York", p01, p02, p03);
        City paris = new City("Paris", p04, p05, p06);
        City london = new City("New York", p07, p08, p09);

        List<City> cities = Arrays.asList(newYork, paris, london);

        long count = cities.stream()
                .flatMap(city -> city.getPeople().stream()) // returns streams of related entities
                .count();

        System.out.println(count);

        cities.stream()
                .flatMap(city -> city.getPeople().stream())
                .map(p -> p.getName())
                .forEach(name -> System.out.println(name)); // terminal operator


    }
}
