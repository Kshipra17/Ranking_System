package edu.neu.coe.info6205.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class Main {
	
	private static int totGoals = 0;
	private static HashMap<String, Team> teamData = new HashMap<String, Team>();

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Reader in = new FileReader(".\\EPL_Data\\Season2019-20.csv");
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
			for (CSVRecord record : records) {
//			    if((record.get("HomeTeam").equals("Liverpool"))) {
//			    	Main.totGoals += Integer.parseInt(record.get("HomeGoals"));
//			    }
//			    if((record.get("AwayTeam").equals("Liverpool")))
//			    	Main.totGoals += Integer.parseInt(record.get("AwayGoals"));
				if(!(Main.teamData.containsKey(record.get("HomeTeam"))))
					Main.teamData.put(record.get("HomeTeam"), new Team(record.get("HomeTeam")));
				if(!(Main.teamData.containsKey(record.get("AwayTeam"))))
					Main.teamData.put(record.get("AwayTeam"), new Team(record.get("AwayTeam")));
				
				Main.teamData.get(record.get("HomeTeam")).setHomeGoals(Integer.parseInt(record.get("HomeGoals")));
				Main.teamData.get(record.get("HomeTeam")).setHomeGoalsAgainst(Integer.parseInt(record.get("AwayGoals")));
				Main.teamData.get(record.get("HomeTeam")).setHomeMatchesPlayed(1);
				Main.teamData.get(record.get("AwayTeam")).setAwayGoals(Integer.parseInt(record.get("AwayGoals")));
				Main.teamData.get(record.get("AwayTeam")).setAwayGoalsAgainst(Integer.parseInt(record.get("HomeGoals")));
				Main.teamData.get(record.get("AwayTeam")).setAwayMatchesPlayed(1);
				
			}
//			System.out.println(Main.teamData.get("Liverpool").getHomeGoals() + Main.teamData.get("Liverpool").getAwayGoals());
			System.out.println(Main.teamData.get("Liverpool").getAwayMatchesPlayed());
//			System.out.println(Main.teamData.size());
			
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(".\\EPL_Data\\Season2019-20_HomeAverageGoals.csv"));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("Team", "Games Played", "Goal For", "Average Goals For", "Goals Against", "Average Goals Against"));
            
            for (Entry<String, Team> entry : Main.teamData.entrySet()) {
            	csvPrinter.printRecord(entry.getValue().getTeamName(), entry.getValue().getHomeMatchesPlayed(),
            			entry.getValue().getHomeGoals(), (entry.getValue().getHomeGoals()/Double.valueOf(entry.getValue().getHomeMatchesPlayed())),
            			entry.getValue().getHomeGoalsAgainst(), (entry.getValue().getHomeGoalsAgainst()/Double.valueOf(entry.getValue().getHomeMatchesPlayed())));
            }
            csvPrinter.flush();
            csvPrinter.close();
            
            writer = Files.newBufferedWriter(Paths.get(".\\EPL_Data\\Season2019-20_AwayAverageGoals.csv"));
            csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("Team", "Games Played", "Goal For", "Average Goals For", "Goals Against", "Average Goals Against"));
            for (Entry<String, Team> entry : Main.teamData.entrySet()) {
            	csvPrinter.printRecord(entry.getValue().getTeamName(), entry.getValue().getAwayMatchesPlayed(),
            			entry.getValue().getAwayGoals(), (entry.getValue().getAwayGoals()/Double.valueOf(entry.getValue().getAwayMatchesPlayed())),
            			entry.getValue().getAwayGoalsAgainst(), (entry.getValue().getAwayGoalsAgainst()/Double.valueOf(entry.getValue().getAwayMatchesPlayed())));
            }
            
            csvPrinter.flush();
            csvPrinter.close();
            
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
