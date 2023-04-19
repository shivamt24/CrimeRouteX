package edu.neu.info6205.SimulatedAnnealing;

import java.util.ArrayList;
import java.util.List;

import edu.neu.info6205.algorithms.Christofides.FileUtil;

public class SimulatedAnnealing {

    public static double simulateAnnealing(double startingTemperature, int numberOfIterations, double coolingRate) {
        // Read from csv file
        List<String> fileData = FileUtil.readFile("./src/main/resources/inputs/info6205.spring2023.teamproject.csv");
        List<Location> coordinates = new ArrayList<>();

        for (int i = 0; i < fileData.size(); i++) {
            if (i == 0)
                continue;
            String[] fields = fileData.get(i).split(",");
            String hexString = fields[0];
            String last5Chars = hexString.substring(hexString.length() - 5);
            Location locationObject = new Location(Double.parseDouble(fields[1]), Double.parseDouble(fields[2]),
                    last5Chars);
            coordinates.add(locationObject);
        }
        TravelRoute travelRoute = new TravelRoute(coordinates);
        System.out.println("Starting Simulated Annealing \n Temperature=" + startingTemperature
                + " Iterations=" + numberOfIterations + " Cooling rate=" + coolingRate);

        travelRoute.generateInitialRoute();
        double thresholdTemperature = 0.1; // Terminate when temperature falls below this
        double bestDistance = travelRoute.getDistance();
        System.out.println("Initial distance of travel route: " + bestDistance);
        TravelRoute bestRoute = travelRoute;

        for (int i = 0; i < numberOfIterations; i++) {
            double temperature = startingTemperature * Math.pow(coolingRate, i);
            if (temperature <= thresholdTemperature) {
                break;
            }

            TravelRoute currentRoute = bestRoute;
            currentRoute.swapLocations();
            double currentDistance = currentRoute.getDistance();

            if (currentDistance < bestDistance) {
                bestDistance = currentDistance;
                bestRoute = currentRoute;
            } else if (Math.exp((bestDistance - currentDistance) / temperature) < Math.random()) {
                currentRoute.revertSwap();
            }
        }

        bestRoute.getOptimalRoute(bestDistance);
        return bestDistance;
    }

}
