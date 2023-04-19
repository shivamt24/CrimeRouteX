package edu.neu.info6205.algorithms.SimulatedAnnealing;

public class Benchmark {
    public static void main(String args[]) {
        System.out.println("TSP Final Project");
        String filename = "./src/main/resources/inputs/info6205.spring2023.teamproject.csv";
        double[][] parameterRanges = { { 1000, 2000, 4000, 8000, 16000 }, { 10000, 20000, 40000, 80000},
                { 0.9995 } };
        double[] distances = new double[parameterRanges[0].length * parameterRanges[1].length
                * parameterRanges[2].length];
        int index = 0;
        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < parameterRanges[0].length; i++) {
            for (int j = 0; j < parameterRanges[1].length; j++) {
                for (int k = 0; k < parameterRanges[2].length; k++) {
                    double temperature = parameterRanges[0][i];
                    int iterations = (int) parameterRanges[1][j];
                    double coolingRate = parameterRanges[2][k];

                    double distance = SimulatedAnnealing.simulateAnnealing(temperature, iterations, coolingRate, filename);
                    System.out.println("Optimal Distance=" + distance);
                    distances[index++] = distance;
                    if (distance < minDistance) {
                        minDistance = distance;
                    }
                }
            }
        }

        System.out.println("Minimum distance: " + minDistance);

    }
}
