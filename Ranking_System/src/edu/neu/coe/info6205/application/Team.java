package edu.neu.coe.info6205.application;

public interface Team {
	
	public double  calculateHomeAttackStrength(double avgHomeTeamGoals,double avgHomeLeague);
	public double calculateHomeDefenseStrength(double avgGoalsConcededHT,double avgHomeLeage);
	public double calculateAwayAttackStrength(double avgAwayTeamGoals,double avgAwayLeague);
	public double calculateAwayDefenseStrength(double avgGoalsConcededAT,double avgAwayLeague);
	public int getHomeMatchesPlayed();
	public void setHomeMatchesPlayed(int homeMatchesPlayed) ;
	public int getAwayMatchesPlayed();
	public void setAwayMatchesPlayed(int awayMatchesPlayed) ;
	public int getHomeGoals();
	public void setHomeGoals(int homeGoals);
	public int getAwayGoals();
	public void setAwayGoals(int awayGoals);
	public int getHomeGoalsAgainst();
	public void setHomeGoalsAgainst(int homeGoalsAgainst);
	public int getAwayGoalsAgainst();
	public void setAwayGoalsAgainst(int awayGoalsAgainst) ;
	public String getTeamName();
	public void setTeamName(String teamName);
	public void updateCurrentPoints(int points);
	public int getCurrentPoints();
	public double getAvgGoalsScoredHT();
	public void setAvgGoalsScoredHT(double avgGoalsScoredHT) ;
	public double getAvgGoalsConcededHT();
	public void setAvgGoalsConcededHT(double avgGoalsConcededHT);
	public double getAvgGoalsScoredAT();
	public void setAvgGoalsScoredAT(double avgGoalsScoredAT);
	public double getAvgGoalsConcededAT() ;
	public void setAvgGoalsConcededAT(double avgGoalsConcededAT);
	public double getHomeAttackStrength();
	public double getAwayAttackStrength();
	public double getHomeDefenseStrength();
	public double getAwayDefenseStrength();
}
