package edu.neu.coe.info6205.application;

public class SeasonStatistics {

	private int numberOfTeams;
	
	private int totalHomeMatchesPlayed;
	private double averageHomeGoalsLeague;
	private int totalHomeGoalsScored;
	private int totalHomeGoalsConceded;
	private double averageOfHomeGoalsAverage ;
	private double averageofGoalsConcededByHomeTeams;
	private double averageOfGoalsConcededByHomeTeamAverage;
	private double averageHomeGamesPlayed;
	
	private int totalAwayMatchesPlayed;
	private double averageAwayGoalsLeague;
	private int totalAwayGoalsScored;
	private int totalAwayGoalsConceded;
	private double averageOfAwayGoalsAverage ;
	private double averageofGoalsConcededByAwayTeams;
	private double averageOfGoalsConcededByAwayTeamAverage;
	private double averageAwayGamesPlayed;
	

	public SeasonStatistics(int numberOfTeams) {
		super();
		this.numberOfTeams = numberOfTeams;
		
		this.averageHomeGamesPlayed = 0;
		this.totalHomeGoalsConceded= 0;
		this.totalHomeMatchesPlayed = 0;
		this.totalHomeGoalsScored = 0;
		this.averageOfHomeGoalsAverage = 0;
		this.averageofGoalsConcededByHomeTeams = 0;
		this.averageOfGoalsConcededByHomeTeamAverage = 0;
		this.averageHomeGoalsLeague = 0;
		
		this.averageAwayGamesPlayed = 0;
		this.totalAwayGoalsConceded = 0;
		this.totalAwayMatchesPlayed = 0 ;
		this.averageAwayGoalsLeague = 0;
		this.totalAwayGoalsScored = 0;
		this.averageOfAwayGoalsAverage = 0 ;
		this.averageofGoalsConcededByAwayTeams = 0;
		this.averageOfGoalsConcededByAwayTeamAverage = 0;
	}


	public double getAverageHomeGamesPlayed() {
		return averageHomeGamesPlayed;
	}


	public void setAverageHomeGamesPlayed() {
		this.averageHomeGamesPlayed = this.totalHomeMatchesPlayed/this.numberOfTeams;
	}


	public double getAverageAwayGamesPlayed() {
		return averageAwayGamesPlayed;
	}


	public void setAverageAwayGamesPlayed() {
		this.averageAwayGamesPlayed = this.totalAwayMatchesPlayed/this.numberOfTeams;
	}


	public int getTotalHomeGoalsConceded() {
		return totalHomeGoalsConceded;
	}

	public void setTotalHomeGoalsConceded() {
		this.totalHomeGoalsConceded = this.totalAwayGoalsScored;
	}


	public int getTotalAwayGoalsConceded() {
		return totalAwayGoalsConceded;
	}


	public void setTotalAwayGoalsConceded() {
		this.totalAwayGoalsConceded = this.totalHomeGoalsScored;
	}


	public int getTotalHomeMatchesPlayed() {
		return totalHomeMatchesPlayed;
	}


	public void setTotalHomeMatchesPlayed(int totalHomeMatchesPlayed) {
		this.totalHomeMatchesPlayed += totalHomeMatchesPlayed;
	}


	public double getAverageHomeGoalsLeague() {
		return averageHomeGoalsLeague;
	}


	public void setAverageHomeGoalsLeague() {
		this.averageHomeGoalsLeague = this.totalHomeGoalsScored/this.numberOfTeams;
	}


	public int getTotalHomeGoalsScored() {
		return totalHomeGoalsScored;
	}


	public void setTotalHomeGoalsScored(int totalHomeGoalsScored) {
		this.totalHomeGoalsScored += totalHomeGoalsScored;
	}


	public double getAverageOfHomeGoalsAverage() {
		return averageOfHomeGoalsAverage;
	}


	public void setAverageOfHomeGoalsAverage(double sumOfIndividualTeamAvg) {
		this.averageOfHomeGoalsAverage = sumOfIndividualTeamAvg/this.numberOfTeams;
	}


	public double getAverageofGoalsConcededByHomeTeams() {
		return averageofGoalsConcededByHomeTeams;
	}

	public void setAverageofGoalsConcededByHomeTeams() {
		this.averageofGoalsConcededByHomeTeams = this.totalHomeGoalsConceded/this.numberOfTeams;
	}
		
	public double getAverageOfGoalsConcededByHomeTeamAverage() {
		return averageOfGoalsConcededByHomeTeamAverage;
	}


	public void setAverageOfGoalsConcededByHomeTeamAverage(double sumOfIndividualAvg) {
		this.averageOfGoalsConcededByHomeTeamAverage = sumOfIndividualAvg/this.numberOfTeams;
	}


	public int getTotalAwayMatchesPlayed() {
		return totalAwayMatchesPlayed;
	}


	public void setTotalAwayMatchesPlayed(int totalAwaysMatchesPlayed) {
		this.totalAwayMatchesPlayed += totalAwaysMatchesPlayed;
	}


	public double getAverageAwayGoalsLeague() {
		return averageAwayGoalsLeague;
	}


	public void setAverageAwayGoalsLeague() {
		this.averageAwayGoalsLeague = this.totalAwayGoalsScored/this.numberOfTeams;
	}


	public int getTotalAwayGoalsScored() {
		return totalAwayGoalsScored;
	}


	public void setTotalAwayGoalsScored(int totalAwayGoalsScored) {
		this.totalAwayGoalsScored += totalAwayGoalsScored;
	}


	public double getAverageOfAwayGoalsAverage() {
		return averageOfAwayGoalsAverage;
	}


	public void setAverageOfAwayGoalsAverage(double sumOfIndividualAvg) {
		this.averageOfAwayGoalsAverage = sumOfIndividualAvg/this.numberOfTeams;
	}


	public double getAverageofGoalsConcededByAwayTeams() {
		return averageofGoalsConcededByAwayTeams;
	}


	public void setAverageofGoalsConcededByAwayTeams(double totalAwayGoalsConceded) {
		this.averageofGoalsConcededByAwayTeams = totalAwayGoalsConceded;
	}


	public double getAverageOfGoalsConcededByAwayTeamAverage() {
		return averageOfGoalsConcededByAwayTeamAverage;
	}	


	public void setAverageOfGoalsConcededByAwayTeamAverage(double avgGoalConcededAwatTeam) {
		this.averageOfGoalsConcededByAwayTeamAverage = avgGoalConcededAwatTeam;
	}

}
