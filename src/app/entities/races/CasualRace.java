package app.entities.races;

import app.entities.cars.Car;

public class CasualRace extends Race {
    public CasualRace(int length, String route, int prizePool) {
        super(length, route, prizePool);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(java.lang.String.format("%s", super.toString()));
        int count = 0;
        int prize;
        for (Car car : super.getCars()) {
            count++;
            if (count > 3) {
                break;
            }
            if (count == 1) {
                prize = super.getFirstPlace(getPrizePool());
            } else if (count == 2) {
                prize = super.getSecondPlace(getPrizePool());
            } else {
                prize = super.getThirdPlace(getPrizePool());
            }
            sb.append(String.format("%d. %s %s %dPP - $%d%n", count, car.getBrand(), car.getModel(), car.getOverallPerformance(), prize));
        }
        return sb.toString();
    }
}
