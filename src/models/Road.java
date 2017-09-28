package models;

import java.util.ArrayDeque;
import java.util.Queue;

public class Road {

    private Road oppositeRoad;
    private Road leftRoad;

    private Queue<Car> leftTurnLane;
    private Queue<Car> straightLane1;
    private Queue<Car> straightLane2;
    private Queue<Car> rightTurnLane;

    private TrafficLight trafficLight;

    private Camera camera;

    public Road() {
        leftTurnLane = new ArrayDeque<>();
        straightLane1 = new ArrayDeque<>();
        straightLane2 = new ArrayDeque<>();
        rightTurnLane = new ArrayDeque<>();
        trafficLight = new TrafficLight();
        camera = new Camera();
    }

    private void addCarToLane(Car car) {
        switch(car.getToward()) {
            case 0 :
                leftTurnLane.add(car);
                break;
            case 1 :
                addCarToStraightLane(car);
                break;
            case 2 :
                rightTurnLane.add(car);
                break;
            default :
                break;
        }
    }

    public void assignNewCars() {
        if(newCarCame()) {
            Car car = new Car();
            addCarToLane(car);
        }
    }

    public void carMove() {
        trafficLight.
    }

    private boolean newCarCame(){
        return Math.random() < 0.5;
    }

    private void addCarToStraightLane(Car car) {
        if(straightLane1.size() < straightLane2.size()) {
            straightLane1.add(car);
        }
        else if(straightLane1.size() > straightLane2.size()) {
            straightLane2.add(car);
        }
        else {
            straightLane1.add(car);
        }
    }

    public boolean anyStraight() {
        return this.straightLane1.size() > 0 || this.straightLane2.size() > 0;
    }

    public Queue<Car> getLeftTurnLane() {
        return leftTurnLane;
    }

    public Queue<Car> getStraightLane1() {
        return straightLane1;
    }

    public Queue<Car> getStraightLane2() {
        return straightLane2;
    }

    public Queue<Car> getRightTurnLane() {
        return rightTurnLane;
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }

    public void setOppositeRoad(Road oppositeRoad) {
        this.oppositeRoad = oppositeRoad;
    }

    public void setLeftRoad(Road leftRoad) {
        this.leftRoad = leftRoad;
    }

}
