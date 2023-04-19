package edu.neu.info6205.algorithms.Christofides;

import java.util.List;

public class MiscUtil {
	public static double haversineDistance(double lon1, double lon2, double lat1, double lat2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
 
        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
 
        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                   Math.pow(Math.sin(dLon / 2), 2) *
                   Math.cos(lat1) *
                   Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c * 1000; // convert to meters
	}
	
	public static double computeDistance(List<Integer> resultCircuit, double distanceMatrix[][]) {
		double sum = 0;
		for(int i=0; i<resultCircuit.size()-1;i++) sum += distanceMatrix[resultCircuit.get(i)][resultCircuit.get(i+1)];
		return sum;
	}
}
