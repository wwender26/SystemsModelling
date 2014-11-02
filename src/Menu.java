import java.util.ArrayList;
import java.util.Random;

public class Menu
{
	
	private static final int BEVERAGE_COUNT = 5;
	private static final int DISH_COUNT = 5;
	
	private java.util.List<Beverage> beverages;
	
	private java.util.List<Dish> dishes;
	
	public Menu(){
		dishes = new ArrayList<Dish>();
		beverages = new ArrayList<Beverage>();
		dishes.add(new Dish("Burger", 500.0));
		dishes.add(new Dish("French Fries", 400.0));
		dishes.add(new Dish("Pasta", 600.0));
		dishes.add(new Dish("Ice cream", 300.0));
		dishes.add(new Dish("Slice of cake", 350.0));
		beverages.add(new Beverage("Beer", 0.5));
		beverages.add(new Beverage("Coca-Cola", 0.25));
		beverages.add(new Beverage("Juice", 0.2));
		beverages.add(new Beverage("water", 1.0));
		beverages.add(new Beverage("Espresso", 0.1));
	}
	
	public void setDisheshAttributes(int highQualityDishesCount, double priceOfHighQualityDish, double priceOfLowQualityDish){
		Dish.setPriceOfHighQualityMeal(priceOfHighQualityDish);
		Dish.setPriceOfLowQualityMeal(priceOfLowQualityDish);
		for (int i = 0; i<DISH_COUNT; i++){
			Dish mutable = dishes.get(i);
			if (i < highQualityDishesCount){
				mutable.setQuality(Quality.HIGH);
			}
			else{
				mutable.setQuality(Quality.LOW);
			}
			
		}
	}
	
	public void setBeverageshAttributes(int highQualityBeverageCount, double priceOfHighQualityBeverage, double priceOfLowQualityBeverage){
		Beverage.setPriceOfHighQualityBeverage(priceOfHighQualityBeverage);
		Beverage.setPriceOfLowQualityBeverage(priceOfLowQualityBeverage);
		for (int i = 0; i<BEVERAGE_COUNT; i++){
			Beverage mutable = beverages.get(i);
			if (i < highQualityBeverageCount){
				mutable.setQuality(Quality.HIGH);
			}
			else{
				mutable.setQuality(Quality.LOW);
			}
			
		}
	}
	
	public Dish getRandomDish(){
		return dishes.get(new Random().nextInt(DISH_COUNT));
	}
	
	public Beverage getRandomBeverage(){
		return beverages.get(new Random().nextInt(BEVERAGE_COUNT));
	}
}
