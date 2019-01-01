package app.contracts;

import app.entities.cars.Car;

public interface CarManagerInterface {

    void register(int id, String type, String brand, String model, int yearOfProduction,
                  int horsepower, int acceleration, int suspension, int durability);

    String check(int id);
    void open(int id, String type, int length, String route, int prizePool);

    void participate(int carId , int raceId);

    String start(int raceId);

    void park(int id);

    void unpark(int id);

    void tune(int tuneIndex, String addOn);

    boolean isCarInRace(Car car);

    void openTimeLimit(int id, String type, int length, String route, int prizePool, int setTime);
}
