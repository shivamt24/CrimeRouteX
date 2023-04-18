package edu.neu.info6205.SimulatedAnnealing.utils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.neu.info6205.SimulatedAnnealing.Location;

/**
 * File Util
 * 
 * @author Kaushik Gnanasekar
 *
 */
public class FileUtil {

    String inputFilename;

    /**
     * Read file utility
     **/
    public static double[][] readCoordinates(String inputFilename) {
        System.out.println("Reading from file: " + inputFilename);
        String thisLine = null;
        List<double[]> coordinatesList = new ArrayList<double[]>();
        try (
            FileReader fr = new FileReader(inputFilename);
            BufferedReader in = new BufferedReader(fr);   )  {
            System.out.println("BufferedReader: '" + inputFilename + "");
            Boolean isHeader = true;
            while ((thisLine = in.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] parts = thisLine.split(",");
                double[] coordinates = new double[2];
                coordinates[0] = Double.parseDouble(parts[1].trim());
                coordinates[1] = Double.parseDouble(parts[2].trim());
                coordinatesList.add(coordinates);
            }
            in.close();
            
        } catch(Exception e){
            e.printStackTrace();
        }
        int numberOfCities = coordinatesList.size();
        double[][] coordinates = new double[numberOfCities][2];
        for (int i = 0; i < numberOfCities; i++) {
            coordinates[i] = coordinatesList.get(i);
        }
        return coordinates;
    }
    

    /**
     * Write file utility
     **/
    public static void writeToFile(List<Location> travel, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < travel.size() - 1; i++) {
                Location cityA = travel.get(i);
                Location cityB = travel.get(i + 1);
                sb.append(cityA.getX() + "," + cityA.getY() + "," + cityB.getX() + "," + cityB.getY() + "\n");
            }
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}