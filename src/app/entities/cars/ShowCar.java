package app.entities.cars;

public class ShowCar extends Car {
    private int stars;

    public ShowCar(String brand, String model, int yearOfProduction, int horsePower, int acceleration, int suspension, int durability) {
        super(brand, model, yearOfProduction, horsePower, acceleration, suspension, durability);
        this.stars = 0;
    }

    public void setStars(int stars) {
        this.stars += stars;
    }

    @Override
    public String toString() {
        return String.format("%s%d *",super.toString(),this.stars);
    }
}
