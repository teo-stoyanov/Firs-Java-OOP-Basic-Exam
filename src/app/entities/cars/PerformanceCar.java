package app.entities.cars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerformanceCar extends Car {
    private List<String> addOns;

    public PerformanceCar(String brand, String model, int yearOfProduction, int horsePower, int acceleration, int suspension, int durability) {
        super(brand, model, yearOfProduction, horsePower, acceleration, suspension, durability);
        this.addOns = (new ArrayList<>());
    }

    public void setAddOns(String ads) {
        this.addOns.add(ads);
    }

    @Override
    public void setHorsePower(int horsePower) {
        super.setHorsePower(horsePower + ((horsePower * 50) / 100));
    }

    @Override
    public void setSuspension(int suspension) {
        super.setSuspension(suspension - ((suspension * 25) / 100));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%sAdd-ons: ",super.toString()));
        if(this.addOns.size() == 0){
            sb.append("None");
        } else {
            sb.append(String.join(", ",addOns));
        }
        return sb.toString();
    }
}
