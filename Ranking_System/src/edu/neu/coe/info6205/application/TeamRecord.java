package edu.neu.coe.info6205.application;

public class TeamRecord implements Team{

	private String teamName;
	private int homeMatchesPlayed;
	private int awayMatchesPlayed;
	private int homeGoals;
	private int awayGoals;
	private int homeGoalsAgainst;
	private int awayGoalsAgainst;
	private double homeAttackStrength;
	private double awayAttackStrength;
	private double homeDefenseStrength;
	private double awayDefenseStrength;
	private double avgGoalsScoredHT;
	private double avgGoalsConcededHT;
	private double avgGoalsScoredAT;
	private double avgGoalsConcededAT;
	

	public TeamRecord(String teamName) {
		super();
		this.teamName = teamName;
		this.homeMatchesPlayed = 0;
		this.awayMatchesPlayed = 0;
		this.homeGoals = 0;
		this.awayGoals = 0;
		this.homeGoalsAgainst = 0;
		this.awayGoalsAgainst = 0;
		this.homeAttackStrength = 0.0f;
		this.awayAttackStrength = 0.0f;
		this.homeDefenseStrength = 0.0f;
		this.awayDefenseStrength = 0.0f;
		this.avgGoalsScoredHT = 0.0f;
		this.avgGoalsConcededHT = 0.0f;
		this.avgGoalsScoredAT = 0.0f;
		this.avgGoalsConcededAT = 0.0f;
	}

	public double getAvgGoalsScoredHT() {
		return avgGoalsScoredHT;
	}

	public void setAvgGoalsScoredHT(double avgGoalsScoredHT) {
		this.avgGoalsScoredHT = avgGoalsScoredHT;
	}

	public double getAvgGoalsConcededHT() {
		return avgGoalsConcededHT;
	}

	public void setAvgGoalsConcededHT(double avgGoalsConcededHT) {
		this.avgGoalsConcededHT = avgGoalsConcededHT;
	}

	public double getAvgGoalsScoredAT() {
		return avgGoalsScoredAT;
	}

	public void setAvgGoalsScoredAT(double avgGoalsScoredAT) {
		this.avgGoalsScoredAT = avgGoalsScoredAT;
	}

	public double getAvgGoalsConcededAT() {
		return avgGoalsConcededAT;
	}

	public void setAvgGoalsConcededAT(double avgGoalsConcededAT) {
		this.avgGoalsConcededAT = avgGoalsConcededAT;
	}

	public int getHomeMatchesPlayed() {
		return homeMatchesPlayed;
	}

	public void setHomeMatchesPlayed(int homeMatchesPlayed) {
		this.homeMatchesPlayed += homeMatchesPlayed;
	}

	public int getAwayMatchesPlayed() {
		return awayMatchesPlayed;
	}

	public void setAwayMatchesPlayed(int awayMatchesPlayed) {
		this.awayMatchesPlayed += awayMatchesPlayed;
	}

	public int getHomeGoals() {
		return homeGoals;
	}

	public void setHomeGoals(int homeGoals) {
		this.homeGoals = this.homeGoals + homeGoals;
	}

	public int getAwayGoals() {
		return awayGoals;
	}

	public void setAwayGoals(int awayGoals) {
		this.awayGoals += awayGoals;
	}

	public int getHomeGoalsAgainst() {
		return homeGoalsAgainst;
	}

	public void setHomeGoalsAgainst(int homeGoalsAgainst) {
		this.homeGoalsAgainst += homeGoalsAgainst;
	}

	public int getAwayGoalsAgainst() {
		return awayGoalsAgainst;
	}

	public void setAwayGoalsAgainst(int awayGoalsAgainst) {
		this.awayGoalsAgainst += awayGoalsAgainst;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public  double  calculateHomeAttackStrength(double avgHomeTeamGoals,double avgHomeLeague) {
		this.homeAttackStrength = avgHomeTeamGoals/avgHomeLeague;
		return homeAttackStrength;
	}

	public double calculateHomeDefenseStrength(double avgGoalsConcededHT,double avgHomeLeage) {
		this.homeDefenseStrength = avgGoalsConcededHT/avgHomeLeage;
		return homeDefenseStrength;
	}

	public double calculateAwayAttackStrength(double avgAwayTeamGoals,double avgAwayLeague) {
		this.awayAttackStrength = avgAwayTeamGoals/avgAwayLeague;
		return awayAttackStrength;
	}

	public double calculateAwayDefenseStrength(double avgGoalsConcededAT,double avgAwayLeague) {
		 this.awayDefenseStrength = avgGoalsConcededAT/avgAwayLeague;
		 return awayDefenseStrength;
	}

	
	public double getHomeAttackStrength() {
		return this.homeAttackStrength;
	}


	public double getAwayAttackStrength() {
		return this.awayAttackStrength;
	}

	
	public double getHomeDefenseStrength() {
		return this.homeDefenseStrength;
	}

	
	public double getAwayDefenseStrength() {
		return this.awayDefenseStrength;
	}

	
	@Override
	public int updateCurrentPoints(int points) {
		return 0;
	}
	
	
}
