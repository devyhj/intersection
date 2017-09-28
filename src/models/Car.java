package models;

import java.util.Random;

public class Car {

    private final int RED = 0;
    private final int YELLOW = 1;
    private final int BLINKING_ORANGE = 2;
    private final int GREEN = 3;

    //where the car is heading to. 0 = left, 1 = straight, 2 = right
    private int toward;

    public Car() {
        Random random = new Random();
        this.toward = random.nextInt(3);
    }

    public int getToward() {
        return toward;
    }

    public boolean canGo(TrafficLight trafficLight, Road oppsiteRoad, Road leftRoad) {
        if( toward == 0 ) {
            if(trafficLight.getLeft() == GREEN || trafficLight.getLeft() == YELLOW) {
                return true;
            }
            else if(trafficLight.getLeft() == BLINKING_ORANGE && !oppsiteRoad.anyStraight()) {
                return true;
            }
        }
        else if( toward == 1 && trafficLight.getStraight() != RED) {

        }

    }

}
