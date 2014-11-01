import java.util.ArrayList;
import java.util.List;

/**
 * @(#) Resturaunt.java
 */

public class Resturaunt
{
	private static final int TABLE_COUNT = 9;
	private static final int WAITER_COUNT = 3;
	
	private String name;
	
	private String address;
	
	private String city;
	
	private Chef chef;
	
	private Barman barman;
	
	private Menu menu;
	
	private double budget = 10000.0;
	
	private int reputationScore = 15;
	
	private java.util.List<Client> clients;
	
	private Reputation reputation = Reputation.MEDIUM;
	
	private java.util.List<Table> tables;
	
	private java.util.List<Waiter> waiters;
	
	public Resturaunt(){
		tables = new ArrayList<Table>();
		waiters = new ArrayList<Waiter>();
		for (int id = 0; id<TABLE_COUNT; id++){
			tables.add(new Table(id));
		}
		for (int id = 0; id<WAITER_COUNT; id++){
			waiters.add(new Waiter(id));
		}
	}
	
	public void paySupplier( )
	{
		
	}
	
	public void increaseReputation( )
	{
		
	}
	
	public void decreaseReputation( )
	{
		
	}
	
	public double getFinalScore( )
	{
		return budget;
	}
	
	public void paySalaries( )
	{
		
	}
	
	public void startDay(int day, List<Client> visitors)
	{
		
	}
	
	
}
