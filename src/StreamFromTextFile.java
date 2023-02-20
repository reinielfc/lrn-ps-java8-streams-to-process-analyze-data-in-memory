import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StreamFromTextFile {
    public static void main(String[] args) {
        Path path = Paths.get("data/first-names.txt");

        try (Stream<String> lines = Files.lines(path)) { // try with resources
            long count = lines.count();
            System.out.println("count = " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
