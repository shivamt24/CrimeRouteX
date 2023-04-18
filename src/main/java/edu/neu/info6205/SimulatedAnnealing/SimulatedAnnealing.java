package edu.neu.info6205.SimulatedAnnealing;

import edu.neu.info6205.SimulatedAnnealing.utils.FileUtil;

public class SimulatedAnnealing {
    // Read from csv file
    static double[][] coordinates = FileUtil.readCoordinates("./src/main/resources/inputs/info6205.spring2023.teamproject.csv");
    private static TravelRoute travelRoute = new TravelRoute(coordinates);

    public static double simulateAnnealing(double startingTemperature, int numberOfIterations, double coolingRate) {
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

        bestRoute.getOptimalRoute();
        return bestDistance;
    }

}
