package edu.neu.coe.info6205.application;

/**
 * 
 * @author NANDAN1393
 * The following abstract class need to be implemented by Team record class
 * to create the team objects
 *
 */

public abstract class  AbstractTeamFactory {
	public abstract TeamRecord getObject(String teamName);
}
