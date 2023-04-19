package edu.neu.info6205.algorithms.SimulatedAnnealing;

public class Driver {
    public static void main(String args[]) {
        String filename = "./src/main/resources/inputs/info6205.spring2023.teamproject.csv";
        System.out.println(
                "Optimized distance=" + SimulatedAnnealing.simulateAnnealing(1000, 10000, 0.9995, filename));

    }
}
