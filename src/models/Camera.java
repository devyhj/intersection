package models;

public class Camera {

    private Road watchingRoad;

    public Camera(Road road) {
        this.watchingRoad = road;
    }

    //Count all cars on the road
    public int carCount() {
        int carCount = 0;

        carCount += this.watchingRoad.getLeftTurnLane().size();
        carCount += this.watchingRoad.getRightTurnLane().size();
        carCount += this.watchingRoad.getStraightLane1().size();
        carCount += this.watchingRoad.getStraightLane2().size();

        return carCount;
    }

    public int leftLaneCount() {
        return this.watchingRoad.getLeftTurnLane().size();
    }

    public int straightLane1Count() {
        return this.watchingRoad.getStraightLane1().size();
    }

    public int straightLane2Count() {
        return this.watchingRoad.getStraightLane2().size();
    }

    public int rightLaneCount() {
        return this.watchingRoad.getRightTurnLane().size();
    }

}
