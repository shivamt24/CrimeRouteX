package edu.neu.info6205.algorithms.Christofides;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;


public class ChristofidesTest {
	@Test
    public void testReadFile() {
        String fileName = "./src/test/resources/inputs/test_file_read.csv";
        List<String> expected = new ArrayList<String>();
        expected.add("1,first,row");
        expected.add("2,second,row");
        expected.add("3,third,row");
        List<String> actual = FileUtil.readFile(fileName);
        assertEquals(expected, actual);
    }
	
	@Test
	public void testWriteFile() {
		String fileName = "./src/test/resources/outputs/test_file_write.csv";
		List<String> testContent;
		testContent = new ArrayList<>();
		testContent.add("Line 1");
		testContent.add("Line 2");
		testContent.add("Line 3");
		FileUtil.writeFile(testContent, fileName);

		List<String> actualContent = new ArrayList<>();
		try (BufferedReader inLine = new BufferedReader(new FileReader(fileName))) {
			String newLine = null;
			while ((newLine = inLine.readLine()) != null) {
				actualContent.add(newLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals(testContent, actualContent);
	}
	
	@Test
	public void testHaversineDistance() {
		double resultDistance = MiscUtil.haversineDistance(-99.436554, -98.315949, 41.507483,38.504048);
		double exptedDistance = 347328.3;
		assertEquals(exptedDistance, resultDistance, 0.1);
		
	}
	
	@Test
	  public void testPrimMST() {
		Graph graph = new Graph(4, 12);
		double[][] distanceMatrix = new double[][] {
                {0.0, 8241.202522670821, 8654.235631290367, 28343.055654344196},
                {8241.202522670821, 0.0, 13833.886151392151, 20755.760258500868},
                {8654.235631290367, 13833.886151392151, 0.0, 34488.34599199699},
                {28343.055654344196, 20755.760258500868, 34488.34599199699, 0.0}
        };
        Edge[] resultEdges = graph.primMST(distanceMatrix);
        Edge[] expectedEdges = new Edge[] {
            null,
            new Edge(0,1,8241.202522670821),
            new Edge(0,2,8654.235631290367),
            new Edge(1,3,20755.760258500868)
        };
        assertArrayEquals(expectedEdges, resultEdges);
	  }
	
	@Test
	public void testMinimumWeightPerfectMatching() {
		Graph graph = new Graph(4, 12);
		List<Location> crimeLocations = new ArrayList<Location>();
		crimeLocations.add(new Location(0, "47823", -0.009691, 51.483548));
		crimeLocations.add(new Location(1, "3dd82", -0.118888, 51.513075));
		crimeLocations.add(new Location(2, "f7d41", 0.076327, 51.540042));
		crimeLocations.add(new Location(3, "47823", -0.418139, 51.500839));
		double[][] distanceMatrix = new double[][] {
                {0.0, 8241.202522670821, 8654.235631290367, 28343.055654344196},
                {8241.202522670821, 0.0, 13833.886151392151, 20755.760258500868},
                {8654.235631290367, 13833.886151392151, 0.0, 34488.34599199699},
                {28343.055654344196, 20755.760258500868, 34488.34599199699, 0.0}
        };
        Edge[] primsResult = graph.primMST(distanceMatrix);
        Edge[] resultEdges = graph.findMinimumWeightPerfectMatching(primsResult,crimeLocations);
        Edge[] expectedEdges = new Edge[] {
                null,
                new Edge(0,1,8241.202522670821),
                new Edge(0,2,8654.235631290367),
                new Edge(2,3,34488.34599199699)
            };
        assertArrayEquals(expectedEdges, resultEdges);
	}
	
	@Test
	public void testHamiltonianCircuit() {
		Graph graph = new Graph(4, 12);
		List<Location> crimeLocations = new ArrayList<Location>();
		crimeLocations.add(new Location(0, "47823", -0.009691, 51.483548));
		crimeLocations.add(new Location(1, "3dd82", -0.118888, 51.513075));
		crimeLocations.add(new Location(2, "f7d41", 0.076327, 51.540042));
		crimeLocations.add(new Location(3, "47823", -0.418139, 51.500839));
		double[][] distanceMatrix = new double[][] {
                {0.0, 8241.202522670821, 8654.235631290367, 28343.055654344196},
                {8241.202522670821, 0.0, 13833.886151392151, 20755.760258500868},
                {8654.235631290367, 13833.886151392151, 0.0, 34488.34599199699},
                {28343.055654344196, 20755.760258500868, 34488.34599199699, 0.0}
        };
        Edge[] primsResult = graph.primMST(distanceMatrix);
        Edge[] mst = graph.findMinimumWeightPerfectMatching(primsResult,crimeLocations);
        Graph g1 = new Graph(4);
        for(int i=1; i<mst.length; i++) {
	    	g1.addEdge(mst[i].src, mst[i].dest);
	    }
        g1.createEulerCircuit();
        List<Integer> resultCircuit = g1.clearRepeatedCities(g1.EulerCircuit);
        List<Integer> exptectedCircuit = new ArrayList<Integer>();
        exptectedCircuit.add(1);
        exptectedCircuit.add(0);
        exptectedCircuit.add(2);
        exptectedCircuit.add(3);
        exptectedCircuit.add(1);
        assertEquals(exptectedCircuit, resultCircuit);
	}
}
