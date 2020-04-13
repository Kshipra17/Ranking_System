package edu.neu.coe.info6205.application;

public abstract class AbstractPredictionModel {

	public abstract void calculateExpectedHomeTeamGoals(double HomeTeamAttackStrength, double awayTeamDefenceStrength, double avgHomeGoals);
	public abstract void calculateExpectedAwayTeamGoals(double AwayTeamAttackStrength, double homeTeamDefenceStrength, double avgAwayGoals);
	public abstract void predictHomeAwayGoals(double homeGoalExpectancy, double awayGoalExpectancy);
	public abstract double getExpectedHomeTeamGoals();
	public abstract double getExpectedAwayTeamGoals();
	public abstract  int getPredictedHomeGoals();
	public abstract int  getPredictedAwayGoals();
}

