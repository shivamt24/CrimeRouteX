package edu.neu.info6205.algorithms.SimulatedAnnealing;

import static org.junit.Assert.*;
import org.junit.Test;

public class SimulatedAnnealingTest {
    @Test
    public void testSimulateAnnealing() {
        double startingTemperature = 1000;
        int numberOfIterations = 10000;
        double coolingRate = 0.995;
        String filename = "./src/test/resources/inputs/test_sa_read.csv";
        double result = SimulatedAnnealing.simulateAnnealing(startingTemperature, numberOfIterations, coolingRate,
                filename);

        // Check that the result is not null
        assertNotNull(result);
        assertTrue(result > 75000);
    }
}
