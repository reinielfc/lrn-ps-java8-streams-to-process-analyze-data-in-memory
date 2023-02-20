import java.util.Arrays;
import java.util.regex.Pattern;

public class StreamFromRegularExpression {
    public static void main(String[] args) {
        String sentence = "the quick brown fox jumped over the lazy dog";

        String[] words = sentence.split(" "); // new array is created (consuming additional resources)
        long count = Arrays.stream(words).count();
        System.out.println("count = " + count);

        Pattern pattern = Pattern.compile(" "); // same regular expression as split
        long count2 = pattern.splitAsStream(sentence).count(); // much more efficient than previous one
        System.out.println("count2 = " + count2);
    }
}
