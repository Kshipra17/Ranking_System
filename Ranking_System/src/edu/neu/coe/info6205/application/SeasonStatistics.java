package edu.neu.coe.info6205.application;

public class SeasonStatistics {

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
	

	public SeasonStatistics(int numberOfTeams) {
		super();
		this.numberOfTeams = numberOfTeams;
		
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
		this.averageHomeGoalsLeague = (double)this.totalHomeGoalsScored/this.totalHomeMatchesPlayed;
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




	public double getAverageofGoalsConcededByHomeTeams() {
		return averageofGoalsConcededByHomeTeams;
	}

	public void setAverageofGoalsConcededByHomeTeams() {
		this.averageofGoalsConcededByHomeTeams = (double)this.totalHomeGoalsConceded/this.totalHomeMatchesPlayed;
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
		this.averageAwayGoalsLeague = (double)this.totalAwayGoalsScored/this.totalHomeMatchesPlayed;
	}


	public int getTotalAwayGoalsScored() {
		return totalAwayGoalsScored;
	}


	public void setTotalAwayGoalsScored(int totalAwayGoalsScored) {
		this.totalAwayGoalsScored += totalAwayGoalsScored;
	}


	public double getAverageofGoalsConcededByAwayTeams() {
		return averageofGoalsConcededByAwayTeams;
	}


	public void setAverageofGoalsConcededByAwayTeams(double totalAwayGoalsConceded) {
		this.averageofGoalsConcededByAwayTeams = totalAwayGoalsConceded;
	}

}
