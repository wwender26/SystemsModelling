/**
 * @(#) Waiter.java
 */

public class Waiter extends Staff
{
	private static final double SALARY_FOR_LOW_EXPERIENCE = 200.0;
	private static final double SALARY_FOR_MEDIUM_EXPERIENCE = 300.0;
	private static final double SALARY_FOR_HIGH_EXPERIENCE = 400.0;
	
	private static final double TRAINING_COST = 800.0;
	
	private int waiterId;
	
	public Waiter(int waiterId){
		this.waiterId = waiterId;
	}

	public double getSalary() {
		if (Experience.HIGH == getExperience()){
			return SALARY_FOR_HIGH_EXPERIENCE;
		}
		else if(Experience.MEDIUM == getExperience()){
			return SALARY_FOR_MEDIUM_EXPERIENCE;
		}
		else {
			return SALARY_FOR_LOW_EXPERIENCE;
		}
	}

	public int getWaiterId() {
		return waiterId;
	}

	public double getTrainingCost() {
		return TRAINING_COST;
	}
	
	
}
