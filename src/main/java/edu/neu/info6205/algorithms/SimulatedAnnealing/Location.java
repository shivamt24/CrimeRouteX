package edu.neu.info6205.algorithms.SimulatedAnnealing;

import edu.neu.info6205.algorithms.Christofides.MiscUtil;

public class Location {
    private double x;
    private double y;
    private String crimeId;

    public Location(double x, double y, String crimeId) {
        this.x = x;
        this.y = y;
        this.crimeId = crimeId;
    }

    public double distanceToLocation(Location city) {
        return MiscUtil.haversineDistance(getX(), city.getX(), getY(), city.getY());
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public String getCrimeId() {
        return crimeId;
    }
}
