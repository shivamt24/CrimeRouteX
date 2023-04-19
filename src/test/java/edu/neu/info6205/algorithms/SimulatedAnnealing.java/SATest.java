import org.junit.Test;

import edu.neu.info6205.SimulatedAnnealing.SimulatedAnnealing;

import static org.junit.Assert.*;

public class SATest {

    @Test
    public void testSimulateAnnealing() {
        double startingTemperature = 100;
        int numberOfIterations = 1000;
        double coolingRate = 0.99;
        String filename = "./src/test/resources/inputs/test_sa_read.csv";
        double result = SimulatedAnnealing.simulateAnnealing(startingTemperature, numberOfIterations, coolingRate, filename);
        // Check that the result is not null
        assertNotNull(result);

        // Check that the result is greater than 0
        assertTrue(result == 75796.0);
    }
}
