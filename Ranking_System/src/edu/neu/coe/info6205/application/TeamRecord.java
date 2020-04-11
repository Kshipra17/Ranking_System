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

	public TeamRecord() {
		// TODO Auto-generated constructor stub
	}

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

	public  double  calculateHomeAttackStrength(double avgHomeTeamGoals,double avgHomeLeague)
	{
		return this.homeAttackStrength = avgHomeTeamGoals/avgHomeLeague;
	}

	public double calculateHomeDefenseStrength(double avgGoalsConcededHT,double avgHomeLeage)
	{
		return this.homeDefenseStrength = avgGoalsConcededHT/avgHomeLeage;
	}

	public double calculateAwayAttackStrength(double avgAwayTeamGoals,double avgAwayLeague)
	{
		return this.awayAttackStrength = avgAwayTeamGoals/avgAwayLeague;
	}

	public double calculateAwayDefenseStrength(double avgGoalsConcededAT,double avgAwayLeague)
	{
		return this.awayDefenseStrength = avgGoalsConcededAT/avgAwayLeague;
	}
	
}
