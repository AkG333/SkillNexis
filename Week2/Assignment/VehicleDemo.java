class Vehicle {
    protected String brand;
    protected int year;

    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Year: " + year);
    }
}

class Car extends Vehicle {

    private int numberOfDoors;

    public Car(String brand, int year, int numberOfDoors) {
        super(brand, year);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public void displayInfo() {
        System.out.println("Car Information");
        System.out.println("----------------");
        System.out.println("Brand: " + brand);
        System.out.println("Year: " + year);
        System.out.println("Number of Doors: " + numberOfDoors);
    }
}

class Bike extends Vehicle {

    private boolean hasGears;

    public Bike(String brand, int year, boolean hasGears) {
        super(brand, year);
        this.hasGears = hasGears;
    }

    @Override
    public void displayInfo() {
        System.out.println("Bike Information");
        System.out.println("-----------------");
        System.out.println("Brand: " + brand);
        System.out.println("Year: " + year);
        System.out.println("Has Gears: " + hasGears);
    }
}

// Main class
public class VehicleDemo {

    public static void main(String[] args) {

        Car car = new Car("Toyota", 2022, 4);
        Bike bike = new Bike("Yamaha", 2023, true);

        car.displayInfo();

        System.out.println();

        bike.displayInfo();
    }
}