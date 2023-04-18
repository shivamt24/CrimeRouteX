package edu.neu.info6205.SimulatedAnnealing;

public class Location {
    private double x;
    private double y;
    public static final double R = 6371000; // Earth's radius in meters

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceToLocation(Location city) {
        double lat1 = Math.toRadians(getX());
        double lat2 = Math.toRadians(city.getX());
        double lon1 = Math.toRadians(getY());
        double lon2 = Math.toRadians(city.getY());
    
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;
    
        double a = Math.pow(Math.sin(dLat/2), 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.pow(Math.sin(dLon/2), 2);
    
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    
        return R * c;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }
}
