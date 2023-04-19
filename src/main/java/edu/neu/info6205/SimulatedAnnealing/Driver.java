package edu.neu.info6205.SimulatedAnnealing;

public class Driver {
    public static void main(String args[]) {
        System.out.println(
                "Optimized distance=" + SimulatedAnnealing.simulateAnnealing(1000, 10000, 0.9995));

    }
}
