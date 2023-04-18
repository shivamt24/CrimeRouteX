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
}
