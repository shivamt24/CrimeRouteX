package edu.neu.info6205.SimulatedAnnealing;

import java.util.ArrayList;
import java.util.Collections;

import edu.neu.info6205.SimulatedAnnealing.utils.FileUtil;

public class TravelRoute {

    private ArrayList<Location> travel = new ArrayList<>();
    private ArrayList<Location> previousTravel = new ArrayList<>();

    public TravelRoute(double[][] coordinates) {
        for (int i = 0; i < coordinates.length; i++) {
            double x = coordinates[i][0];
            double y = coordinates[i][1];
            travel.add(new Location(x, y));
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


    public void getOptimalRoute() {
        FileUtil.writeToFile(travel, "./src/main/resources/outputs/SimulatedAnnealingOutput.csv");
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

