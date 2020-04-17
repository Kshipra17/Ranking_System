package edu.neu.coe.info6205.application;

import java.io.BufferedWriter;


import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
 * 
 * @author NANDAN1393
 *The RankingSystem class is designed to process historical season data and create team records
 *The teams records are formed along with there attacking/defense strength to get the goal expectancy
 *The goals scored by home and away teams is assumed to be follow poission distibution 
 */

public class RankingSystem {
	
	private SeasonStatistics ssInstance = null;
	private AbstractPredictionModel predictionModelObj = null;
	
	/**
	 * @Fun RankingSystem
	 * @param None
	 * @Des Construct the Ranking system object to predict outcome of EPL
	 * @return None
	 */
	
	public RankingSystem() throws FileNotFoundException {
		
		this.ssInstance = new SeasonStatistics(20);
		predictionModelObj = new PredictionModel();
	}
	
	/**
	 * @Fun createIndividualTeamData
	 * @param None
	 * @Des The function is design to read the previous/current/accumulative csv data and create 
	 * team record objects from it The function then creates the two CSVs in the background to keep 
	 * the performance of all teams when played at home and away
	 * @return None
	 */
	
	public void createIndividualTeamData() throws IOException
	{
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(this.ssInstance.getSDInstance());
		
		for (CSVRecord record : records) {
			if(!(this.ssInstance.getTeamData().containsKey(record.get("HomeTeam"))))
				this.ssInstance.getTeamData().put(record.get("HomeTeam"), this.ssInstance.getTeamObject(record.get("HomeTeam")));

			if(!(this.ssInstance.getTeamData().	containsKey(record.get("AwayTeam"))))
				this.ssInstance.getTeamData().put(record.get("AwayTeam"), this.ssInstance.getTeamObject(record.get("AwayTeam")));

			this.ssInstance.getTeamData().get(record.get("HomeTeam")).setHomeGoals(Integer.parseInt(record.get("HomeGoals")));
			this.ssInstance.getTeamData().get(record.get("HomeTeam")).setHomeGoalsAgainst(Integer.parseInt(record.get("AwayGoals")));
			this.ssInstance.getTeamData().get(record.get("HomeTeam")).setHomeMatchesPlayed(1);
			this.ssInstance.setTotalHomeMatchesPlayed(1);
			this.ssInstance.setTotalHomeGoalsScored(Integer.parseInt(record.get("HomeGoals")));

			this.ssInstance.getTeamData().get(record.get("AwayTeam")).setAwayGoals(Integer.parseInt(record.get("AwayGoals")));
			this.ssInstance.getTeamData().get(record.get("AwayTeam")).setAwayGoalsAgainst(Integer.parseInt(record.get("HomeGoals")));
			this.ssInstance.getTeamData().get(record.get("AwayTeam")).setAwayMatchesPlayed(1);
			this.ssInstance.setTotalAwayMatchesPlayed(1);
			this.ssInstance.setTotalAwayGoalsScored(Integer.parseInt(record.get("AwayGoals")));
			if(Integer.parseInt(record.get("AwayGoals")) < Integer.parseInt(record.get("HomeGoals"))) {
				this.ssInstance.getTeamData().get(record.get("HomeTeam")).setHwin(1);
				this.ssInstance.getTeamData().get(record.get("AwayTeam")).setAlose(1);
				this.ssInstance.getTeamData().get(record.get("HomeTeam")).setCurrentPoints(3);
				
			}
			else if(Integer.parseInt(record.get("AwayGoals")) > Integer.parseInt(record.get("HomeGoals"))) {
				this.ssInstance.getTeamData().get(record.get("AwayTeam")).setAwin(1);
				this.ssInstance.getTeamData().get(record.get("HomeTeam")).setHlose(1);
				this.ssInstance.getTeamData().get(record.get("AwayTeam")).setCurrentPoints(3);
			}
			else {
				this.ssInstance.getTeamData().get(record.get("AwayTeam")).setAdraw(1);
				this.ssInstance.getTeamData().get(record.get("HomeTeam")).setHdraw(1);
				this.ssInstance.getTeamData().get(record.get("HomeTeam")).setCurrentPoints(1);
				this.ssInstance.getTeamData().get(record.get("AwayTeam")).setCurrentPoints(1);
			}
		}

			BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.ssInstance.getHomeStatCsv()));
			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
					.withHeader("Team", "Games Played", "Goal For", "Average Goals For", "Goals Against", "Average Goals Against"));


			for (Entry<String, TeamRecord> entry : this.ssInstance.getTeamData().entrySet()) {
				entry.getValue().setAvgGoalsScoredHT(entry.getValue().getHomeGoals()/Double.valueOf(entry.getValue().getHomeMatchesPlayed()));
				entry.getValue().setAvgGoalsConcededHT(entry.getValue().getHomeGoalsAgainst()/Double.valueOf(entry.getValue().getHomeMatchesPlayed()));

				csvPrinter.printRecord(entry.getValue().getTeamName(), entry.getValue().getHomeMatchesPlayed(),
						entry.getValue().getHomeGoals(), (entry.getValue().getAvgGoalsScoredHT()),
						entry.getValue().getHomeGoalsAgainst(), (entry.getValue().getAvgGoalsConcededHT()));
			}
			csvPrinter.flush();
			csvPrinter.close();

			writer = Files.newBufferedWriter(Paths.get(this.ssInstance.getAwayStatCsv()));
			csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
					.withHeader("Team", "Games Played", "Goal For", "Average Goals For", "Goals Against", "Average Goals Against"));
			for (Entry<String, TeamRecord> entry : this.ssInstance.getTeamData().entrySet()) {
				entry.getValue().setAvgGoalsScoredAT(entry.getValue().getAwayGoals()/Double.valueOf(entry.getValue().getAwayMatchesPlayed()));
				entry.getValue().setAvgGoalsConcededAT(entry.getValue().getAwayGoalsAgainst()/Double.valueOf(entry.getValue().getAwayMatchesPlayed()));

				csvPrinter.printRecord(entry.getValue().getTeamName(), entry.getValue().getAwayMatchesPlayed(),
						entry.getValue().getAwayGoals(), (entry.getValue().getAvgGoalsScoredAT()),
						entry.getValue().getAwayGoalsAgainst(), (entry.getValue().getAvgGoalsConcededAT()));
			}

			csvPrinter.flush();
			csvPrinter.close();
	}
	
	
	/**
	 * @Fun makeTeamStatCsvData
	 * @param None
	 * @Des The function is design to write a csv with overall average performance of teams when
	 * playing away/home
	 * @return None
	 */
	
	public void makeTeamStatCsvData() throws IOException
	{
		this.ssInstance.setTotalHomeGoalsConceded();//***********dependency  TBD
		this.ssInstance.setAverageofGoalsConcededByHomeTeams();//***********dependency TBD
		this.ssInstance.setAverageHomeGoalsLeague();	

		this.ssInstance.setAverageAwayGoalsLeague();
		this.ssInstance.setAverageofGoalsConcededByAwayTeams(this.ssInstance.getAverageHomeGoalsLeague());

		BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.ssInstance.getTeamStatCsv()));

		CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
				.withHeader( "Home Goal Avg",
						"Avg Home Goals Conceded", 
						"Away Goal Avg","Avg Away Goal Conceded"));
		csvPrinter.printRecord(this.ssInstance.getAverageHomeGoalsLeague(),
				this.ssInstance.getAverageofGoalsConcededByHomeTeams(),
				this.ssInstance.getAverageAwayGoalsLeague(),
				this.ssInstance.getAverageofGoalsConcededByAwayTeams());
		
		csvPrinter.flush();
		csvPrinter.close();
	}
	
	/**
	 * @Fun makeAttackDefenseStrengthData
	 * @param None
	 * @Des The function is design to calculate attack and defense strengths of teams when playing away/home
	 * playing away/home
	 * @return None
	 */
	
	public void makeAttackDefenseStrengthData() throws IOException
	{
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.ssInstance.getAttackDefenseStrengthCsv()));
		CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
				.withHeader("Team", "Home Attack Strength", "Home Defense Strength","Away Attack Strength", 
						"Away Defense Strength"));

		//One by one write attack defensive strength to csv
		for (Entry<String, TeamRecord> entry : this.ssInstance.getTeamData().entrySet()) {

			csvPrinter.printRecord(entry.getKey(),entry.getValue().calculateHomeAttackStrength(entry.getValue().getAvgGoalsScoredHT()
					, this.ssInstance.getAverageHomeGoalsLeague()),entry.getValue().calculateHomeDefenseStrength(entry.getValue().getAvgGoalsConcededHT()
							, this.ssInstance.getAverageofGoalsConcededByHomeTeams()),entry.getValue().calculateAwayAttackStrength(entry.getValue().getAvgGoalsScoredAT()
									, this.ssInstance.getAverageAwayGoalsLeague()),entry.getValue().calculateAwayDefenseStrength(entry.getValue().getAvgGoalsConcededAT()
											, this.ssInstance.getAverageofGoalsConcededByAwayTeams()));
		}

		csvPrinter.flush();
		csvPrinter.close();
	}
	
	/**
	 * @Fun updateTeamPointsFromCSV
	 * @param None
	 * @Des The function is design to update team point from csv if provided
	 * @return None
	 */
	
	public void updateTeamPointsFromCSV() throws IOException {
		try {
			Reader in = new FileReader(this.ssInstance.getCurrentRankingCsv());
			Iterable<CSVRecord> csvrecords = CSVFormat.EXCEL.withHeader("Team","Points").parse(in);

			for (CSVRecord record : csvrecords) {
				if(this.ssInstance.getTeamData().containsKey(record.get("Team"))) 
					this.ssInstance.getTeamData().get(record.get("Team")).updateCurrentPoints(Integer.parseInt(record.get("Points")));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Fun predictRemainingMatches
	 * @param None
	 * @Des The function is design to predict the outcome of reamining matches in the season
	 * @return None
	 */
	
	public void predictRemainingMatches() throws IOException
	{
		//give remaining matches as Input
		Reader in;
		try {
			in = new FileReader(this.ssInstance.getInputMatchCsv());
			Iterable<CSVRecord> csvrecords = CSVFormat.EXCEL.withHeader().parse(in);

			for (CSVRecord record : csvrecords) {
				if(this.ssInstance.getTeamData().containsKey(record.get("HomeTeam")) && this.ssInstance.getTeamData().containsKey(record.get("AwayTeam"))) {
					predictionModelObj.calculateExpectedAwayTeamGoals(this.ssInstance.getTeamData().get(record.get("AwayTeam")).getAwayAttackStrength(),
							this.ssInstance.getTeamData().get(record.get("HomeTeam")).getHomeDefenseStrength()
							, this.ssInstance.getAverageAwayGoalsLeague());

					predictionModelObj.calculateExpectedHomeTeamGoals(this.ssInstance.getTeamData().get(record.get("HomeTeam")).getHomeAttackStrength(),
							this.ssInstance.getTeamData().get(record.get("AwayTeam")).getAwayDefenseStrength(),
							this.ssInstance.getAverageHomeGoalsLeague());

					predictionModelObj.predictHomeAwayGoals(predictionModelObj.getExpectedHomeTeamGoals(),
							predictionModelObj.getExpectedAwayTeamGoals());

					int homePredictedGoals =  predictionModelObj.getPredictedHomeGoals();
					int awayPredictedGoals =  predictionModelObj.getPredictedAwayGoals();

					if(homePredictedGoals > awayPredictedGoals) {
						this.ssInstance.getTeamData().get(record.get("HomeTeam")).updateCurrentPoints(3);
						this.ssInstance.getTeamData().get(record.get("HomeTeam")).setHwin(1);
						this.ssInstance.getTeamData().get(record.get("AwayTeam")).setAlose(1);//home team wins
					}
					else if (homePredictedGoals < awayPredictedGoals) {
						this.ssInstance.getTeamData().get(record.get("AwayTeam")).updateCurrentPoints(3);
						this.ssInstance.getTeamData().get(record.get("HomeTeam")).setHlose(1);
						this.ssInstance.getTeamData().get(record.get("AwayTeam")).setAwin(1);//Away team wins
					}
					else {
						this.ssInstance.getTeamData().get(record.get("AwayTeam")).updateCurrentPoints(1);	//Draw
						this.ssInstance.getTeamData().get(record.get("HomeTeam")).updateCurrentPoints(1);
						this.ssInstance.getTeamData().get(record.get("AwayTeam")).setAdraw(1);
						this.ssInstance.getTeamData().get(record.get("HomeTeam")).setHdraw(1);
					}

					/////////////////////updateCurrentTeamStatistics

					updateCurrentTeamStatistics(record,homePredictedGoals,awayPredictedGoals);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Fun updateCurrentTeamStatistics
	 * @param record, homePredictedGoals, awayPredictedGoals
	 * @Des The function is design to update goals scored and other statistics data as when matches are 
	 * being predicted
	 * @return None
	 */

	public void updateCurrentTeamStatistics(CSVRecord record,int homePredictedGoals, int awayPredictedGoals)
	{
		
		this.ssInstance.getTeamData().get(record.get("HomeTeam")).setHomeMatchesPlayed(1);//for individual team
		this.ssInstance.getTeamData().get(record.get("AwayTeam")).setAwayMatchesPlayed(1);//for individual team

		this.ssInstance.getTeamData().get(record.get("HomeTeam")).setHomeGoals(homePredictedGoals);
		this.ssInstance.getTeamData().get(record.get("HomeTeam")).setHomeGoalsAgainst(awayPredictedGoals);

		this.ssInstance.getTeamData().get(record.get("AwayTeam")).setAwayGoals(awayPredictedGoals);
		this.ssInstance.getTeamData().get(record.get("AwayTeam")).setAwayGoalsAgainst(homePredictedGoals);

		this.ssInstance.setTotalHomeMatchesPlayed(1);//total home teams
		this.ssInstance.setTotalAwayMatchesPlayed(1);

		this.ssInstance.setTotalHomeGoalsScored(homePredictedGoals);
		this.ssInstance.setTotalAwayGoalsScored(awayPredictedGoals);
		this.ssInstance.setTotalAwayGoalsConceded();//=totalHome Goals scored
		this.ssInstance.setTotalHomeGoalsConceded();//==awayGoalsScored

		this.ssInstance.setAverageAwayGoalsLeague();
		this.ssInstance.setAverageHomeGoalsLeague();
		this.ssInstance.setAverageofGoalsConcededByHomeTeams();
		this.ssInstance.setAverageofGoalsConcededByAwayTeams(this.ssInstance.getAverageHomeGoalsLeague());
		
		//Home attack strength
		int homeGoalsScored = this.ssInstance.getTeamData().get(record.get("HomeTeam")).getHomeGoals();
		int homeMatchesPlayed = this.ssInstance.getTeamData().get(record.get("HomeTeam")).getHomeMatchesPlayed();
		this.ssInstance.getTeamData().get(record.get("HomeTeam")).setAvgGoalsScoredHT((double)homeGoalsScored/homeMatchesPlayed);
		this.ssInstance.getTeamData().get(record.get("HomeTeam")).calculateHomeAttackStrength
		(this.ssInstance.getTeamData().get(record.get("HomeTeam")).getAvgGoalsScoredHT(), this.ssInstance.getAverageHomeGoalsLeague());

		//Home defense strength
		int homeGoalsConceded = this.ssInstance.getTeamData().get(record.get("HomeTeam")).getHomeGoalsAgainst();
		this.ssInstance.getTeamData().get(record.get("HomeTeam")).setAvgGoalsConcededHT((double)homeGoalsConceded/homeMatchesPlayed);
		this.ssInstance.getTeamData().get(record.get("HomeTeam")).calculateHomeDefenseStrength
		(this.ssInstance.getTeamData().get(record.get("HomeTeam")).getAvgGoalsConcededHT(), this.ssInstance.getAverageofGoalsConcededByHomeTeams());

		//Away Attack Strength
		int awayGoalsScored =   this.ssInstance.getTeamData().get(record.get("AwayTeam")).getAwayGoals();
		int awayMatchesPlayed = this.ssInstance.getTeamData().get(record.get("AwayTeam")).getAwayMatchesPlayed();
		this.ssInstance.getTeamData().get(record.get("AwayTeam")).setAvgGoalsScoredAT((double)awayGoalsScored/awayMatchesPlayed);
		this.ssInstance.getTeamData().get(record.get("AwayTeam")).calculateAwayAttackStrength
		(this.ssInstance.getTeamData().get(record.get("AwayTeam")).getAvgGoalsScoredAT(), this.ssInstance.getAverageAwayGoalsLeague());

		//Away Defense Strength
		this.ssInstance.getTeamData().get(record.get("AwayTeam")).setAvgGoalsConcededAT((double)homeGoalsConceded/awayMatchesPlayed);
		this.ssInstance.getTeamData().get(record.get("AwayTeam")).calculateAwayAttackStrength
		(this.ssInstance.getTeamData().get(record.get("AwayTeam")).getAvgGoalsConcededAT(), this.ssInstance.getAverageofGoalsConcededByAwayTeams());


	}
	
	/**
	 * @Fun getSsInstance
	 * @param None
	 * @Des The function get the seasons statistics instance
	 * @return ssInstance
	 */

	public SeasonStatistics getSsInstance() {
		return ssInstance;
	}
	
	/**
	 * @Fun showPredictedRanking
	 * @param None
	 * @Des The function use jTable to display the detailed EPL standings of all the teams 
	 * after/before prediction
	 * @return None
	 */
	
	public void showPredictedRanking() {
		
		List<TeamRecord> ls = new ArrayList<TeamRecord>(this.ssInstance.getTeamData().values());
        Collections.sort(ls);
        int index = 0;
        JFrame f;
        JTable j;
        String[][] data = new String[20][12];
        String[] ColumnsName = {"Ranking", "Premier League ", "GP", "PTS", "W", "D", "L", "GF", "GA", "GD", "HOME", "AWAY"};
        
        f = new JFrame();
        f.setTitle("2019-20 Premier League Standings");
        for (TeamRecord tr : ls) {
        	data[index][0] = Integer.toString(index+1);
        	data[index][1] = tr.getTeamName(); data[index][2] = Integer.toString(tr.getHomeMatchesPlayed()+tr.getAwayMatchesPlayed());
        	data[index][3] = Integer.toString(tr.getCurrentPoints()); data[index][4] = Integer.toString(tr.getHwin()+tr.getAwin());
        	data[index][5] = Integer.toString(tr.getHdraw()+tr.getAdraw()); data[index][6] = Integer.toString(tr.getHlose()+tr.getAlose());
        	data[index][7] = Integer.toString(tr.getHomeGoals()+tr.getAwayGoals()); data[index][8] = Integer.toString(tr.getHomeGoalsAgainst()+tr.getAwayGoalsAgainst());
        	data[index][9] = Integer.toString((tr.getHomeGoals()+tr.getAwayGoals()) - (tr.getHomeGoalsAgainst()+tr.getAwayGoalsAgainst()));
        	data[index][10] = Integer.toString(tr.getHwin())+"-"+Integer.toString(tr.getHdraw())+"-"+Integer.toString(tr.getHlose());
        	data[index][11] = Integer.toString(tr.getAwin())+"-"+Integer.toString(tr.getAdraw())+"-"+Integer.toString(tr.getAlose());
        	index++;
		}
        j = new JTable(data, ColumnsName	); 
        j.setBounds(30, 40, 200, 300); 
        // adding it to JScrollPane 
        JScrollPane sp = new JScrollPane(j); 
        f.add(sp); 
        // Frame Size 
        f.setSize(500, 200); 
        // Frame Visible = true 
        f.setVisible(true); 
	}

}
