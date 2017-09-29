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

    //Check if car can cross
    public boolean canGo(TrafficLight trafficLight, Road oppsiteRoad, Road leftRoad) {
        if( toward == LEFT ) {
            // if it's green or yellow go
            if(trafficLight.getLeft() == TrafficLight.GREEN || trafficLight.getLeft() == TrafficLight.YELLOW) {
                return true;
            } // if it's blinking orange check opposite road if there is any car crossing
            else if(trafficLight.getLeft() == TrafficLight.BLINKING_ORANGE && !oppsiteRoad.anyStraight()) {
                return true;
            }
            else {
                return false;
            }
        }
        //there is no blinking orange, so cross if it's not red
        else if( toward == STRAIGHT && trafficLight.getStraight() != TrafficLight.RED) {
            return true;
        }
        else if( toward == RIGHT ) {
            //on any light status check if there is car coming from left road straight lane2 if there is none cross
            if(!leftRoad.anyStraightOnStraightLane2())
                return true;
            //if straight light is green, or left road straight sign is red you can cross
            else if(trafficLight.getStraight() == TrafficLight.GREEN || leftRoad.isStraightRed())
                return true;
            else
                return false;
        }
        else {
            return false;
        }

    }

}
