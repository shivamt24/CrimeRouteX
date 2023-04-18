package edu.neu.info6205.algorithms.Christofides;
import java.util.*;

public class Graph {

	int numVertices, numEdges;
	private List<Integer>[] adjacencyList;
	List<Integer> EulerCircuit = new ArrayList<Integer>();
	
	Graph(int Vertices, int Edges) {
        this.numVertices = Vertices;
        this.numEdges = Edges;
    }
	
	Graph(int Vertices) {
        this.numVertices = Vertices;
        initializeGraph();
    }
	
	void initializeGraph() {
		adjacencyList = new LinkedList[numVertices];
	    for (int i = 0; i < numVertices; i++) adjacencyList[i] = new LinkedList<Integer>();
	} 

	void addEdge(Integer u, Integer v) {
		adjacencyList[u].add(v); 
	    adjacencyList[v].add(u); 
	} 
	  
	void removeEdge(Integer u, Integer v) {
		adjacencyList[u].remove(v); 
	    adjacencyList[v].remove(u); 
	}

	void createEulerCircuit() {
	    Integer startingVertex = findStartingVertexForEulerCircuit();
	    traverseEulerCircuit(startingVertex);
	}

	Integer findStartingVertexForEulerCircuit() {
	    for (int i = 0; i < numVertices; i++) {
	        if (adjacencyList[i].size() % 2 == 1) {
	            return i;
	        }
	    }
	    return 0;
	}

	void traverseEulerCircuit(Integer currentVertex) {
	    for (int i = 0; i < adjacencyList[currentVertex].size(); i++) {
	        Integer nextVertex = adjacencyList[currentVertex].get(i);
	        if (isNextEdgeValid(currentVertex, nextVertex)) {
	            EulerCircuit.add(currentVertex);
	            EulerCircuit.add(nextVertex);
	            removeEdge(currentVertex, nextVertex);
	            traverseEulerCircuit(nextVertex);
	        }
	    }
	}

	    
	boolean isNextEdgeValid(Integer u, Integer v) {
		if (adjacencyList[u].size() == 1) return true; 
	    boolean[] isVisited1 = new boolean[this.numVertices];
	    int count1 = dfsCount(u, isVisited1); 
	    removeEdge(u, v); 
	    boolean[] isVisited2 = new boolean[this.numVertices];
	    int count2 = dfsCount(u, isVisited2); 
	    addEdge(u, v); 
	    return (count1 > count2) ? false : true; 
	} 

	int dfsCount(Integer start, boolean[] isVisited) {
	    int count = 0;
	    Stack<Integer> stack = new Stack<>();
	    stack.push(start);

	    while(!stack.empty()) {
	        Integer current = stack.pop();
	        if(!isVisited[current]) {
	            isVisited[current] = true;
	            count++;
	        }
	        Iterator<Integer> itr = adjacencyList[current].iterator();
	        while(itr.hasNext()) {
	            Integer next = itr.next();
	            if(!isVisited[next]) {
	                stack.push(next);
	            }
	        }
	    }
	    return count;
	}
	
	ArrayList<Integer> clearRepeatedCities(List<Integer> crimeLocations) {
		int[] crimeLocationArray = new int[numVertices];
		ArrayList<Integer> resultCircuit = new ArrayList<Integer>();
		for(int i=0; i<crimeLocations.size(); i++) {
			int currentLocation = crimeLocations.get(i);
			crimeLocationArray[currentLocation]++;
			if(crimeLocationArray[currentLocation] == 1) resultCircuit.add(currentLocation);
		}
		resultCircuit.add(resultCircuit.get(0));
		return resultCircuit;
	}

