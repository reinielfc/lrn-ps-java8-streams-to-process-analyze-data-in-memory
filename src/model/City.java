package model;

public class City {
    private String name;
    private String state;
    private int population;
    private double area;


    public City(String name, String state, int population, double area) {
        this.name = name;
        this.state = state;
        this.population = population;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return String.format("City{name='%s', state='%s', population=%d, area=%s}", name, state, population, area);
    }
}
