import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SkippingAndLimiting {
    public static void main(String[] args) {
        IntStream.range(0, 30)
                .skip(10) // skip first 10 elements
                .limit(10) // limit the stream to 10 elements
                .forEach(i -> System.out.print(i + " "));

        System.out.println();

        Path path = Paths.get("data/first-names.txt");
        try (Stream<String> lines = Files.lines(path)) {
            lines.skip(20).limit(10).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
