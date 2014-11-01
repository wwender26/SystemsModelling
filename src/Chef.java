/**
 * @(#) Chef.java
 */

public class Chef extends Staff
{
	private static final double SALARY_FOR_LOW_EXPERIENCE = 300.0;
	private static final double SALARY_FOR_MEDIUM_EXPERIENCE = 400.0;
	private static final double SALARY_FOR_HIGH_EXPERIENCE = 500.0;
	
	private static final double TRAINING_COST = 1200.0;
	
	private String taxCode;


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

	public double getTrainingCost() {
		return TRAINING_COST;
	}
	
	
}
