package edu.neu.info6205.SimulatedAnnealing.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import edu.neu.info6205.SimulatedAnnealing.Location;

/**
 * File Util
 * 
 * @author Kaushik Gnanasekar
 *
 */
public class RouteWriter {

    String inputFilename;

    /**
     * Write file utility
     **/
    public static void write(List<Location> travel, String filename, Double bestDistance) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            StringBuilder sb = new StringBuilder();
            sb.append("longitude,latitude,tolongitude,tolatitude,id,distance" + "\n");
            Location first = travel.get(0);
            Location last = travel.get(travel.size() - 1);
            for (int i = 0; i < travel.size() - 1; i++) {
                
                Location cityA = travel.get(i);
                Location cityB = travel.get(i + 1);
                if (i == 0)
                    sb.append(cityA.getX() + "," + cityA.getY() + "," + cityB.getX() + "," + cityB.getY() + ","
                            + cityA.getCrimeId() + "," + bestDistance + "\n");
                sb.append(cityA.getX() + "," + cityA.getY() + "," + cityB.getX() + "," + cityB.getY() + ","
                        + cityA.getCrimeId() + "\n");
            }
            sb.append(last.getX() + "," + last.getY() + "," + first.getX() + "," + first.getY() + "," + last.getCrimeId());
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}