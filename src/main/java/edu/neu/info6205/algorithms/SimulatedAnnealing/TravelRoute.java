package edu.neu.info6205.algorithms.SimulatedAnnealing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.neu.info6205.algorithms.SimulatedAnnealing.utils.RouteWriter;

public class TravelRoute {

    private ArrayList<Location> travel = new ArrayList<>();
    private ArrayList<Location> previousTravel = new ArrayList<>();

    public TravelRoute(List<Location> coordinates) {
        for (int i = 0; i < coordinates.size(); i++) {
            double x = coordinates.get(i).getX();
            double y = coordinates.get(i).getY();
            String crimeId = coordinates.get(i).getCrimeId();
            travel.add(new Location(x, y, crimeId));
        }
    }

    public void generateInitialRoute() {
        if (travel.isEmpty()) {
            throw new IllegalStateException("Coordinates data is empty");
        }
        Collections.shuffle(travel);
    }

    public void swapLocations() {
        int a = generateRandomIndex();
        int b = generateRandomIndex();
        previousTravel = new ArrayList<>(travel);
        Location x = travel.get(a);
        Location y = travel.get(b);
        travel.set(a, y);
        travel.set(b, x);
    }

    public void getOptimalRoute(Double bestDistance) {
        RouteWriter.write(travel, "./src/main/resources/outputs/annealing.csv", bestDistance);
    }

    public void revertSwap() {
        travel = previousTravel;
    }

    private int generateRandomIndex() {
        return (int) (Math.random() * travel.size());
    }

    public Location getLocation(int index) {
        return travel.get(index);
    }

    public int getDistance() {
        int distance = 0;
        for (int index = 0; index < travel.size(); index++) {
            Location starting = getLocation(index);

            Location destination;
            if (index + 1 < travel.size()) {
                destination = getLocation(index + 1);
            } else {
                destination = getLocation(0);
            }
            distance += starting.distanceToLocation(destination);
        }
        return distance;
    }
}
