package app.contracts;

import app.entities.cars.Car;

public interface RaceInterface {

    void getWinners(String raceType);

    void addParticipant(Car car);

    int getFirstPlace(int prizePool);

    int getSecondPlace(int prizePool);

    int getThirdPlace(int prizePool);
}
