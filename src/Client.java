import java.util.Random;

/**
 * @(#) Client.java
 */
public class Client extends Person
{
	private String Surname;
	
	private String Name;
	
	private long telNumber;
	
	private String taxCode;
	
	private java.util.List<Order> orders;
	
	private Order currentOrder;
	
	public boolean isSatisfiedWithService(Waiter waiter)
	{
		double randDouble = new Random().nextDouble();
		if (randDouble < 0.6){
			return true;
		}
		if (waiter.getExperience() == Experience.HIGH){
			if (randDouble < 0.9){
				return true;
			}
		}
		else if (waiter.getExperience() == Experience.MEDIUM){
			if (randDouble < 0.8){
				return true;
			}
		}
		return false;
	}
	
	public boolean isSatisfiedWithFood(Chef chef){
		double randDouble = new Random().nextDouble();
		Quality qualityOfFood = currentOrder.getOrderedDish().getQuality();
		if (qualityOfFood == Quality.HIGH){
			randDouble = randDouble - 0.2;
		}
		if (randDouble < 0.4){
			return true;
		}
		if (chef.getExperience() == Experience.HIGH){
			if (randDouble < 0.8){
				return true;
			}
		}
		else if (chef.getExperience() == Experience.MEDIUM){
			if (randDouble < 0.6){
				return true;
			}
		}
		return false;	
	}
	
	public boolean isSatisfiedWithBeverages(Barman barman){
		double randDouble = new Random().nextDouble();
		Quality qualityOfBeverages = currentOrder.getOrderedBeverage().getQuality();
		if (qualityOfBeverages == Quality.HIGH){
			randDouble = randDouble - 0.2;
		}
		if (randDouble < 0.4){
			return true;
		}
		if (barman.getExperience() == Experience.HIGH){
			if (randDouble < 0.8){
				return true;
			}
		}
		else if (barman.getExperience() == Experience.MEDIUM){
			if (randDouble < 0.6){
				return true;
			}
		}
		return false;
		
	}
	
	public void orderFoodAndPay(Budget budget, Menu menu, int day){
		currentOrder = new Order(menu.getRandomBeverage(), menu.getRandomDish(), day);
		budget.increaseBudget(currentOrder.calculateTotalPrice());
		orders.add(currentOrder);
	}
	
	public void genStatistics( )
	{
		
	}
	
	
}
