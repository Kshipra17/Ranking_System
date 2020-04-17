package edu.neu.coe.info6205.application;

/**
 * 
 * @author NANDAN1393
 * The following class defines the data that team object will hold during the 
 * course of execution
 *
 */

public class TeamRecord implements Comparable<TeamRecord> {

	private String teamName;
	private int currentPoints;
	private int homeMatchesPlayed;
	private int awayMatchesPlayed;
	private int homeGoals;
	private int awayGoals;
	private int homeGoalsAgainst;
	private int awayGoalsAgainst;
	private double homeAttackStrength;
	private double awayAttackStrength;
	private double homeDefenseStrength;
	private double awayDefenseStrength;
	private double avgGoalsScoredHT;
	private double avgGoalsConcededHT;
	private double avgGoalsScoredAT;
	private double avgGoalsConcededAT;
	private int hwin, hlose, hdraw, awin, alose, adraw;
	
	/**
	 * Constructor to create a single team record
	 * @param teamName
	 */

	public TeamRecord(String teamName) {
		super();
		this.hwin = 0; this.hlose = 0; this.hdraw = 0;
		this.awin = 0; this.alose = 0; this.adraw = 0;
		this.teamName = teamName;
		this.homeMatchesPlayed = 0;
		this.awayMatchesPlayed = 0;
		this.homeGoals = 0;
		this.awayGoals = 0;
		this.homeGoalsAgainst = 0;
		this.awayGoalsAgainst = 0;
		this.homeAttackStrength = 0.0f;
		this.awayAttackStrength = 0.0f;
		this.homeDefenseStrength = 0.0f;
		this.awayDefenseStrength = 0.0f;
		this.avgGoalsScoredHT = 0.0f;
		this.avgGoalsConcededHT = 0.0f;
		this.avgGoalsScoredAT = 0.0f;
		this.avgGoalsConcededAT = 0.0f;
		this.currentPoints = 0;
	}
	
	/**
	 * @fun: getAvgGoalsScoredHT
	 * @return Average goals scored by home team
	 */

	public double getAvgGoalsScoredHT() {
		return avgGoalsScoredHT;
	}
	
	/**
	 * @Fun setAvgGoalsScoredHT
	 * @param avgGoalsScoredHT
	 * @Des: The average goals scored by home team
	 * @return None
	 */

	public void setAvgGoalsScoredHT(double avgGoalsScoredHT) {
		this.avgGoalsScoredHT = avgGoalsScoredHT;
	}
	
	/**
	 * @Fun getAvgGoalsConcededHT
	 * @param None
	 * @Des Get the average goals conceded by home team
	 * @return avgGoalsConcededHT
	 */

	public double getAvgGoalsConcededHT() {
		return avgGoalsConcededHT;
	}
	
	/**
	 * @Fun setAvgGoalsConcededHT
	 * @param avgGoalsConcededHT
	 * @Des Set the average goals conceded by home team
	 * @return None
	 */

	public void setAvgGoalsConcededHT(double avgGoalsConcededHT) {
		this.avgGoalsConcededHT = avgGoalsConcededHT;
	}
	
	/**
	 * @Fun getAvgGoalsScoredAT
	 * @param None
	 * @Des Get the average goals scored by away team
	 * @return avgGoalsScoredAT
	 */

	public double getAvgGoalsScoredAT() {
		return avgGoalsScoredAT;
	}
	
	/**
	 * @Fun setAvgGoalsScoredAT
	 * @param avgGoalsScoredAT
	 * @Des Set the average goals scored by away team
	 * @return None
	 */

	public void setAvgGoalsScoredAT(double avgGoalsScoredAT) {
		this.avgGoalsScoredAT = avgGoalsScoredAT;
	}
	
	/**
	 * @Fun getAvgGoalsConcededAT
	 * @param None
	 * @Des Get the average goals conceded by away team
	 * @return avgGoalsConcededAT
	 */

	public double getAvgGoalsConcededAT() {
		return avgGoalsConcededAT;
	}
	
	/**
	 * @Fun setAvgGoalsConcededAT
	 * @param avgGoalsConcededAT
	 * @Des Set the average goals conceded by away team
	 * @return None
	 */

	public void setAvgGoalsConcededAT(double avgGoalsConcededAT) {
		this.avgGoalsConcededAT = avgGoalsConcededAT;
	}
	
	/**
	 * @Fun getHomeMatchesPlayed
	 * @param None
	 * @Des Get the number of home matched played by team
	 * @return homeMatchesPlayed
	 */

	public int getHomeMatchesPlayed() {
		return homeMatchesPlayed;
	}
	
	/**
	 * @Fun setHomeMatchesPlayed
	 * @param homeMatchesPlayed
	 * @Des Set the number of home matched played by team
	 * @return None
	 */

	public void setHomeMatchesPlayed(int homeMatchesPlayed) {
		this.homeMatchesPlayed += homeMatchesPlayed;
	}
	
	/**
	 * @Fun getAwayMatchesPlayed
	 * @param None
	 * @Des Get the number of away matched played by team
	 * @return awayMatchesPlayed
	 */

