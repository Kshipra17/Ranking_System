package edu.neu.coe.info6205.application;

public abstract class AbstractPredictionModel {

	public abstract double calculateExpectedHomeTeamGoals(double HomeTeamAttackStrength, double awayTeamDefenceStrength, double avgHomeGoals);
	public abstract double calculateExpectedAwayTeamGoals(double AwayTeamAttackStrength, double homeTeamDefenceStrength, double avgAwayGoals);
	public abstract void calculateWinningProbability(double homeGoalExpectancy, double awayGoalExpectancy);
	public abstract double getExpectedHomeTeamGoals();
	public abstract double getExpectedAwayTeamGoals();
}
