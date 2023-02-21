import model.City;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComputeStatistics {
    public static void main(String[] args) {
        Function<String, City> lineToCity = line -> {
            String[] tokens = line.split(";");

            String cityName = tokens[1];
            String state = tokens[2];
            int population = Integer.parseInt(tokens[3]);

            String landAreaAsString = tokens[4].replace(",", "");
            double landArea = Double.parseDouble(landAreaAsString);

            return new City(cityName, state, population, landArea);
        };

        // https://en.wikipedia.org/w/index.php?title=List_of_United_States_cities_by_population
        Path path = Paths.get("data/cities.csv");
        Set<City> cities = null;
        try (Stream<String> lines = Files.lines(path, StandardCharsets.ISO_8859_1)) {
            cities = lines.skip(2)
                    .map(lineToCity)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("cities.size() = " + cities.size());

        Map<String, List<City>> citesPerStateMap = cities.stream()
                .collect(Collectors.groupingBy(City::getState));

        System.out.println("citesPerStateMap.size() = " + citesPerStateMap.size());

        List<City> citiesOfUtah = citesPerStateMap.get("Utah");
        System.out.println("citiesOfUtah = " + citiesOfUtah);

        Map<String, Long> numberOfCitiesPerStateMap = cities.stream()
                .collect(Collectors.groupingBy(City::getState,
                        Collectors.counting())); // downstream collector

        Long numberOfCitiesInUtah = numberOfCitiesPerStateMap.get("Utah");
        System.out.println("numberOfCitiesInUtah = " + numberOfCitiesInUtah);

        Map.Entry<String, Long> stateWithMostCities =
                numberOfCitiesPerStateMap.entrySet().stream() // no stream method for Map, we use the entry set
                        //.max(Comparator.comparing(Map.Entry::getValue))
                        .max(Map.Entry.comparingByValue())
                        .orElseThrow(NoSuchElementException::new);
        System.out.println("stateWithMostCities = " + stateWithMostCities);

        int populationOfUtah = citesPerStateMap.get("Utah").stream()
                //.mapToInt(City::getPopulation).sum()
                .collect(Collectors.summingInt(City::getPopulation));
        System.out.println("populationOfUtah = " + populationOfUtah);

        Map<String, Integer> populationOfCitiesPerStateMap = cities.stream()
                .collect(Collectors.groupingBy(City::getState,
                        Collectors.summingInt(City::getPopulation)));
        Integer populationOfUtah2 = populationOfCitiesPerStateMap.get("Utah");
        System.out.println("populationOfUtah2 = " + populationOfUtah2);

        Map.Entry<String, Integer> stateWithTheMostPeople = populationOfCitiesPerStateMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(NoSuchElementException::new);
        System.out.println("stateWithTheMostPeople = " + stateWithTheMostPeople);
    }

}
