package com.cheku.cheku.data_representation;

public class MapPositions {
    
    private int tripNumber;
    private Coordinates start;
    private Coordinates end;

    public MapPositions() { }

    public Coordinates getEnd() {
        return end;
    }

    public Coordinates getStart() {
        return start;
    }

    public int getTripNumber() {
        return tripNumber;
    }

    public void setEnd(Coordinates end) {
        this.end = end;
    }

    public void setStart(Coordinates start) {
        this.start = start;
    }

    public void setTripNumber(int tripNumber) {
        this.tripNumber = tripNumber;
    }
}
