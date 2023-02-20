import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class DropWhileTakeWhile {
    public static void main(String[] args) {
        Class<?> cls = ArrayList.class;
        cls.getSuperclass();

        Stream.<Class<?>>iterate(cls, Class::getSuperclass)
                //.takeWhile(Objects::nonNull) // <- introduced in Java 9, TAKEs elements from original stream WHILE the predicate holds true
                .filter(Objects::nonNull) // tests ALL elements in the stream
                .forEach(System.out::println);
    }
}
