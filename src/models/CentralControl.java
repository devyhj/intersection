package models;

public class CentralControl {

    public static final int WEST = 0;
    public static final int EAST = 1;
    public static final int NORTH = 2;
    public static final int SOUTH = 3;

    private Road west;
    private Road east;
    private Road north;
    private Road south;

    private Road activeRoad1;
    private Road activeRoad2;

    private int remainingTime;
    private int currentPhase;
    private int currentSubPhase;

    public CentralControl() {
        this.remainingTime = 30;
        this.currentPhase = 1;
        this.currentSubPhase = 1;
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

        this.setPhase1();
        this.setSubPhase1();

        for(int i = 0; i < 10; i++) {
            this.assignNewCarsToRoads();
        }
    }

    public void run() {
        while(this.remainingTime > 0) {
            this.assignNewCarsToRoads();
            this.moveCars();

            this.remainingTime -= this.checkRoadStat();

            if(this.remainingTime == 3)
                this.doYellowLight();

            System.out.println("******************************************* Summary *******************************************");
            this.printSummary();
            System.out.println();

            System.out.println("*************************************** North Road Stat ***************************************");
            this.north.printRoadStat();
            System.out.println("***********************************************************************************************");
            System.out.println();

            System.out.println("*************************************** South Road Stat ***************************************");
            this.south.printRoadStat();
            System.out.println("***********************************************************************************************");
            System.out.println();

            System.out.println("*************************************** East Road Stat ****************************************");
            this.east.printRoadStat();
            System.out.println("***********************************************************************************************");
            System.out.println();

            System.out.println("*************************************** West Road Stat ****************************************");
            this.west.printRoadStat();
            System.out.println("***********************************************************************************************");
            System.out.println();

            try {
                Thread.sleep(1000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        this.nextPhase();
    }


    private void printSummary() {
        System.out.println("Remaining Current Time:" + remainingTime);
        System.out.println("Current Phase:" + currentPhase);
        System.out.println("Current SubPhase:" + currentSubPhase);
        System.out.println("Car Count: " + (west.getCamera().carCount() + east.getCamera().carCount() + north.getCamera().carCount() + south.getCamera().carCount()));
    }

    //turn lights to yellow 3 seconds before goes to the next phase
    private void doYellowLight() {
        if(currentSubPhase == 1) {
            activeRoad1.setTrafficLight(TrafficLight.GREEN, TrafficLight.YELLOW);
        }
        else if(currentSubPhase == 2) {
            activeRoad1.setTrafficLight(TrafficLight.YELLOW, TrafficLight.BLINKING_ORANGE);
        }
        else {
            activeRoad1.setTrafficLight(TrafficLight.YELLOW, TrafficLight.YELLOW);
            activeRoad2.setTrafficLight(TrafficLight.YELLOW, TrafficLight.YELLOW);
        }
    }

    private void assignNewCarsToRoads() {
        west.assignNewCar();
        east.assignNewCar();
        north.assignNewCar();
        south.assignNewCar();
    }

    private void moveCars() {
        west.carMove();
        east.carMove();
        north.carMove();
        south.carMove();
    }

    private void setPhase1() {
        north.setTrafficLight(TrafficLight.RED, TrafficLight.RED);
        south.setTrafficLight(TrafficLight.RED, TrafficLight.RED);
        activeRoad1 = west;
        activeRoad2 = east;
    }

    private void setPhase2() {
        west.setTrafficLight(TrafficLight.RED, TrafficLight.RED);
        east.setTrafficLight(TrafficLight.RED, TrafficLight.RED);
        activeRoad1 = north;
        activeRoad2 = south;
    }

    private void setSubPhase1() {
        activeRoad1.setTrafficLight(TrafficLight.GREEN, TrafficLight.GREEN);
        activeRoad2.setTrafficLight(TrafficLight.RED, TrafficLight.BLINKING_ORANGE);
        this.remainingTime = 15;
    }

    private void setSubPhase2() {
        activeRoad1.setTrafficLight(TrafficLight.GREEN, TrafficLight.BLINKING_ORANGE);
        activeRoad2.setTrafficLight(TrafficLight.GREEN, TrafficLight.BLINKING_ORANGE);
        this.remainingTime = 30;
    }

    private void setSubPhase3() {
        activeRoad1.setTrafficLight(TrafficLight.RED, TrafficLight.BLINKING_ORANGE);
        activeRoad2.setTrafficLight(TrafficLight.GREEN, TrafficLight.GREEN);
        this.remainingTime = 15;
    }

    // check car stat and return seconds to substract depend on the road status
    private int checkRoadStat() {

        int decrement = 0;
        if( currentSubPhase == 2 ) {
            if (activeRoad1.getCamera().carCount() == 0 && activeRoad2.getCamera().carCount() == 0) {
                decrement = 15;
            }
        }

        else if( currentSubPhase == 1 ) {
            if( activeRoad1.getCamera().leftLaneCount() == 0 ) {
                 decrement = 5;
            }
        }

        else if( currentSubPhase == 3 ) {
            if( activeRoad2.getCamera().leftLaneCount() == 0 ) {
                decrement = 5;
            }
        }
        else {
            decrement = 1;
        }

        if(remainingTime - decrement <= 3) {
            return 1;
        }

        return decrement;
    }

    public void nextPhase() {
        if(currentSubPhase < 3) {
            currentSubPhase++;
        }
        else {
            currentSubPhase = 1;
            if(currentPhase < 2) {
                currentPhase++;
            }
            else {
                currentPhase = 1;
            }
        }

        switch(currentPhase) {
            case 1:
                setPhase1();
                break;
            case 2:
                setPhase2();
                break;
            default:
                break;
        }

        switch(currentSubPhase) {
            case 1:
                setSubPhase1();
                break;
            case 2:
                setSubPhase2();
                break;
            case 3:
                setSubPhase3();
                break;
            default:
                break;
        }
    }

}
