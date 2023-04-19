package edu.neu.info6205.algorithms.Christofides;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
			List<Location> crimeLocations = new ArrayList<Location>();
			double distanceMatrix[][];
			String pathName = "./src/main/resources/inputs";
			String fileName = "/info6205.spring2023.teamproject";
			String ext = ".csv";
			HashMap<Integer, Location> map = new HashMap<>();
			int Vertices = 0;
		    int Edges = 0;
			List<String> fileData = FileUtil.readFile(pathName+fileName+ext);
			for (int i = 0; i < fileData.size(); i++) {
				if(i == 0) continue;
				String[] fields = fileData.get(i).split(",");
				String hexString = fields[0];
				String last5Chars = hexString.substring(hexString.length() - 5);
				Location locObj = new Location (Vertices, last5Chars, Double.parseDouble(fields[1]), Double.parseDouble(fields[2]));
				crimeLocations.add(locObj);
				map.put(Vertices, locObj);
				Vertices++;
			}
			Edges = (Vertices*Vertices)-Vertices;
			distanceMatrix = new double[Vertices][Vertices];
		    double distance = 0;
		    for(int i=0; i<Vertices; i++) {
			    for(int j=0; j<Vertices; j++) {
			    	if(i!=j) {
			    		distance = MiscUtil.haversineDistance(crimeLocations.get(i).getLongitude(), crimeLocations.get(j).getLongitude(), crimeLocations.get(i).getLatitude(), crimeLocations.get(j).getLatitude());
				    	distanceMatrix[i][j] = distance;
			    	}
			    }
			}
		    
		    //1. Creating Minimum Spanning Tree using Prims
			Graph g=new Graph(Vertices, Edges);
			Edge [] primsResult = g.primMST(distanceMatrix);
		    
			//2.  Finding Minimum-Weight Perfect Matching
			Edge mst[] = g.findMinimumWeightPerfectMatching(primsResult,crimeLocations);
			
			//3.
		    Graph g1 = new Graph(Vertices);
		    for(int i=1; i<mst.length; i++) {
		    	g1.addEdge(mst[i].src, mst[i].dest);
		    }
		    
		    //4. Creating Eulerian Circuit
	        g1.createEulerCircuit();
	        
	        //5. skipping repeated vertices, i.e. shortcutting to form Hamiltonian Circuit
		    List<Integer> HamiltonianCircuit = g1.clearRepeatedCities(g1.EulerCircuit);
		    
	        double totalDistance = MiscUtil.computeDistance(HamiltonianCircuit, distanceMatrix);
			String outputPathName = "./src/main/resources/outputs/";
			String outputFileName = "christofides";
			ArrayList<String> outputFileContent = new ArrayList<String>();
			outputFileContent.add("longitude,latitude,tolongitude,tolatitude,id,distance");
			for(int k=0; k<HamiltonianCircuit.size()-1; k++) {
				int locationId1 = -1, locationId2 = -1;
				if(k != HamiltonianCircuit.size()-1){
					locationId1 = HamiltonianCircuit.get(k);
					locationId2 = HamiltonianCircuit.get(k+1);
				} else if(k == HamiltonianCircuit.size()-1) {
					locationId1 = HamiltonianCircuit.get(k);
					locationId2 = HamiltonianCircuit.get(0);
				}
				Location l1 = map.get(locationId1);
				Location l2 = map.get(locationId2);
				if(k == 0) outputFileContent.add(l1.getLongitude()+","+l1.getLatitude()+","+l2.getLongitude()+","+l2.getLatitude()+","+l1.getCrimeId()+","+totalDistance);
				else outputFileContent.add(l1.getLongitude()+","+l1.getLatitude()+","+l2.getLongitude()+","+l2.getLatitude()+","+l1.getCrimeId());
			}
			FileUtil.writeFile(outputFileContent, outputPathName+outputFileName+ext);
		}
}
