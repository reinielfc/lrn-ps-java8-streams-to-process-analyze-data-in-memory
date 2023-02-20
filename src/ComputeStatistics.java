import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.DoubleSummaryStatistics;
import java.util.NoSuchElementException;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

public class ComputeStatistics {
    public static void main(String[] args) {
        String lineForNewYork = "1;New York;New York;8336817;780.9";
        ToDoubleFunction<String> lineToDensity = line -> {
            String [] tokens = line.split(";");

            String populationAsString = tokens[3];
            int population = Integer.parseInt(populationAsString);

            String landAreaAsString = tokens[4].replace(",","");
            double landArea = Double.parseDouble(landAreaAsString);

            return population / landArea;
        };

        double density = lineToDensity.applyAsDouble(lineForNewYork);
        System.out.printf("density of New York = %s people per square kilometer%n", density);

        printMaxDensity(lineToDensity);
        printSummaryStatistics(lineToDensity);
    }

    private static void printMaxDensity(ToDoubleFunction<String> lineToDensity) {
        // https://en.wikipedia.org/w/index.php?title=List_of_United_States_cities_by_population
        Path path = Paths.get("data/cities.csv");
        try (Stream<String> lines = Files.lines(path, StandardCharsets.ISO_8859_1)) {
            double maxDensity = lines.skip(2)
                    .mapToDouble(lineToDensity)
                    .max()
                    .orElseThrow(NoSuchElementException::new);
            System.out.println("maxDensity = " + maxDensity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printSummaryStatistics(ToDoubleFunction<String> lineToDensity) {
        // https://en.wikipedia.org/w/index.php?title=List_of_United_States_cities_by_population
        Path path = Paths.get("data/cities.csv");
        try (Stream<String> lines = Files.lines(path, StandardCharsets.ISO_8859_1)) {
            DoubleSummaryStatistics summaryStatistics = lines.skip(2)
                    .mapToDouble(lineToDensity)
                    .summaryStatistics();
            System.out.println("summaryStatistics = " + summaryStatistics);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
