package edu.neu.info6205.algorithms.Christofides;

import java.io.*;
import java.util.*;

public class FileUtil {
	
	public static List<String> readFile(String fileName) {
	List<String> csvLine = new ArrayList<String>();
	
	try ( BufferedReader inLine = new BufferedReader(new FileReader(fileName));)
	{	
		String newLine = null; 
		while ((newLine = inLine.readLine()) != null) {
			csvLine.add(newLine);
		}
	} catch (IOException e) {
		System.out.println("Error occured while reading data from file: " + e);
		e.printStackTrace();
	}
	return csvLine;
	}
	
	public static void writeFile(List<String> fileContent, String fileName) {
		try ( FileWriter fw = new FileWriter(fileName); BufferedWriter out= new BufferedWriter(fw);)
		{
			for (int i = 0; i < fileContent.size(); i++) {
				out.write(fileContent.get(i));
				out.newLine();
			}
			out.flush();
		} catch (IOException e) {
			System.out.println("Error occured while writing data to file: " + e);
			e.printStackTrace();
		}
	}
}
