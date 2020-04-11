package edu.neu.coe.info6205.util;

public class Team {
	private String teamName;
	private int homeMatchesPlayed;
	private int awayMatchesPlayed;
	private int homeGoals;
	private int awayGoals;
	private int homeGoalsAgainst;
	private int awayGoalsAgainst;

	public Team() {
		// TODO Auto-generated constructor stub
	}

	public Team(String teamName) {
		super();
		this.teamName = teamName;
		this.homeMatchesPlayed = 0;
		this.awayMatchesPlayed = 0;
		this.homeGoals = 0;
		this.awayGoals = 0;
		this.homeGoalsAgainst = 0;
		this.awayGoalsAgainst = 0;
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

}
