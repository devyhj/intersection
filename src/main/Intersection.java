package main;

import models.CentralControl;

public class Intersection {
    public static void main(String[] args) {
        CentralControl control = new CentralControl();
        while(true) {
            control.run();
        }
    }
}
