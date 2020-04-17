package edu.neu.coe.info6205.application;

import org.apache.commons.math3.distribution.PoissonDistribution;

/**
 * 
 * @author NANDAN1393
 *The class serves a the design for preduction model 
 */

public class PredictionModel extends AbstractPredictionModel{

	private double expectedHomeTeamGoals;
	private double expectedAwayTeamGoals;
	private int predictedHomeGoals;
	private int predictedAwayGoals;
	
	/**
	 * @Fun PredictionModel
	 * @param None
	 * @Des Constructor to get the prediction model instance
	 * @return None
	 */

	public PredictionModel() {
		super();
		this.expectedHomeTeamGoals = 0;
		this.expectedAwayTeamGoals = 0;
		this.predictedHomeGoals = 0;
		this.predictedAwayGoals = 0;
	}
	
	/**
	 * @Fun getPredictedHomeGoals
	 * @param None
	 * @Des Get the predicted home goals
	 * @return predictedHomeGoals
	 */
	
	public int getPredictedHomeGoals() {
		return predictedHomeGoals;
	}
	
	/**
	 * @Fun getPredictedAwayGoals
	 * @param None
	 * @Des Get the predicted away goals
	 * @return predictedAwayGoals
	 */

	public int getPredictedAwayGoals() {
		return predictedAwayGoals;
	}
	
	/**
	 * @Fun getExpectedHomeTeamGoals
	 * @param None
	 * @Des Get expected home team goals
	 * @return expectedHomeTeamGoals
	 */

	public double getExpectedHomeTeamGoals() {
		return expectedHomeTeamGoals;
	}
	
	/**
	 * @Fun getExpectedAwayTeamGoals
	 * @param None
	 * @Des Get expected away team's goal
	 * @return expectedAwayTeamGoals
	 */
	

	public double getExpectedAwayTeamGoals() {
		return expectedAwayTeamGoals;
	}
	
	/**
	 * @Fun calculateExpectedHomeTeamGoals
	 * @param HomeTeamAttackStrength, awayTeamDefenceStrength, avgHomeGoals
	 * @Des Calculate home team's expected goals
	 * @return None
	 */

	public void calculateExpectedHomeTeamGoals(double HomeTeamAttackStrength, double awayTeamDefenceStrength, double avgHomeGoals) {
		 this.expectedHomeTeamGoals = HomeTeamAttackStrength * awayTeamDefenceStrength * avgHomeGoals;
	}
	
	/**
	 * @Fun calculateExpectedAwayTeamGoals
	 * @param AwayTeamAttackStrength, homeTeamDefenceStrength, avgAwayGoals
	 * @Des Calculate away team's expected goals
	 * @return None
	 */

	public void calculateExpectedAwayTeamGoals(double AwayTeamAttackStrength, double homeTeamDefenceStrength, double avgAwayGoals) {
		 this.expectedAwayTeamGoals = AwayTeamAttackStrength * homeTeamDefenceStrength * avgAwayGoals;
	}
	
	/**
	 * @Fun predictHomeAwayGoals
	 * @param homeGoalExpectancy, awayGoalExpectancy
	 * @Des Predict goal expectancy matrix of home/away team
	 * @return None
	 */

	public void predictHomeAwayGoals(double homeGoalExpectancy, double awayGoalExpectancy) {

//		System.out.println("Home Goal ex"+homeGoalExpectancy+"away Goal Expec" + awayGoalExpectancy);
		
		double[][] poissonProbMatrix = new double[5][5];
		double[] sumAwayTeamProb = new double[5];
		double[] sumHomeTeamProb = new double[5];

		PoissonDistribution poissonDistHomeObj = new PoissonDistribution(homeGoalExpectancy);
		PoissonDistribution poissonDistAwayObj = new PoissonDistribution(awayGoalExpectancy);

		for(int awayScore = 0; awayScore<poissonProbMatrix.length;awayScore++) {
			for(int homeScore = 0; homeScore<poissonProbMatrix[awayScore].length;homeScore++) {
				poissonProbMatrix[awayScore][homeScore] = poissonDistAwayObj.probability(awayScore) * poissonDistHomeObj.probability(homeScore) *100;//Implied probability
				sumAwayTeamProb[awayScore] += poissonProbMatrix[awayScore][homeScore] ; //Summing columns ex arr[0][0]+arr[0][1]+arr[1][0]+arr[1][1]
		//		System.out.println(" index[homeScore] "+homeScore+" index[awayScore] "+ awayScore+" matrix "+poissonProbMatrix[awayScore][homeScore]);
			}
		}

		for(int awayScore = 0; awayScore<poissonProbMatrix.length;awayScore++) {
			for(int homeScore = 0; homeScore<poissonProbMatrix[awayScore].length;homeScore++) {
				sumHomeTeamProb[awayScore] += poissonProbMatrix[homeScore][awayScore] ; //Summing rows ex arr[0][0]+arr[1][0]+arr[0][1]+arr[1][1]
			}
		}

//		for (int i = 0; i < sumHomeTeamProb.length; i++) {
//			double d = sumHomeTeamProb[i];
//			double d1 = sumAwayTeamProb[i];
//			System.out.println(d + "  " + d1);
//		}

		this.predictedHomeGoals = PredictionModel.getMaxValueIndex(sumHomeTeamProb);
		this.predictedAwayGoals = PredictionModel.getMaxValueIndex(sumAwayTeamProb);
	//	System.out.println("Home team max prob ofscoring " +predictedHomeGoals+" goals" + " Away Team has max prob of scoring " +predictedAwayGoals+" goals");
	 
		
		//If its a draw then what 1-1?????
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
