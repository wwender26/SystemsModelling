/**
 * @(#) Staff.java
 */

public abstract class Staff extends Person
{
	private Experience experience = Experience.LOW;
	
	public abstract double getSalary( );
	
	public void increaseExperience(Budget budget) throws InsufficientFundsException{
		budget.decreaseBudget(getTrainingCost());
	};

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}
	
	public void paySalary(Budget budget) throws OutOfBudgetException{
		budget.decreaseBudget(getSalary());
	}
	
	public abstract double getTrainingCost();
	
}
