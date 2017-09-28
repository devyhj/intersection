package models;

public class TrafficLight {

    // 0 = red, 1 = yellow, 2 = blinking orange 3 = green
    private int straight;
    private int left;


    public TrafficLight() {
        this.straight = 0;
        this.left = 0;
    }

    public void setStraight(int color) {
        this.straight = color;
    }

    public void setLeft(int color) {
        this.left = color;
    }

    public int getStraight() {
        return straight;
    }

    public int getLeft() {
        return left;
    }

}
