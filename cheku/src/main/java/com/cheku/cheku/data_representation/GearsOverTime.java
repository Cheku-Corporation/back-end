package com.cheku.cheku.data_representation;

public class GearsOverTime {
    
    private int timeInGear0;
    private int timeInGear1;
    private int timeInGear2;
    private int timeInGear3;
    private int timeInGear4;
    private int timeInGear5;
    private int timeInGear6;

    public GearsOverTime() {
        timeInGear0 = 0;
        timeInGear1 = 0;
        timeInGear2 = 0;
        timeInGear3 = 0;
        timeInGear4 = 0;
        timeInGear5 = 0;
        timeInGear6 = 0;
    }

    public int getTimeInGear0() {
        return timeInGear0;
    }

    public float getTimeInGear1() {
        return timeInGear1 / 10;
    }

    public float getTimeInGear2() {
        return timeInGear2 / 10;
    }

    public float getTimeInGear3() {
        return timeInGear3 / 10;
    }

    public float getTimeInGear4() {
        return timeInGear4 / 10;
    }

    public float getTimeInGear5() {
        return timeInGear5;
    }

    public float getTimeInGear6() {
        return timeInGear6;
    }

    public void incrementTimeGear0() {
        timeInGear0++;
    }

    public void incrementTimeGear1() {
        timeInGear1++;
    }

    public void incrementTimeGear2() {
        timeInGear2++;
    }

    public void incrementTimeGear3() {
        timeInGear3++;
    }

    public void incrementTimeGear4() {
        timeInGear4++;
    }

    public void incrementTimeGear5() {
        timeInGear5++;
    }

    public void incrementTimeGear6() {
        timeInGear6++;
    }
}
