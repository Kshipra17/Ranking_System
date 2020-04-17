package edu.neu.coe.info6205.application;

/**
 * 
 * @author NANDAN1393
 *The following class demonstrates the use of factory design pattern
 *The team objects can be created once the instance of the factory is created
 */

public class TeamFactory extends AbstractTeamFactory{

	private static TeamFactory TeamFactoryInstance;
	
	/**
	 * @Fun TeamFactory
	 * @param None
	 * @Des Construct the team factory
	 * @return None
	 */
	private TeamFactory() {
		TeamFactory.TeamFactoryInstance = null;
	}
	
	/**
	 * @Fun TeamFactory
	 * @param None
	 * @Des Get the team factory instance
	 * @return TeamFactoryInstance
	 */
	public static TeamFactory getInstance() {

		if(TeamFactory.TeamFactoryInstance == null) {
			TeamFactoryInstance = new TeamFactory();
		}

		return TeamFactoryInstance;
	}
	/**
	 * @Fun getObject
	 * @param TeamName
	 * @Des Get the team record instance
	 * @return TeamRecord
	 */

	@Override
	public TeamRecord getObject(String teamName) {
		return new TeamRecord(teamName);
	}
}