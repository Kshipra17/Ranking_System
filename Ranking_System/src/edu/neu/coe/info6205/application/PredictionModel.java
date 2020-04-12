package edu.neu.coe.info6205.application;

import org.apache.commons.math3.distribution.PoissonDistribution;

public class PredictionModel extends AbstractPredictionModel{
	
	private double expectedHomeTeamGoals;
	private double expectedAwayTeamGoals;
	private double winningProbability;
	
	public PredictionModel() {
		super();
		this.expectedHomeTeamGoals = 0;
		this.expectedAwayTeamGoals = 0;
		this.winningProbability = 0;
	}

	public double calculateExpectedHomeTeamGoals(double HomeTeamAttackStrength, double awayTeamDefenceStrength, double avgHomeGoals) {
		return this.expectedHomeTeamGoals = HomeTeamAttackStrength * awayTeamDefenceStrength * avgHomeGoals;
	}
	
	public double calculateExpectedAwayTeamGoals(double AwayTeamAttackStrength, double homeTeamDefenceStrength, double avgAwayGoals) {
		return this.expectedAwayTeamGoals = AwayTeamAttackStrength * homeTeamDefenceStrength * avgAwayGoals;
	}
	
	public void calculateWinningProbability(double homeGoalExpectancy, double awayGoalExpectancy) {
			
		int[][] poissonProbMatrix = new int[6][6];
		
		PoissonDistribution poissonDistHomeObj = new PoissonDistribution(homeGoalExpectancy);
		PoissonDistribution poissonDistAwayObj = new PoissonDistribution(awayGoalExpectancy);
		int count = 0;
		for(int awayScore = 0; awayScore<poissonProbMatrix.length;awayScore++) {
			for(int homeScore = 0; homeScore<poissonProbMatrix[awayScore].length;homeScore++) {
				
				double test1 = poissonDistAwayObj.probability(awayScore);
				double test2 = poissonDistHomeObj.probability(homeScore);
				count++;
				System.out.println(test1 + "**********"+count+"*********"  + test2);
			}
		}
		
		
	}
	
}
