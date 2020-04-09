package edu.neu.coe.info6205.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	/**
	 * read from a CSV file
	 * @param fileName text file name
	 * @return data list of strings from the file
	 */
	public static List<String> readTextFile( String fileName) {
		List<String> data = new ArrayList<String>();
		
		String thisLine = null;
		try (FileReader fr = new FileReader(fileName);
				BufferedReader br = new BufferedReader(fr);)
		{
			while((thisLine = br.readLine())!= null) {
				if (thisLine.isEmpty() || thisLine.trim().equals("") || thisLine.trim().equals("\n")) {
				 System.out.println("Empty line encountered, skipping");
				        continue;
				    }
				data.add(thisLine);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Reading from file "+fileName+" complete");
		return data;
	}
}