	public int getAwayMatchesPlayed() {
		return awayMatchesPlayed;
	}
	
	/**
	 * @Fun setAwayMatchesPlayed
	 * @param awayMatchesPlayed
	 * @Des Set the number of away matched played by team
	 * @return None
	 */

	public void setAwayMatchesPlayed(int awayMatchesPlayed) {
		this.awayMatchesPlayed += awayMatchesPlayed;
	}
	
	/**
	 * @Fun getHomeGoals
	 * @param None
	 * @Des Get the number of home goals scored by team
	 * @return homeGoals
	 */

	public int getHomeGoals() {
		return homeGoals;
	}
	
	/**
	 * @Fun setHomeGoals
	 * @param homeGoals
	 * @Des Set the number of home goals scored by team
	 * @return None
	 */

	public void setHomeGoals(int homeGoals) {
		this.homeGoals += homeGoals;
	}
	
	/**
	 * @Fun setAwayGoals
	 * @param None
	 * @Des Get the number of away goals scored by team
	 * @return awayGoals
	 */

	public int getAwayGoals() {
		return awayGoals;
	}
	
	/**
	 * @Fun setAwayGoals
	 * @param awayGoals
	 * @Des Set the number of away goals scored by team
	 * @return None
	 */

	public void setAwayGoals(int awayGoals) {
		this.awayGoals += awayGoals;
	}
	
	/**
	 * @Fun getHomeGoalsAgainst
	 * @param None
	 * @Des get the number of goals scored by away team against home team
	 * @return homeGoalsAgainst
	 */

	public int getHomeGoalsAgainst() {
		return homeGoalsAgainst;
	}
	
	/**
	 * @Fun setHomeGoalsAgainst
	 * @param homeGoalsAgainst
	 * @Des Set the number of goals scored by away team against home team
	 * @return None
	 */

	public void setHomeGoalsAgainst(int homeGoalsAgainst) {
		this.homeGoalsAgainst += homeGoalsAgainst;
	}
	
	/**
	 * @Fun getAwayGoalsAgainst
	 * @param None
	 * @Des Set the number of goals scored by the team aginst a team when away
	 * @return awayGoalsAgainst
	 */

	public int getAwayGoalsAgainst() {
		return awayGoalsAgainst;
	}
	
	/**
	 * @Fun setAwayGoalsAgainst
	 * @param awayGoalsAgainst
	 * @Des Set the number of goals scored by home team against away team
	 * @return None
	 */

	public void setAwayGoalsAgainst(int awayGoalsAgainst) {
		this.awayGoalsAgainst += awayGoalsAgainst;
	}
	
	/**
	 * @Fun getTeamName
	 * @param None
	 * @Des Get the team name
	 * @return teamName
	 */

	public String getTeamName() {
		return teamName;
	}
	
	/**
	 * @Fun setTeamName
	 * @param teamName
	 * @Des Set the team name
	 * @return None
	 */
	

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	/**
	 * @Fun calculateHomeAttackStrength
	 * @param avgHomeTeamGoals, avgHomeLeague
	 * @Des The function is used to calculate the home team attack strength 
	 * @return homeAttackStrength
	 */

	public  double  calculateHomeAttackStrength(double avgHomeTeamGoals,double avgHomeLeague) {
		this.homeAttackStrength = avgHomeTeamGoals/avgHomeLeague;
		return homeAttackStrength;
	}
	
	/**
	 * @Fun calculateHomeAttackStrength
	 * @param avgGoalsConcededHT, avgHomeLeage
	 * @Des The function is used to calculate the home team defense strength 
	 * @return homeDefenseStrength
	 */

	public double calculateHomeDefenseStrength(double avgGoalsConcededHT,double avgHomeLeage) {
		this.homeDefenseStrength = avgGoalsConcededHT/avgHomeLeage;
		return homeDefenseStrength;
	}
	
	/**
	 * @Fun calculateAwayAttackStrength
	 * @param avgAwayTeamGoals, avgAwayLeague
	 * @Des The function is used to calculate the attack strength of the team when it is away
	 * @return awayAttackStrength
	 */

	public double calculateAwayAttackStrength(double avgAwayTeamGoals,double avgAwayLeague) {
		this.awayAttackStrength = avgAwayTeamGoals/avgAwayLeague;
		return awayAttackStrength;
	}
	
	/**
	 * @Fun calculateAwayDefenseStrength
	 * @param avgGoalsConcededAT, avgAwayLeague
	 * @Des The function is used to calculate the defence strength of the team when it is away
	 * @return awayDefenseStrength
	 */

	public double calculateAwayDefenseStrength(double avgGoalsConcededAT,double avgAwayLeague) {
		 this.awayDefenseStrength = avgGoalsConcededAT/avgAwayLeague;
		 return awayDefenseStrength;
	}
	
	/**
	 * @Fun getHomeAttackStrength
	 * @param None
	 * @Des The function use get home attack strength of the team
	 * @return homeAttackStrength
	 */

	
	public double getHomeAttackStrength() {
		return this.homeAttackStrength;
	}
	
