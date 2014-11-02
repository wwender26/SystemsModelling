import java.util.ArrayList;
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
	
	private double satisfactionPercent = 100;
	
	public Client(){
		orders = new ArrayList<Order>();
	}
	
	private void calculateSatisfactionPercentage(){
		int multiplier =  ((int)(currentOrder.calculateTotalPrice()-currentOrder.calculateTotalIngredientPrice())/3);
		if (multiplier <= 0){
			return;
		}
		else{
			satisfactionPercent = satisfactionPercent - 10*multiplier;
		}
		if (satisfactionPercent < 0){
			satisfactionPercent = 0;
		}
	}
	
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
		calculateSatisfactionPercentage();
		orders.add(currentOrder);
	}
	
	public void genStatistics( )
	{
		double moneySpent = 0;
		double avgCalorieCount = 0;
		double avgVolume = 0;
		for (Order order:orders){
			moneySpent = moneySpent + order.calculateTotalPrice();
			avgCalorieCount = avgCalorieCount + order.getOrderedDish().getCalorieCount();
			avgVolume = avgVolume + order.getOrderedBeverage().getVolume();
		}
		avgCalorieCount = avgCalorieCount/orders.size();
		avgVolume = avgVolume / orders.size();		
		System.out.println("Money spent: " + moneySpent);
		System.out.println("Average calories: " + avgCalorieCount);
		System.out.println("Average volume: " + avgVolume);
		System.out.println("Satsifaction Percent: "  + satisfactionPercent + "%");
	}

	public Order getCurrentOrder() {
		return currentOrder;
	}
	
	
}
