package app.entities.races;

import app.contracts.RaceInterface;
import app.entities.cars.Car;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Race implements RaceInterface {
    private int length;
    private String route;
    private int prizePool;
    private List<Car> cars;

    Race(int length, String route, int prizePool) {
        this.length = length;
        this.route = route;
        this.prizePool = prizePool;
        this.cars = (new ArrayList<>());
    }

    String getRoute() {
        return this.route;
    }

    int getLength() {
        return this.length;
    }

    int getPrizePool() {
        return this.prizePool;
    }

    @Override
    public void getWinners(String raceType) {
        switch (raceType) {
            case "CasualRace":
                this.cars.sort(Comparator.comparingInt(Car::getOverallPerformance).reversed());
                break;
            case "DragRace":
                this.cars.sort(Comparator.comparingInt(Car::getEnginePerformance).reversed());
                break;
            case "DriftRace":
                this.cars.sort(Comparator.comparingInt(Car::getSuspensionPerformance).reversed());
                break;
        }
    }

    @Override
    public void addParticipant(Car participant) {
        this.getCars().add(participant);
    }

    public List<Car> getCars() {
        return this.cars;
    }

    @Override
    public String toString() {
        return String.format("%s - %d%n", this.route, this.length);
    }

    @Override
    public int getFirstPlace(int prizePool) {
        return (prizePool * 50) / 100;
    }

    @Override
    public int getSecondPlace(int prizePool) {
        return (prizePool * 30) / 100;
    }

    @Override
    public int getThirdPlace(int prizePool) {
        return (prizePool * 20) / 100;
    }
}
