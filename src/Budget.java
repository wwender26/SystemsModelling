/**
 * @(#) Budget.java
 */
public class Budget
{

	private double budget = 10000.0;

	public double getBudget() {
		return budget;
	}
	
	public void increaseBudget(double inflow){
		budget = budget + inflow;
	}
	
	public void decreaseBudget(double outflow) throws InsufficientFundsException{
		if (budget - outflow < 0){
			throw new InsufficientFundsException();
		}
		else{
			budget = budget - outflow;
		}
		
	}
	
	
}
