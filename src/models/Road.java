package models;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Road {

    private Road oppositeRoad;
    private Road leftRoad;

    private ArrayDeque<Car> leftTurnLane;
    private ArrayDeque<Car> straightLane1;
    private ArrayDeque<Car> straightLane2;
    private ArrayDeque<Car> rightTurnLane;

    private TrafficLight trafficLight;

    private Camera camera;

    public Road() {
        leftTurnLane = new ArrayDeque<>();
        straightLane1 = new ArrayDeque<>();
        straightLane2 = new ArrayDeque<>();
        rightTurnLane = new ArrayDeque<>();
        trafficLight = new TrafficLight();
        camera = new Camera(this);
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

    private void putCarBackToLane(Car car) {
        switch(car.getToward()) {
            case 0 :
                leftTurnLane.addFirst(car);
                break;
            case 1 :
                addFirstCarToStraightLane(car);
                break;
            case 2 :
                rightTurnLane.addFirst(car);
                break;
            default :
                break;
        }
    }

    public void assignNewCar() {
        if(newCarCame()) {
            Car car = new Car();
            addCarToLane(car);
        }
    }

    public void carMove() {
        List<Car> cars = new ArrayList<>();
        cars.add(leftTurnLane.poll());
        cars.add(straightLane1.poll());
        cars.add(straightLane2.poll());
        cars.add(rightTurnLane.poll());
        for( Car thisCar : cars ) {
            if( thisCar != null ) {
                if( !thisCar.canGo(this.trafficLight, this.oppositeRoad, this.leftRoad) ) {
                    putCarBackToLane(thisCar);
                }
            }
        }
    }

    private boolean newCarCame(){
        return Math.random() < 0.4;
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

    private void addFirstCarToStraightLane(Car car) {
        if(straightLane1.size() < straightLane2.size()) {
            straightLane1.addFirst(car);
        }
        else if(straightLane1.size() > straightLane2.size()) {
            straightLane2.addFirst(car);
        }
        else {
            straightLane1.addFirst(car);
        }
    }

    public boolean anyStraight() {
        return this.straightLane1.size() > 0 || this.straightLane2.size() > 0;
    }

    public boolean anyStraightOnStraightLane2() {
        return this.straightLane2.size() > 0;
    }

    public void setTrafficLight(int straightColor, int leftColor) {
        this.trafficLight.setStraight(straightColor);
        this.trafficLight.setLeft(leftColor);
    }

    public void setOppositeRoad(Road oppositeRoad) {
        this.oppositeRoad = oppositeRoad;
    }

    public void setLeftRoad(Road leftRoad) {
        this.leftRoad = leftRoad;
    }

    public ArrayDeque<Car> getLeftTurnLane() {
        return leftTurnLane;
    }

    public ArrayDeque<Car> getStraightLane1() {
        return straightLane1;
    }

    public ArrayDeque<Car> getStraightLane2() {
        return straightLane2;
    }

    public ArrayDeque<Car> getRightTurnLane() {
        return rightTurnLane;
    }

    public Camera getCamera() {
        return camera;
    }

    public void printRoadStat() {
        String straightColor = colorIntToString(trafficLight.getStraight());
        String leftColor = colorIntToString(trafficLight.getLeft());

        System.out.println("Straight Light: " + straightColor);
        System.out.println("Left Light: " + leftColor);
        System.out.println("Left Turn Lane: " + this.getCamera().leftLaneCount());
        System.out.println("Straight Lane 1: " + this.getCamera().straightLane1Count());
        System.out.println("Straight Lane 2: " + this.getCamera().straightLane2Count());
        System.out.println("Right Turn Lane: " + this.getCamera().rightLaneCount());
    }

    private String colorIntToString(int color) {
        String stringColor = "";
        switch(color) {
            case TrafficLight.RED:
                stringColor = "Red";
                break;
            case TrafficLight.YELLOW:
                stringColor = "Yellow";
                break;
            case TrafficLight.BLINKING_ORANGE:
                stringColor = "Blinking Orange";
                break;
            case TrafficLight.GREEN:
                stringColor = "Green";
                break;
            default:
                break;
        }
        return stringColor;
    }
}
