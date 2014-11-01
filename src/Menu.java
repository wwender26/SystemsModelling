import java.util.ArrayList;

public class Menu
{
	
	private static final int BEVERAGE_COUNT = 5;
	private static final int DISH_COUNT = 5;
	
	private java.util.List<Beverage> beverages;
	
	private java.util.List<Dish> dishes;
	
	public Menu(){
		dishes = new ArrayList<Dish>();
		beverages = new ArrayList<Beverage>();
		for (int i = 0; i<BEVERAGE_COUNT; i++){
			beverages.add(new Beverage());
		}
		for (int i = 0; i<DISH_COUNT; i++){
			dishes.add(new Dish());
		}
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
	
}
