public class StreamFromString {
    public static void main(String[] args) {
        String sentence = "the quick brown fox jumped over the lazy dog";
        sentence.chars() // IntStream
                .mapToObj(codePoint -> Character.toString((char) codePoint)) // map character to String
                .filter(letter -> !letter.equals(" ")) // take out spaces
                .distinct() // get distinct characters
                .sorted()   // sort them
                .forEach(System.out::print);
    }
}
