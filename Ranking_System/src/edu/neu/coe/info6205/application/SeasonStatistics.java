package edu.neu.coe.info6205.application;

import java.io.FileNotFoundException;


import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;


/**
 * 
 * @author NANDAN1393
 *The following class is designed to hold the record if the overall season statistics
 */

public class SeasonStatistics {

	private HashMap<String, TeamRecord> teamData;
	private Reader SDInstance = null;
	AbstractTeamFactory teamFactoryObj = null;
	private String homeStatCsv = null;
	private String awayStatCsv = null;
	private String teamStatCsv = null;
	private String attackDefenseStrengthCsv = null;
	private String currentRankingCsv = null;
	private String inputMatchCsv = null;
	
	private int numberOfTeams;
	private int totalHomeMatchesPlayed;
	private double averageHomeGoalsLeague;
	private int totalHomeGoalsScored;
	private int totalHomeGoalsConceded;
	private double averageOfHomeGoalsAverage ;
	private double averageofGoalsConcededByHomeTeams;
	
	private int totalAwayMatchesPlayed;
	private double averageAwayGoalsLeague;
	private int totalAwayGoalsScored;
	private int totalAwayGoalsConceded;
	private double averageofGoalsConcededByAwayTeams;
	
	
	/**
	 * @Fun SeasonStatistics
	 * @param numberOfTeams
	 * @Des Construct the seasons statistics instance with number of teams as a input
	 * @return None
	 */

	public SeasonStatistics(int numberOfTeams) throws FileNotFoundException {
		super();
		this.SDInstance = new FileReader(".\\EPL_Data\\Season2019-20.csv");
		this.teamData = new HashMap<String, TeamRecord>();
		teamFactoryObj = TeamFactory.getInstance();
		this.numberOfTeams = numberOfTeams;
		this.homeStatCsv = ".\\EPL_Data\\Season2019-20_HomeAverageGoals.csv";
		this.awayStatCsv = ".\\EPL_Data\\Season2019-20_AwayAverageGoals.csv";
		this.teamStatCsv = ".\\EPL_Data\\Season2019-20_TeamStats.csv";
		this.attackDefenseStrengthCsv = ".\\EPL_Data\\Season2019-20_AttackDefenseStrength.csv";
		this.currentRankingCsv = ".\\EPL_Data\\Current_Ranking_EPL.csv";
		this.inputMatchCsv = ".\\EPL_Data\\InputMatches2019-20.csv";
		
		this.totalHomeGoalsConceded= 0;
		this.totalHomeMatchesPlayed = 0;
		this.totalHomeGoalsScored = 0;
		this.averageofGoalsConcededByHomeTeams = 0.0f;
		this.averageHomeGoalsLeague = 0.0f;
		
		this.totalAwayGoalsConceded = 0;
		this.totalAwayMatchesPlayed = 0 ;
		this.averageAwayGoalsLeague = 0;
		this.totalAwayGoalsScored = 0;
		this.averageofGoalsConcededByAwayTeams = 0.0f;
	}
	
	/**
	 * @Fun getTotalHomeGoalsConceded
	 * @param None
	 * @Des Get the total goals conceded by home teams in a season
	 * @return totalHomeGoalsConceded
	 */

	public int getTotalHomeGoalsConceded() {
		return totalHomeGoalsConceded;
	}
	
	/**
	 * @Fun setTotalHomeGoalsConceded
	 * @param None
	 * @Des Set the total goals conceded by home teams in a season
	 * @return None
	 */

	public void setTotalHomeGoalsConceded() {
		this.totalHomeGoalsConceded = this.totalAwayGoalsScored;
	}

	/**
	 * @Fun getTotalAwayGoalsConceded
	 * @param None
	 * @Des Get the total goals conceded by away teams in a season
	 * @return totalAwayGoalsConceded
	 */

	public int getTotalAwayGoalsConceded() {
		return totalAwayGoalsConceded;
	}
	
	/**
	 * @Fun setTotalAwayGoalsConceded
	 * @param None
	 * @Des Set the total goals conceded by away teams in a season
	 * @return None
	 */


