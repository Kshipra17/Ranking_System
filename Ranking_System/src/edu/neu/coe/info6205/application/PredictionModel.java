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

	public double getExpectedHomeTeamGoals() {
		return expectedHomeTeamGoals;
	}

	public double getExpectedAwayTeamGoals() {
		return expectedAwayTeamGoals;
	}


	public double calculateExpectedHomeTeamGoals(double HomeTeamAttackStrength, double awayTeamDefenceStrength, double avgHomeGoals) {
		return this.expectedHomeTeamGoals = HomeTeamAttackStrength * awayTeamDefenceStrength * avgHomeGoals;
	}

	public double calculateExpectedAwayTeamGoals(double AwayTeamAttackStrength, double homeTeamDefenceStrength, double avgAwayGoals) {
		return this.expectedAwayTeamGoals = AwayTeamAttackStrength * homeTeamDefenceStrength * avgAwayGoals;
	}

	public void calculateWinningProbability(double homeGoalExpectancy, double awayGoalExpectancy) {

		System.out.println("Home Goal ex"+homeGoalExpectancy+"away Goal Expec" + awayGoalExpectancy);
		
		double[][] poissonProbMatrix = new double[5][5];
		double[] sumAwayTeamProb = new double[5];
		double[] sumHomeTeamProb = new double[5];

		PoissonDistribution poissonDistHomeObj = new PoissonDistribution(homeGoalExpectancy);
		PoissonDistribution poissonDistAwayObj = new PoissonDistribution(awayGoalExpectancy);

		for(int awayScore = 0; awayScore<poissonProbMatrix.length;awayScore++) {
			for(int homeScore = 0; homeScore<poissonProbMatrix[awayScore].length;homeScore++) {
				poissonProbMatrix[awayScore][homeScore] = poissonDistAwayObj.probability(awayScore) * poissonDistHomeObj.probability(homeScore) *100;//Implied probability
				//System.out.println("index"+ awayScore+"Poisson prob away score"+poissonDistAwayObj.probability(awayScore));
				sumAwayTeamProb[awayScore] += poissonProbMatrix[awayScore][homeScore] ; //Summing columns ex arr[0][0]+arr[0][1]+arr[1][0]+arr[1][1]
				System.out.println(" index[homeScore] "+homeScore+" index[awayScore] "+ awayScore+" matrix "+poissonProbMatrix[awayScore][homeScore]);
			}
		}

		for(int awayScore = 0; awayScore<poissonProbMatrix.length;awayScore++) {
			for(int homeScore = 0; homeScore<poissonProbMatrix[awayScore].length;homeScore++) {
				sumHomeTeamProb[awayScore] += poissonProbMatrix[homeScore][awayScore] ; //Summing rows ex arr[0][0]+arr[1][0]+arr[0][1]+arr[1][1]
			}
		}

		for (int i = 0; i < sumHomeTeamProb.length; i++) {
			double d = sumHomeTeamProb[i];
			double d1 = sumAwayTeamProb[i];
			System.out.println(d + "  " + d1);
		}

		int maxHomeProbIndex = PredictionModel.getMaxValueIndex(sumHomeTeamProb);
		int maxAwayProbIndex = PredictionModel.getMaxValueIndex(sumAwayTeamProb);
		System.out.println("Home team max prob ofscoring " +maxHomeProbIndex+" goals" + " Away Team has max prob of scoring " +maxAwayProbIndex+" goals");
	 	
	}

	private static int getMaxValueIndex(double[] arr) {
		int maxVal = 0;
		for (int i = 1; i < arr.length; i++) {
			if(arr[i]>arr[maxVal])
				maxVal = i;
		}
		return maxVal;
	}
}
