import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieRental {
    private final List<Rental> rentals = new ArrayList<>();

    public static void main(String[] args) {
        MovieRental movieRental = new MovieRental();
        movieRental.addRental("Blade Runner", 2);
        movieRental.addRental("Frozen", 3);
        movieRental.addRental("Star Wars", 1);

        System.out.println(movieRental.statement());
        System.out.println();
        System.out.println(movieRental.statementRefactored());
    }

    public String statementRefactored() {
        // a stream does one thing at a time
        double totalAmount =
                rentals.stream()
                        .mapToDouble(this::computeRentalAmount)
                        .sum();

        int frequentRenterPoints =
                rentals.stream()
                        .mapToInt(this::getFrequentRenterPoints)
                        .sum();

        String statement = composeHeader() +
                rentals.stream()
                        .map(this::computeStatementLine)
                        .collect(Collectors.joining())
                + composeFooter(totalAmount, frequentRenterPoints);


        return statement;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String statement = composeHeader();

        for (Rental rental : rentals) {
            totalAmount += computeRentalAmount(rental);
            frequentRenterPoints += getFrequentRenterPoints(rental);
            statement += computeStatementLine(rental);
        }

        statement += composeFooter(totalAmount, frequentRenterPoints);

        return statement;
    }

    private String composeFooter(double totalAmount, int frequentRenterPoints) {
        return String.format(
                "\nTotal amount owed: %s\nFrequent renter points earned: %d", totalAmount, frequentRenterPoints);
    }

    private int getFrequentRenterPoints(Rental rental) {
        return (rental.daysRented + 1) / 2;
    }

    private String computeStatementLine(Rental rental) {
        return "\n\t" + rental.name + ": " + computeRentalAmount(rental);
    }

    private double computeRentalAmount(Rental rental) {
        return rental.daysRented + 2;
    }

    private String composeHeader() {
        return "Statement for the rental of " + rentals.size() + " movies";
    }

    public void addRental(String name, int daysRented) {
        Rental rental = new Rental(name, daysRented);
        this.rentals.add(rental);
    }

    private static class Rental {
        String name;
        int daysRented;

        public Rental(String name, int daysRented) {
            this.name = name;
            this.daysRented = daysRented;
        }
    }
}
