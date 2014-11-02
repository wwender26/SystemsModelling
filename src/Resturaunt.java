import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Collections;
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
	
	private double ownedMoneyToSupplier = 0;
	
	private java.util.List<Client> currentClients;
	
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
		budget = new Budget();
		menu = new Menu();
		chef = new Chef();
		barman = new Barman();
		
		
	}
	
	public void paySupplier() throws OutOfBudgetException
	{
		budget.decreaseBudget(ownedMoneyToSupplier);
		ownedMoneyToSupplier = 0;
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
	
	
	public void payEndGameCosts( ) throws OutOfBudgetException
	{
		budget.decreaseBudget(END_OF_GAME_COSTS);
	}
	
	public void paySalaries( ) throws OutOfBudgetException
	{
		for (Waiter waiter:waiters){
			waiter.paySalary(budget);
		}
		chef.paySalary(budget);
		barman.paySalary(budget);
	}
	
	public void startDay(int day, List<Client> visitors) throws OutOfBudgetException
	{
		this.currentClients = visitors;
		
		setWaitersForTables();
		
		seatClients();
		
		for (Client client : currentClients){
			client.orderFoodAndPay(budget, menu, day);
			ownedMoneyToSupplier = ownedMoneyToSupplier + client.getCurrentOrder().calculateTotalIngredientPrice();
			if (client.isSatisfiedWithBeverages(barman)){
				increaseReputation();
			}
			else{
				decreaseReputation();
			}
			if (client.isSatisfiedWithFood(chef)){
				increaseReputation();
			}
			else{
				decreaseReputation();
			}
			if (client.isSatisfiedWithService(findWaiter(client))){
				increaseReputation();
			}
			else{
				decreaseReputation();
			}
		}
		
		if (day%7==0){
			paySalaries();
			paySupplier();
		}
		if (day == 30){
			payEndGameCosts();
		}
		
	}
	
	private Waiter findWaiter(Client client){
		for (Table table: tables){
			if (table.getCurrentClients().contains(client)){
				return table.getCurrentWaiter();
			}
		}
		return null;
	}
	
	public void seatClients(){
		for (int i = 0; i < currentClients.size()/2; i++){
			tables.get(i).seatNewClients(currentClients.get(i*2), currentClients.get(i*2+1));
		}
	}
	
	public void setWaitersForTables(){
		// Lets sort them according to their XP.
		Collections.sort(waiters);
		for (int i = 0; i < WAITER_COUNT; i++){
			for (int j = 0; j < 3; j++){
				tables.get(i*3 + j).assignWaiter(waiters.get(i));
			}
		}
	}

	public Menu getMenu() {
		return menu;
	}

	public Chef getChef() {
		return chef;
	}

	public Barman getBarman() {
		return barman;
	}

	public java.util.List<Waiter> getWaiters() {
		return waiters;
	}

	public Budget getBudget() {
		return budget;
	}
	
	
}
