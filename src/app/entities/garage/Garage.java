package app.entities.garage;

import app.entities.cars.Car;
import app.entities.cars.PerformanceCar;
import app.entities.cars.ShowCar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Garage {
    private List<Car> parkedCars;
    private List<Integer> carsInGarage;

    public Garage() {
        this.parkedCars = (new ArrayList<>());
        this.carsInGarage = (new ArrayList<>());
    }

    public boolean isCarInGarage(int carId) {
        return carsInGarage.contains(carId);
    }

    public void addCarInGarage(int carId, Car car) {
        this.carsInGarage.add(carId);
        this.parkedCars.add(car);
    }

    public void removeCar(int carId, Car car) {
        this.parkedCars.remove(car);
        List<Integer> returnList = new ArrayList<>();
        for (Integer integer : carsInGarage) {
            if (integer == carId) {
                continue;
            }
            returnList.add(integer);
        }
        this.carsInGarage = returnList;
    }

    public void upGradeCar(int value, String adds){
        for (Car parkedCar : parkedCars) {
            parkedCar.tuneHorsePower(value);
            parkedCar.tuneSuspension(value);
            if(parkedCar.getClass().getSimpleName().equals("ShowCar")){
                ((ShowCar)parkedCar).setStars(value);
            } else {
                ((PerformanceCar)parkedCar).setAddOns(adds);
            }
        }
    }
}
