import java.util.ArrayList;

/**
 * @(#) Table.java
 */

public class Table
{
	private int tableId;
	
	private java.util.List<Client> currentClients;
	
	private Waiter currentWaiter;
	
	public Table(int tableId){
		this.currentClients = new ArrayList<Client>();
		this.tableId = tableId;
	}
	
	public void assignWaiter(Waiter waiter)
	{	
		currentWaiter = waiter;
	}
	
	public void seatNewClients(Client client1, Client client2){
		currentClients.removeAll(currentClients);
		currentClients.add(client1);
		currentClients.add(client2);
	}

	public int getTableId() {
		return tableId;
	}

	public java.util.List<Client> getCurrentClients() {
		return currentClients;
	}

	public Waiter getCurrentWaiter() {
		return currentWaiter;
	}
	
	
}
