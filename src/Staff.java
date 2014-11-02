/**
 * @(#) Staff.java
 */

public abstract class Staff extends Person implements Comparable<Staff>
{
	private Experience experience = Experience.LOW;
	
	public abstract double getSalary( );
	
	public void increaseExperience(Budget budget) throws InsufficientFundsException, MaxLevelException{
		budget.decreaseBudget(getTrainingCost());
		switch (getExperience()){
		case LOW:
			setExperience(Experience.MEDIUM);
			break;
		case MEDIUM:
			setExperience(Experience.HIGH);
			break;
		case HIGH:
			throw new MaxLevelException();
		}
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
	
	public int compareTo(Staff o) {
		return getExperience().compareTo(o.getExperience());
	}
	
}