	public void setTotalAwayGoalsConceded() {
		this.totalAwayGoalsConceded = this.totalHomeGoalsScored;
	}
	
	/**
	 * @Fun getTotalHomeMatchesPlayed
	 * @param None
	 * @Des Get the total number of home matches played
	 * @return totalHomeMatchesPlayed
	 */


	public int getTotalHomeMatchesPlayed() {
		return totalHomeMatchesPlayed;
	}
	
	/**
	 * @Fun setTotalHomeMatchesPlayed
	 * @param totalHomeMatchesPlayed
	 * @Des Set the total number of home matches played
	 * @return None
	 */


	public void setTotalHomeMatchesPlayed(int totalHomeMatchesPlayed) {
		this.totalHomeMatchesPlayed += totalHomeMatchesPlayed;
	}
	
	/**
	 * @Fun getAverageHomeGoalsLeague
	 * @param None
	 * @Des Get the average home goals in a league
	 * @return averageHomeGoalsLeague
	 */


	public double getAverageHomeGoalsLeague() {
		return averageHomeGoalsLeague;
	}
	
	/**
	 * @Fun setAverageHomeGoalsLeague
	 * @param None
	 * @Des Set the average home goals in a league
	 * @return None
	 */


	public void setAverageHomeGoalsLeague() {
		this.averageHomeGoalsLeague = (double)this.totalHomeGoalsScored/this.totalHomeMatchesPlayed;
	}
	
	/**
	 * @Fun getTotalHomeGoalsScored
	 * @param None
	 * @Des Get the total home goals scored in the season
	 * @return totalHomeGoalsScored
	 */


	public int getTotalHomeGoalsScored() {
		return totalHomeGoalsScored;
	}
	
	/**
	 * @Fun setTotalHomeGoalsScored
	 * @param totalHomeGoalsScored
	 * @Des Set the total home goals scored in the season
	 * @return None
	 */


	public void setTotalHomeGoalsScored(int totalHomeGoalsScored) {
		this.totalHomeGoalsScored += totalHomeGoalsScored;
	}

	/**
	 * @Fun getAverageofGoalsConcededByHomeTeams
	 * @param None
	 * @Des Get the average goals conceded by home teams
	 * @return averageofGoalsConcededByHomeTeams
	 */


	public double getAverageofGoalsConcededByHomeTeams() {
		return averageofGoalsConcededByHomeTeams;
	}
	
	/**
	 * @Fun setAverageofGoalsConcededByHomeTeams
	 * @param None
	 * @Des Set the average goals conceded by home teams
	 * @return None
	 */

	public void setAverageofGoalsConcededByHomeTeams() {
		this.averageofGoalsConcededByHomeTeams = (double)this.totalHomeGoalsConceded/this.totalHomeMatchesPlayed;
	}
	
	/**
	 * @Fun getTotalAwayMatchesPlayed
	 * @param None
	 * @Des Get the total number of away matches played
	 * @return totalAwayMatchesPlayed
	 */
		
	public int getTotalAwayMatchesPlayed() {
		return totalAwayMatchesPlayed;
	}

	/**
	 * @Fun setTotalAwayMatchesPlayed
	 * @param totalAwaysMatchesPlayed
	 * @Des Set the total number of away matches played
	 * @return None
	 */

	public void setTotalAwayMatchesPlayed(int totalAwaysMatchesPlayed) {
		this.totalAwayMatchesPlayed += totalAwaysMatchesPlayed;
	}
	
	/**
	 * @Fun getAverageAwayGoalsLeague
	 * @param None
	 * @Des Get the average away goals in the league
	 * @return averageAwayGoalsLeague
	 */


	public double getAverageAwayGoalsLeague() {
		return averageAwayGoalsLeague;
	}
	
	/**
	 * @Fun setAverageAwayGoalsLeague
	 * @param None
	 * @Des Set the average away goals in the league
	 * @return None
	 */


