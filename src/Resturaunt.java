import java.util.ArrayList;
import java.util.List;

/**
 * @(#) Resturaunt.java
 */

public class Resturaunt
{
	private static final int END_OF_GAME_COSTS = 4000;
	private static final int TABLE_COUNT = 9;
	private static final int WAITER_COUNT = 3;
	
	private String name;
	
	private String address;
	
	private String city;
	
	private Chef chef;
	
	private Barman barman;
	
	private Menu menu;
	
	private int reputationScore = 15;
	
	private Budget budget;
	
	private java.util.List<Client> clients;
	
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
	
	public void increaseReputation()
	{
		reputationScore = reputationScore + 1;
	}
	
	public void decreaseReputation()
	{
		reputationScore = reputationScore - 1;
	}
	
	public Reputation getReputation(){
		if (reputationScore>=30){
			return Reputation.HIGH;
		}
		else if (reputationScore >= 15){
			return Reputation.MEDIUM;
		}
		else{
			return Reputation.LOW;
		}
	}
	
	
	public double getFinalScore( ) throws OutOfBudgetException
	{
		budget.decreaseBudget(END_OF_GAME_COSTS);
		return budget.getBudget();
	}
	
	public void paySalaries( ) throws OutOfBudgetException
	{
		for (Waiter waiter:waiters){
			waiter.paySalary(budget);
		}
		chef.paySalary(budget);
		barman.paySalary(budget);
	}
	
	public void startDay(int day, List<Client> visitors)
	{
		this.clients = visitors;
	}
	
	
}
