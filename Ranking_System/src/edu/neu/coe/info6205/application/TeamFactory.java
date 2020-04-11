package edu.neu.coe.info6205.application;

public class TeamFactory extends AbstractTeamFactory{

	private static TeamFactory TeamFactoryInstance;
	private TeamFactory() {
		TeamFactory.TeamFactoryInstance = null;
	}
	public static TeamFactory getInstance() {

		if(TeamFactory.TeamFactoryInstance == null) {
			TeamFactoryInstance = new TeamFactory();
		}

		return TeamFactoryInstance;
	}

	@Override
	public Team getObject(String teamName) {
		return new TeamRecord(teamName);
	}
}