	public void setAverageAwayGoalsLeague() {
		this.averageAwayGoalsLeague = (double)this.totalAwayGoalsScored/this.totalHomeMatchesPlayed;
		
	}
	
	/**
	 * @Fun getTotalAwayGoalsScored
	 * @param None
	 * @Des Get the total number of away goals scored
	 * @return totalAwayGoalsScored
	 */



	public int getTotalAwayGoalsScored() {
		return totalAwayGoalsScored;
	}
	
	/**
	 * @Fun setTotalAwayGoalsScored
	 * @param totalAwayGoalsScored
	 * @Des Set the total number of away goals scored
	 * @return None
	 */

	public void setTotalAwayGoalsScored(int totalAwayGoalsScored) {
		this.totalAwayGoalsScored += totalAwayGoalsScored;
	}
	
	/**
	 * @Fun getAverageofGoalsConcededByAwayTeams
	 * @param None
	 * @Des Get the average goals conceded by away teams
	 * @return averageofGoalsConcededByAwayTeams
	 */


	public double getAverageofGoalsConcededByAwayTeams() {
		return averageofGoalsConcededByAwayTeams;
	}
	
	/**
	 * @Fun setAverageofGoalsConcededByAwayTeams
	 * @param totalAwayGoalsConceded
	 * @Des Set the average goals conceded by away teams
	 * @return None
	 */


	public void setAverageofGoalsConcededByAwayTeams(double totalAwayGoalsConceded) {
		this.averageofGoalsConcededByAwayTeams = totalAwayGoalsConceded;
	}
	
	
	/**
	 * @Fun getSDInstance
	 * @param None
	 * @Des Get the season data instance
	 * @return SDInstance
	 */

	public Reader getSDInstance() {
		return SDInstance;
	}
	
	/**
	 * @Fun setSDInstance
	 * @param sDInstance
	 * @Des Set the season data instance
	 * @return None
	 */

	public void setSDInstance(Reader sDInstance) {
		SDInstance = sDInstance;
	}
	
	/**
	 * @Fun getTeamObject
	 * @param TeamRecord
	 * @Des get the new team record object from team factory
	 * @return TeamRecord
	 */
	
	public TeamRecord getTeamObject(String teamName) {
		return this.teamFactoryObj.getObject(teamName);
	}
	
	/**
	 * @Fun getTeamData
	 * @param HashMap<String, TeamRecord>
	 * @Des get the team record objects for all the teams
	 * @return teamData
	 */

	public HashMap<String, TeamRecord> getTeamData() {
		return teamData;
	}
	
	/**
	 * @Fun getHomeStatCsv
	 * @param None
	 * @Des get the csv file to read season stat data for home teams
	 * @return homeStatCsv
	 */

	public String getHomeStatCsv() {
		return homeStatCsv;
	}
	
	/**
	 * @Fun getAwayStatCsv
	 * @param None
	 * @Des get the csv file to read season stat data for away team
	 * @return awayStatCsv
	 */

	public String getAwayStatCsv() {
		return awayStatCsv;
	}
	
	/**
	 * @Fun getTeamStatCsv
	 * @param None
	 * @Des get the csv file to write season stat data
	 * @return teamStatCsv
	 */

	public String getTeamStatCsv() {
		return teamStatCsv;
	}
	
	/**
	 * @Fun getAttackDefenseStrengthCsv
	 * @param None
	 * @Des get the csv file to write attack and defence strenth
	 * @return attackDefenseStrengthCsv
	 */

	public String getAttackDefenseStrengthCsv() {
		return attackDefenseStrengthCsv;
	}
	
	/**
	 * @Fun getCurrentRankingCsv
	 * @param None
	 * @Des get the current ranking CSV
	 * @return currentRankingCsv
	 */

	public String getCurrentRankingCsv() {
		return currentRankingCsv;
	}
	
	/**
	 * @Fun getInputMatchCsv
	 * @param None
	 * @Des get the input matches for the prediction
	 * @return inputMatchCsv
	 */

	public String getInputMatchCsv() {
		return inputMatchCsv;
	}

}
