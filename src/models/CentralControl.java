package models;

public class CentralControl {
    private Road west;
    private Road east;
    private Road north;
    private Road south;

    public CentralControl() {
        west = new Road();
        east = new Road();
        north = new Road();
        south = new Road();

        west.setOppositeRoad(east);
        west.setLeftRoad(north);

        east.setOppositeRoad(west);
        east.setLeftRoad(south);

        north.setOppositeRoad(south);
        north.setLeftRoad(east);

        south.setOppositeRoad(north);
        south.setLeftRoad(west);
    }

    public void assignCarsToRoad() {
        west.assignNewCars();
        east.assignNewCars();
        north.assignNewCars();
        south.assignNewCars();
    }

    public Road getWest() {
        return west;
    }

    public Road getEast() {
        return east;
    }

    public Road getNorth() {
        return north;
    }

    public Road getSouth() {
        return south;
    }
}