	/**
	 * @Fun getAwayAttackStrength
	 * @param None
	 * @Des The function use get away attack strength of the team
	 * @return awayAttackStrength
	 */


	public double getAwayAttackStrength() {
		return this.awayAttackStrength;
	}
	
	/**
	 * @Fun getHomeDefenseStrength
	 * @param None
	 * @Des The function use get home defense strength of the team
	 * @return homeDefenseStrength
	 */

	
	public double getHomeDefenseStrength() {
		return this.homeDefenseStrength;
	}
	
	/**
	 * @Fun getAwayDefenseStrength
	 * @param None
	 * @Des The function use get away defense strength of the team
	 * @return awayDefenseStrength
	 */

	
	public double getAwayDefenseStrength() {
		return this.awayDefenseStrength;
	}
	
	/**
	 * @Fun updateCurrentPoints
	 * @param points
	 * @Des The function use to update the current points of the team
	 * @return None
	 */
	

	public void updateCurrentPoints(int points) {
		 this.currentPoints += points;
	}
	
	/**
	 * @Fun getCurrentPoints
	 * @param None
	 * @Des The function use to get the current points of the team
	 * @return currentPoints
	 */
	
	public int getCurrentPoints() {
		return this.currentPoints;
	}

	/**
	 * @Fun toString
	 * @param None
	 * @Des The function is used to print all team objects
	 * @return String
	 */
	
	@Override
	public String toString() {
		return "TeamRecord [teamName=" + teamName + ", currentPoints=" + currentPoints + ", homeMatchesPlayed="
				+ homeMatchesPlayed + ", awayMatchesPlayed=" + awayMatchesPlayed + ", homeGoals=" + homeGoals
				+ ", awayGoals=" + awayGoals + ", homeAttackStrength=" + homeAttackStrength + ", awayAttackStrength="
				+ awayAttackStrength + ", homeDefenseStrength=" + homeDefenseStrength + ", awayDefenseStrength="
				+ awayDefenseStrength + "]";
	}

	/**
	 * @Fun compareTo
	 * @param TeamRecord
	 * @Des The function provides the natural orderting to team record using current points
	 * earn by team
	 * @return int
	 */

	@Override
	public int compareTo(TeamRecord o) {
		// TODO Auto-generated method stub
		if(this.getCurrentPoints() > o.getCurrentPoints())
			return -1;
		else if(this.getCurrentPoints() < o.getCurrentPoints())
			return 1;
		else
			return 0;
	}
	
	/**
	 * @Fun setCurrentPoints
	 * @param currentPoints
	 * @Des The function use to set the current points of the team
	 * @return None
	 */


	public void setCurrentPoints(int currentPoints) {
		this.currentPoints += currentPoints;
	}
	
	/**
	 * @Fun getHwin
	 * @param None
	 * @Des The function use to get Home wins by the team
	 * @return None
	 */

	public int getHwin() {
		return hwin;
	}
	
	/**
	 * @Fun setHwin
	 * @param hwin
	 * @Des The function use to set Home wins by the team
	 * @return None
	 */

	public void setHwin(int hwin) {
		this.hwin += hwin;
	}
	
	/**
	 * @Fun getHlose
	 * @param None
	 * @Des The function use to get Home lose by the team
	 * @return None
	 */

	public int getHlose() {
		return hlose;
	}
	
	/**
	 * @Fun setHlose
	 * @param hlose
	 * @Des The function use to set Home lose by the team
	 * @return None
	 */

	public void setHlose(int hlose) {
		this.hlose += hlose;
	}
	
	/**
	 * @Fun getHdraw
	 * @param None
	 * @Des The function use to get Home draw by the team
	 * @return None
	 */

	public int getHdraw() {
		return hdraw;
	}
	
	/**
	 * @Fun setHdraw
	 * @param hdraw
	 * @Des The function use to set Home draw by the team
	 * @return None
	 */

	public void setHdraw(int hdraw) {
		this.hdraw += hdraw;
	}
	
	/**
	 * @Fun getAwin
	 * @param None
	 * @Des The function use to get away wins by the team
	 * @return None
	 */

	public int getAwin() {
		return awin;
	}
	
	/**
	 * @Fun setAwin
	 * @param awin
	 * @Des The function use to set away wins by the team
	 * @return None
	 */

	public void setAwin(int awin) {
		this.awin += awin;
	}
	
	/**
	 * @Fun getAlose
	 * @param None
	 * @Des The function use to get away lose by the team
	 * @return None
	 */

	public int getAlose() {
		return alose;
	}
	
	/**
	 * @Fun setAlose
	 * @param alose
	 * @Des The function use to set away lose by the team
	 * @return None
	 */

	public void setAlose(int alose) {
		this.alose += alose;
	}
	
	/**
	 * @Fun getAdraw
	 * @param None
	 * @Des The function use to get away draw by the team
	 * @return None
	 */

	public int getAdraw() {
		return adraw;
	}
	
	/**
	 * @Fun setAdraw
	 * @param adraw
	 * @Des The function use to set away draw by the team
	 * @return None
	 */

	public void setAdraw(int adraw) {
		this.adraw += adraw;
	}	
	
}
