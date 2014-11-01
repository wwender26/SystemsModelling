/**
 * @(#) Order.java
 */

public class Order
{
	private final Beverage orderedBeverage;
	
	private final Dish orderedDish;
	
	private final int day;
	
	public Order(Beverage orderedBeverage, Dish orderedDish, int day){
		this.orderedBeverage = orderedBeverage;
		this.orderedDish = orderedDish;
		this.day = day;
	}
	
	public double calculateTotalPrice(){
		return orderedBeverage.getPrice() + orderedDish.getPrice();
		
	}
	
	public double calculateTotalIngredientPrice(){
		return orderedBeverage.getIngredientPrice() + orderedDish.getIngredientPrice();
	}

	public Beverage getOrderedBeverage() {
		return orderedBeverage;
	}

	public Dish getOrderedDish() {
		return orderedDish;
	}

	public int getDay() {
		return day;
	}
	
	
}
