package edu.neu.coe.info6205.application;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class Main {

	private static HashMap<String, Team> teamData = new HashMap<String, Team>();
	public static void main(String[] args) {

		double sumofAvgHomeGoalScored = 0;
		double sumofAvgHomeGoalConceded = 0;
		double sumofAvgAwayGoalScored = 0;


		AbstractPredictionModel predictionModelObj = new PredictionModel();
		SeasonStatistics seasonStatObj = new SeasonStatistics(20);
		
		try {
			Reader in = new FileReader(".\\EPL_Data\\Season2019-20.csv");
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
			for (CSVRecord record : records) {
				if(!(Main.teamData.containsKey(record.get("HomeTeam"))))
					Main.teamData.put(record.get("HomeTeam"), new TeamRecord(record.get("HomeTeam")));

				if(!(Main.teamData.	containsKey(record.get("AwayTeam"))))
					Main.teamData.put(record.get("AwayTeam"), new TeamRecord(record.get("AwayTeam")));

				Main.teamData.get(record.get("HomeTeam")).setHomeGoals(Integer.parseInt(record.get("HomeGoals")));
				Main.teamData.get(record.get("HomeTeam")).setHomeGoalsAgainst(Integer.parseInt(record.get("AwayGoals")));
				Main.teamData.get(record.get("HomeTeam")).setHomeMatchesPlayed(1);
				seasonStatObj.setTotalHomeMatchesPlayed(1);//redundant
				seasonStatObj.setTotalHomeGoalsScored(Integer.parseInt(record.get("HomeGoals")));

				Main.teamData.get(record.get("AwayTeam")).setAwayGoals(Integer.parseInt(record.get("AwayGoals")));
				Main.teamData.get(record.get("AwayTeam")).setAwayGoalsAgainst(Integer.parseInt(record.get("HomeGoals")));
				Main.teamData.get(record.get("AwayTeam")).setAwayMatchesPlayed(1);
				seasonStatObj.setTotalAwayMatchesPlayed(1);//redundant
				seasonStatObj.setTotalAwayGoalsScored(Integer.parseInt(record.get("AwayGoals")));
			}

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

			//////*********Calculate Avg Goals HT*****Avg Goals Against HT***************************************//////////////
			for (Entry<String, Team> entry : Main.teamData.entrySet()) {
				sumofAvgHomeGoalScored += entry.getValue().getHomeGoals()/Double.valueOf(entry.getValue().getHomeMatchesPlayed());
				sumofAvgHomeGoalConceded += entry.getValue().getHomeGoalsAgainst()/Double.valueOf(entry.getValue().getHomeMatchesPlayed());
				sumofAvgAwayGoalScored += entry.getValue().getAwayGoals()/Double.valueOf(entry.getValue().getAwayMatchesPlayed());
			}

			seasonStatObj.setAverageOfHomeGoalsAverage(sumofAvgHomeGoalScored); 
			seasonStatObj.setAverageOfGoalsConcededByHomeTeamAverage(sumofAvgHomeGoalConceded);
			seasonStatObj.setTotalHomeGoalsConceded();//***********dependency  TBD
			seasonStatObj.setAverageofGoalsConcededByHomeTeams();//***********dependency TBD
			seasonStatObj.setAverageHomeGamesPlayed();
			seasonStatObj.setAverageHomeGoalsLeague();	

			seasonStatObj.setAverageOfAwayGoalsAverage(sumofAvgAwayGoalScored);
			seasonStatObj.setAverageAwayGamesPlayed();//TBD
			seasonStatObj.setAverageAwayGoalsLeague();
			seasonStatObj.setAverageofGoalsConcededByAwayTeams(seasonStatObj.getAverageHomeGoalsLeague());
			seasonStatObj.setAverageOfGoalsConcededByAwayTeamAverage(seasonStatObj.getAverageOfHomeGoalsAverage());

			writer = Files.newBufferedWriter(Paths.get(".\\EPL_Data\\Season2019-20_TeamStats.csv"));

			csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
					.withHeader("Avg Home Matches Played", "Home Goal Avg", "Avg Home Goal Avg",
							"Avg Home Goals Conceded", "Avg Home Goals Conceded Avg","Avg Away Matches Played", 
							"Away Goal Avg", "Avg Away Goal Avg","Avg Away Goal Conceded", "Avg Away Goals Conceded Avg"));
			csvPrinter.printRecord(seasonStatObj.getAverageHomeGamesPlayed(),seasonStatObj.getAverageHomeGoalsLeague(),
					seasonStatObj.getAverageOfHomeGoalsAverage(),seasonStatObj.getAverageofGoalsConcededByHomeTeams(),
					seasonStatObj.getAverageOfGoalsConcededByHomeTeamAverage(),seasonStatObj.getAverageAwayGamesPlayed(),
					seasonStatObj.getAverageAwayGoalsLeague(),seasonStatObj.getAverageOfAwayGoalsAverage(),
					seasonStatObj.getAverageofGoalsConcededByAwayTeams(),seasonStatObj.getAverageOfGoalsConcededByAwayTeamAverage());
			csvPrinter.flush();
			csvPrinter.close();

			writer = Files.newBufferedWriter(Paths.get(".\\EPL_Data\\Season2019-20_AttackDefenseStrength.csv"));
			csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
					.withHeader("Team", "Home Attack Strength", "Home Defense Strength","Away Attack Strength", 
							"Away Defense Strength"));

			//One by one write attack defensive strength to csv
			for (Entry<String, Team> entry : Main.teamData.entrySet()) {
				csvPrinter.printRecord(entry.getKey(),entry.getValue().calculateHomeAttackStrength(entry.getValue().getHomeGoals()/Double.valueOf(entry.getValue().getHomeMatchesPlayed())
						, seasonStatObj.getAverageHomeGoalsLeague()),entry.getValue().calculateHomeDefenseStrength(entry.getValue().getHomeGoalsAgainst()/Double.valueOf(entry.getValue().getHomeMatchesPlayed())
								, seasonStatObj.getAverageHomeGoalsLeague()),entry.getValue().calculateAwayAttackStrength(entry.getValue().getAwayGoals()/Double.valueOf(entry.getValue().getAwayMatchesPlayed())
										, seasonStatObj.getAverageAwayGoalsLeague()),entry.getValue().calculateAwayDefenseStrength(entry.getValue().getAwayGoalsAgainst()/Double.valueOf(entry.getValue().getAwayMatchesPlayed())
												, seasonStatObj.getAverageAwayGoalsLeague()));
			}
			
			csvPrinter.flush();
			csvPrinter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		predictionModelObj.calculateWinningProbability(predictionModelObj.calculateExpectedHomeTeamGoals(1.5, 2.0, 1.8), predictionModelObj.calculateExpectedAwayTeamGoals(0.97, 0.85, 1.2));
	}

}
