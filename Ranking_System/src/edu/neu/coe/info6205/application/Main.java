package edu.neu.coe.info6205.application;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	private static final String seasonHistoryCsv = ".\\EPL_Data\\Season2019-20.csv";
	private static final String homeStatCsv =".\\EPL_Data\\Season2019-20_HomeAverageGoals.csv";
	private static final String awayStatCsv = ".\\EPL_Data\\Season2019-20_AwayAverageGoals.csv";
	private static final String teamStatCsv = ".\\EPL_Data\\Season2019-20_TeamStats.csv";
	private static final String attackDefenseStrengthCsv = ".\\EPL_Data\\Season2019-20_AttackDefenseStrength.csv";
	private static final String inputMatchCsv = ".\\EPL_Data\\InputMatches2019-20.csv";
	private static final String currentRankingCsv = ".\\EPL_Data\\Current_Ranking_EPL.csv";
	SeasonStatistics seasonStatObj = null;
	AbstractPredictionModel predictionModelObj = null;


	public Main(int numberOfTeams) {
		super();
		predictionModelObj = new PredictionModel();
		seasonStatObj = new SeasonStatistics(numberOfTeams);
	}

	public static void main(String[] args) throws IOException {
		int totalTeams = 20;
		Main mainObj = new Main(totalTeams);
		mainObj.createIndividualTeamData();
		mainObj.makeTeamStatCsvData();
		mainObj.makeAttackDefenseStrengthData();
		mainObj.predictRemainingMatches();
}

public void predictRemainingMatches() throws IOException
{
	//give remaining matches as Input
	Reader in;
	try {
		in = new FileReader(inputMatchCsv);
		Iterable<CSVRecord> csvrecords = CSVFormat.EXCEL.withHeader().parse(in);

		for (CSVRecord record : csvrecords) {
			if(teamData.containsKey(record.get("HomeTeam")) && teamData.containsKey(record.get("AwayTeam"))) {
				predictionModelObj.calculateExpectedAwayTeamGoals(teamData.get(record.get("AwayTeam")).getAwayAttackStrength(),
						teamData.get(record.get("HomeTeam")).getHomeDefenseStrength()
						, seasonStatObj.getAverageAwayGoalsLeague());

				predictionModelObj.calculateExpectedHomeTeamGoals(teamData.get(record.get("HomeTeam")).getHomeAttackStrength(),
						teamData.get(record.get("AwayTeam")).getAwayDefenseStrength(),
						seasonStatObj.getAverageHomeGoalsLeague());

				predictionModelObj.calculateWinningProbability(predictionModelObj.getExpectedHomeTeamGoals(), predictionModelObj.getExpectedAwayTeamGoals());
			}
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
}

public void makeTeamStatCsvData() throws IOException
{

	double sumofAvgHomeGoalScored = 0;
	double sumofAvgHomeGoalConceded = 0;
	double sumofAvgAwayGoalScored = 0;

	for (Entry<String, Team> entry : Main.teamData.entrySet()) {
		sumofAvgHomeGoalScored += entry.getValue().getAvgGoalsScoredHT();
		sumofAvgHomeGoalConceded += entry.getValue().getAvgGoalsConcededHT();
		sumofAvgAwayGoalScored += entry.getValue().getAvgGoalsScoredAT();
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

	BufferedWriter writer = Files.newBufferedWriter(Paths.get(teamStatCsv));

	CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
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
}

public void makeAttackDefenseStrengthData() throws IOException
{
	BufferedWriter writer = Files.newBufferedWriter(Paths.get(attackDefenseStrengthCsv));
	CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
			.withHeader("Team", "Home Attack Strength", "Home Defense Strength","Away Attack Strength", 
					"Away Defense Strength"));

	//One by one write attack defensive strength to csv
	for (Entry<String, Team> entry : Main.teamData.entrySet()) {

		csvPrinter.printRecord(entry.getKey(),entry.getValue().calculateHomeAttackStrength(entry.getValue().getAvgGoalsScoredHT()
				, seasonStatObj.getAverageHomeGoalsLeague()),entry.getValue().calculateHomeDefenseStrength(entry.getValue().getAvgGoalsConcededHT()
						, seasonStatObj.getAverageHomeGoalsLeague()),entry.getValue().calculateAwayAttackStrength(entry.getValue().getAvgGoalsScoredAT()
								, seasonStatObj.getAverageAwayGoalsLeague()),entry.getValue().calculateAwayDefenseStrength(entry.getValue().getAvgGoalsConcededAT()
										, seasonStatObj.getAverageAwayGoalsLeague()));
	}

	csvPrinter.flush();
	csvPrinter.close();
}


public void createIndividualTeamData() throws IOException
{
	Reader in = new FileReader(seasonHistoryCsv);
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

		BufferedWriter writer = Files.newBufferedWriter(Paths.get(homeStatCsv));
		CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
				.withHeader("Team", "Games Played", "Goal For", "Average Goals For", "Goals Against", "Average Goals Against"));


		for (Entry<String, Team> entry : Main.teamData.entrySet()) {
			entry.getValue().setAvgGoalsScoredHT(entry.getValue().getHomeGoals()/Double.valueOf(entry.getValue().getHomeMatchesPlayed()));
			entry.getValue().setAvgGoalsConcededHT(entry.getValue().getHomeGoalsAgainst()/Double.valueOf(entry.getValue().getHomeMatchesPlayed()));

			csvPrinter.printRecord(entry.getValue().getTeamName(), entry.getValue().getHomeMatchesPlayed(),
					entry.getValue().getHomeGoals(), (entry.getValue().getAvgGoalsScoredHT()),
					entry.getValue().getHomeGoalsAgainst(), (entry.getValue().getAvgGoalsConcededHT()));
		}
		csvPrinter.flush();
		csvPrinter.close();

		writer = Files.newBufferedWriter(Paths.get(awayStatCsv));
		csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
				.withHeader("Team", "Games Played", "Goal For", "Average Goals For", "Goals Against", "Average Goals Against"));
		for (Entry<String, Team> entry : Main.teamData.entrySet()) {
			entry.getValue().setAvgGoalsScoredAT(entry.getValue().getAwayGoals()/Double.valueOf(entry.getValue().getAwayMatchesPlayed()));
			entry.getValue().setAvgGoalsConcededAT(entry.getValue().getAwayGoalsAgainst()/Double.valueOf(entry.getValue().getAwayMatchesPlayed()));

			csvPrinter.printRecord(entry.getValue().getTeamName(), entry.getValue().getAwayMatchesPlayed(),
					entry.getValue().getAwayGoals(), (entry.getValue().getAvgGoalsScoredAT()),
					entry.getValue().getAwayGoalsAgainst(), (entry.getValue().getAvgGoalsConcededAT()));
		}

		csvPrinter.flush();
		csvPrinter.close();

	}
}
}