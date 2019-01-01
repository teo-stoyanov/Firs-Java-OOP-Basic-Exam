package app.core;

import app.contracts.CarManagerInterface;
import app.entities.cars.Car;
import app.entities.cars.PerformanceCar;
import app.entities.cars.ShowCar;
import app.entities.garage.Garage;
import app.entities.races.*;
import app.utilities.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class CarManager implements CarManagerInterface {
    private Map<Integer, Car> registerCars;
    private Map<Integer, Race> raceMap;
    private Garage garage;

    public CarManager() {
        this.registerCars = new LinkedHashMap<>();
        this.raceMap = new LinkedHashMap<>();
        this.garage = new Garage();
    }

    @Override
    public void register(int id, String type, String brand, String model, int yearOfProduction,
                         int horsepower, int acceleration, int suspension, int durability) {
        if (!registerCars.containsKey(id)) {
            if (type.equals(Constants.PERFORMANCE_CAR)) {
                PerformanceCar performanceCar = new PerformanceCar(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
                registerCars.put(id, performanceCar);
            } else if (type.equals(Constants.SHOW_CAR)) {
                ShowCar showCar = new ShowCar(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
                registerCars.put(id, showCar);
            }
        }
    }

    @Override
    public String check(int id) {
        if (registerCars.containsKey(id)) {
            return registerCars.get(id).toString();
        }
        return "";
    }

    @Override
    public void open(int id, String type, int length, String route, int prizePool) {
        if (!raceMap.containsKey(id)) {
            switch (type) {
                case Constants.CASUAL_RACE:
                    CasualRace casualRace = new CasualRace(length, route, prizePool);
                    raceMap.put(id, casualRace);
                    break;
                case Constants.DRIFT_RACE:
                    DriftRace driftRace = new DriftRace(length, route, prizePool);
                    raceMap.put(id, driftRace);
                    break;
                case Constants.DRAG_RACE:
                    DragRace dragRace = new DragRace(length, route, prizePool);
                    raceMap.put(id, dragRace);
                    break;

            }
        }
    }

    @Override
    public void participate(int carId, int raceId) {
        if (registerCars.containsKey(carId) && raceMap.containsKey(raceId) && raceMap.get(raceId) != null) {
            if (!garage.isCarInGarage(carId)) {
                if (raceMap.get(raceId).getClass().getSimpleName().equals("TimeLimit")) {
                    if (raceMap.get(raceId).getCars().size() < 1) {
                        raceMap.get(raceId).addParticipant(registerCars.get(carId));
                    }
                } else {
                    raceMap.get(raceId).addParticipant(registerCars.get(carId));
                }
            }
        }
    }

    @Override
    public String start(int raceId) {
        if (raceMap.containsKey(raceId) && raceMap.get(raceId) != null) {
            if (raceMap.get(raceId).getCars().size() == 0) {
                return "Cannot start the race with zero participants." + System.lineSeparator();
            } else if (raceMap.get(raceId).getClass().getSimpleName().equals("TimeLimit")) {
                Car car = raceMap.get(raceId).getCars().get(0);
                int racerTime = ((TimeLimit) raceMap.get(raceId)).timePerformance(car);
                int wonPrize = ((TimeLimit) raceMap.get(raceId)).wonPrize(racerTime);
                String wonMedal = ((TimeLimit) raceMap.get(raceId)).onMedal(racerTime);
                return ((TimeLimit) raceMap.get(raceId)).myString(car, racerTime, wonMedal, wonPrize);
            } else {
                raceMap.get(raceId).getWinners(raceMap.get(raceId).getClass().getSimpleName());
                String result = raceMap.get(raceId).toString();
                raceMap.put(raceId, null);
                return result;
            }
        }
        return "";
    }

    @Override
    public void park(int id) {
        if (registerCars.containsKey(id)) {
            Car car = registerCars.get(id);
            if (!isCarInRace(car)) {
                this.garage.addCarInGarage(id, car);
            }
        }
    }

    @Override
    public void unpark(int id) {
        if (registerCars.containsKey(id)) {
            Car car = registerCars.get(id);
            if (!isCarInRace(car)) {
                if (garage.isCarInGarage(id)) {
                    garage.removeCar(id, car);
                }
            }
        }
    }

    @Override
    public void tune(int tuneIndex, String addOn) {
        garage.upGradeCar(tuneIndex, addOn);
    }

    @Override
    public boolean isCarInRace(Car car) {
        for (Map.Entry<Integer, Race> integerRaceEntry : raceMap.entrySet()) {
            if (integerRaceEntry.getValue() != null && integerRaceEntry.getValue().getCars().contains(car)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void openTimeLimit(int id, String type, int length, String route, int prizePool, int setTime) {
        if (!raceMap.containsKey(id)) {
            TimeLimit timeLimit = new TimeLimit(length, route, prizePool, setTime);
            raceMap.put(id, timeLimit);
        }
    }
}