	Edge[] findMinimumWeightPerfectMatching(Edge[] mst,List<Location> crimeLocations){
    	int[] neighbourCounterOnMST = new int[numVertices];
    	
    	for(int i = 1 ; i < mst.length ; i++) {
    		int src = mst[i].src;
            int dest = mst[i].dest;
            neighbourCounterOnMST[src]++;
            neighbourCounterOnMST[dest]++;
    	}
    	
    	ArrayList<Edge> newEdgesForOddVertexs = new ArrayList<Edge>();
    	List<Location> oddDegreVertex = new ArrayList<Location>();
    	
    	for(int i = 0 ; i < neighbourCounterOnMST.length ; i++) {
    		if(neighbourCounterOnMST[i] % 2 == 1) {
    			oddDegreVertex.add(crimeLocations.get(i));
    		}
    	}
    	findMatchesWithNearestNeighbour(oddDegreVertex,newEdgesForOddVertexs);
    	
    	Edge[] newEdges = newEdgesForOddVertexs.toArray(new Edge[0]);
    	int length1 = mst.length-1;
    	int lenght2 = newEdges.length;
    	Edge[] result = new Edge[length1 + lenght2]; 
    	System.arraycopy(mst, 0, result, 0, length1);  
    	System.arraycopy(newEdges, 0, result, length1, lenght2);

    	int[] neighbourCounterOnMST2 = new int[numVertices];
        
        for (int i = 1; i < length1+lenght2; ++i) {
        	int src = result[i].src;
            int dest = result[i].dest;
            neighbourCounterOnMST2[src]++;
            neighbourCounterOnMST2[dest]++;
        }
        return result;
    }

	void findMatchesWithNearestNeighbour(List<Location> oddDegreVertex, ArrayList<Edge> newEdgesForOddVertexs) {
		double distance = 0.0, minDistance = Double.MAX_VALUE;
		int nextcityIndex = 0, indexToRemove = 0;
		Edge edge;
		Location loc1,loc2;
		for(int i = 0 ;  i < oddDegreVertex.size(); i = nextcityIndex) {
			loc1 = oddDegreVertex.get(i);
			oddDegreVertex.remove(i);
			for (int k = 0; k < oddDegreVertex.size(); k++) {
				loc2 = oddDegreVertex.get(k);
				distance = MiscUtil.haversineDistance(loc1.getLongitude(), loc2.getLongitude(), loc1.getLatitude(), loc2.getLatitude());
				if(distance < minDistance ) {
					minDistance = distance;
					nextcityIndex = 0;
					indexToRemove = k;
				}
			}
			loc2 = oddDegreVertex.get(indexToRemove);
			edge = new Edge(loc1.getId(), loc2.getId(), minDistance);
			newEdgesForOddVertexs.add(edge);
			minDistance = Double.MAX_VALUE;
			oddDegreVertex.remove(indexToRemove);
			if(oddDegreVertex.size()==2){
				int src = oddDegreVertex.get(0).getId();
				int dest = oddDegreVertex.get(1).getId();
				double weight = MiscUtil.haversineDistance(oddDegreVertex.get(0).getLongitude(),oddDegreVertex.get(1).getLongitude(), oddDegreVertex.get(0).getLatitude(), oddDegreVertex.get(1).getLatitude());
				edge = new Edge(src, dest, weight);
				newEdgesForOddVertexs.add(edge);
				break;
			}
		}
	}

	int minKey(double key[], Boolean inMST[]) {
		double minKey = Double.MAX_VALUE;
		int minKeyIndex = -1;
		for (int i = 0; i < numVertices; i++)
			if (!inMST[i] && key[i] < minKey) {
				minKey = key[i];
				minKeyIndex = i;
			}

		return minKeyIndex;
	}

	Edge[] buildMST(int parent[], double distanceMatrix[][]) {
		Edge[] mst = new Edge[numVertices];
		for (int i = 1; i < numVertices; i++) mst[i]=new Edge(parent[i], i, distanceMatrix[i][parent[i]]);
		return mst;
	}

	Edge[] primMST(double distanceMatrix[][]) {
		int parent[] = new int[numVertices];
		double key[] = new double[numVertices];
		Boolean inMST[] = new Boolean[numVertices];
		Arrays.fill(key, Double.MAX_VALUE);
		Arrays.fill(inMST, false);
		key[0] = 0.0;
		parent[0] = -1;
		for (int i = 0; i < numVertices - 1; i++) {
			int u = minKey(key, inMST);
			inMST[u] = true;
			for (int v = 0; v < numVertices; v++)
				if (distanceMatrix[u][v] != 0.0 && inMST[v] == false && distanceMatrix[u][v] < key[v]) {
					parent[v] = u;
					key[v] = distanceMatrix[u][v];
				}
		}
		return buildMST(parent, distanceMatrix);
	}
}
