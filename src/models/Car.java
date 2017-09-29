package models;

import java.util.Random;

public class Car {

    private final int LEFT = 0;
    private final int STRAIGHT = 1;
    private final int RIGHT = 2;

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
        if( toward == LEFT ) {
            if(trafficLight.getLeft() == TrafficLight.GREEN || trafficLight.getLeft() == TrafficLight.YELLOW) {
                return true;
            }
            else if(trafficLight.getLeft() == TrafficLight.BLINKING_ORANGE && !oppsiteRoad.anyStraight()) {
                return true;
            }
            else {
                return false;
            }
        }
        else if( toward == STRAIGHT && trafficLight.getStraight() != TrafficLight.RED) {
            return true;
        }
        else if( toward == RIGHT ) {
            if(!leftRoad.anyStraightOnStraightLane2())
                return true;
            else if(trafficLight.getStraight() == TrafficLight.GREEN)
                return true;
            else
                return false;
        }
        else {
            return false;
        }

    }

}
