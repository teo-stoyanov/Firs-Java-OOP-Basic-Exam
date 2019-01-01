package app.entities.races;

import app.entities.cars.Car;

public class TimeLimit extends Race {
    private int goldTime;

    public TimeLimit(int length, String route, int prizePool, int goldTime) {
        super(length, route, prizePool);
        this.goldTime = goldTime;
    }

    public int timePerformance(Car car) {
        return super.getLength() * ((car.getHorsePower() / 100) * car.getAcceleration());
    }

    public int wonPrize(int raceTime) {
        if (raceTime <= this.goldTime) {
            return super.getPrizePool();
        } else if (raceTime <= this.goldTime + 15) {
            return super.getPrizePool() / 2;
        } else {
            return (super.getPrizePool() * 30) / 100;
        }
    }

    public String onMedal(int raceTime) {
        if (raceTime <= this.goldTime) {
            return "Gold Time";
        } else if (raceTime <= this.goldTime + 15) {
            return "Silver Time";
        } else {
            return "Bronze Time";
        }
    }

    public String myString(Car car, int timePerformance, String medal, int wonPrize) {

        return String.format("%s - %d%n%s %s - %d s.%n%s, $%d.%n", super.getRoute(), super.getLength()
                , car.getBrand(), car.getModel(), timePerformance, medal, wonPrize);
    }
}
