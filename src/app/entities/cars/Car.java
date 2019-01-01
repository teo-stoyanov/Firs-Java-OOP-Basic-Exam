package app.entities.cars;

public abstract class Car {
    private String brand;
    private String model;
    private int yearOfProduction;
    private int horsePower;
    private int acceleration;
    private int suspension;
    private int durability;

    public Car(String brand, String model, int yearOfProduction, int horsePower, int acceleration, int suspension, int durability) {
        this.brand = brand;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.setHorsePower(horsePower);
        this.acceleration = acceleration;
        this.setSuspension(suspension);
        this.durability = durability;
    }

    public int getAcceleration() {
        return this.acceleration;
    }

    public int getHorsePower() {
        return this.horsePower;
    }

    public void tuneSuspension(int susp){
        int tune = getSuspension() + (susp / 2);
        this.suspension = tune;
    }

    public void tuneHorsePower(int hp){
        int tune = getHorsePower() + hp;
        this.horsePower = tune;
    }

    public int getSuspension() {
        return this.suspension;
    }

    public String getModel() {
        return this.model;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public void setSuspension(int suspension) {
        this.suspension = suspension;
    }

    public int getOverallPerformance(){
        return (horsePower / acceleration) + (suspension + durability);
    }

    public int getEnginePerformance(){
        return (horsePower / acceleration);
    }

    public int getSuspensionPerformance(){
        return (suspension + durability);
    }

    @Override
    public String toString() {

        return String.format("%s %s %d%n%d HP, 100 m/h in %d s%n%d Suspension force, %d Durability%n",this.brand
        ,this.model,this.yearOfProduction,this.horsePower,this.acceleration,this.suspension,this.durability);
    }
}
