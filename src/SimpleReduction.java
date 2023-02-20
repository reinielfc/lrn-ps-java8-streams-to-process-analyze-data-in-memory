import java.util.*;

public class SimpleReduction {
    public static void main(String[] args) {
        openingAnOptional();
        openingAnEmptyOptional();
        passingAWrongIdentityElementToReductions();
    }

    public static void openingAnOptional() {
        System.out.println("\nSimpleReduction.openingAnOptional");
        List<Integer> ints = Arrays.asList(1, 1, 1, 1, 1);

        Optional<Integer> reduce = ints.stream().reduce((i1, i2) -> i1 + i2); // no identity element
        System.out.println("reduce = " + reduce);

        Integer sum = reduce.get();
        System.out.println("sum = " + sum);
    }

    public static void openingAnEmptyOptional() {
        System.out.println("\nSimpleReduction.openingAnEmptyOptional");
        List<Integer> ints = Collections.emptyList();

        Optional<Integer> reduce = ints.stream().reduce((i1, i2) -> i1 + i2); // no identity element
        System.out.println("reduce = " + reduce);

        try {
            Integer sum = reduce.get();
            //Integer sum = reduce.orElseThrow(NoSuchElementException::new);
            System.out.println("sum = " + sum);
        } catch (NoSuchElementException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void passingAWrongIdentityElementToReductions() {
        System.out.println("\nSimpleReduction.passingAWrongIdentityElementToReductions");
        List<Integer> ints = Collections.emptyList();
        int sum = ints.stream().reduce(0, (i1, i2) -> i1 + i2); // 0 is the identity element (taken into account whether the stream is empty or not)
        System.out.println("sum = " + sum);
    }
}
