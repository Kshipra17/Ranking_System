package edu.neu.coe.info6205.application;

/**
 * 
 * @author Kshipra17
 * The following class defines the abstract prediction model and recommends API 
 * to be implemented by class which inherits from this class 
 *
 */

public abstract class AbstractPredictionModel {

	public abstract void calculateExpectedHomeTeamGoals(double HomeTeamAttackStrength, double awayTeamDefenceStrength, double avgHomeGoals);
	public abstract void calculateExpectedAwayTeamGoals(double AwayTeamAttackStrength, double homeTeamDefenceStrength, double avgAwayGoals);
	public abstract void predictHomeAwayGoals(double homeGoalExpectancy, double awayGoalExpectancy);
	public abstract double getExpectedHomeTeamGoals();
	public abstract double getExpectedAwayTeamGoals();
	public abstract  int getPredictedHomeGoals();
	public abstract int  getPredictedAwayGoals();
}

