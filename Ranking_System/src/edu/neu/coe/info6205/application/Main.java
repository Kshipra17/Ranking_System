package edu.neu.coe.info6205.application;


/**
 * 
 * @author NANDAN1393
 *Main class is design to demonstrates the functionality of application
 *At the end of the execution, Jtable is generated and shows predicted standings for EPL
 */
public class Main {

	RankingSystem rankingSystem = null;

	public Main(int numberOfTeams) {
		super();
	}

	public static void main(String[] args) {
		
		RankingSystem rankingSystem;
		try {
			
			rankingSystem = new RankingSystem();
			rankingSystem.createIndividualTeamData();
			rankingSystem.makeTeamStatCsvData();
			rankingSystem.makeAttackDefenseStrengthData();
			rankingSystem.predictRemainingMatches();
			rankingSystem.showPredictedRanking();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
}