/**
 * @(#) Dish.java
 */

public class Dish extends Menuitem
{
	private static final double HIGH_QUALITY_DISH_INGREDIENT_COST = 10.0;
	private static final double LOW_QUALITY_DISH_INGREDIENT_COST = 3.0;
	
	private double calorieCount;
	
	private static double priceOfHighQualityMeal;
	
	private static double priceOfLowQualityMeal;

	public double getIngredientPrice() {
		if (getQuality() == Quality.HIGH){
			return HIGH_QUALITY_DISH_INGREDIENT_COST;
		}
		else{
			return LOW_QUALITY_DISH_INGREDIENT_COST;
		}
	}

	public double getPrice() {
		if (getQuality() == Quality.HIGH){
			return priceOfHighQualityMeal;
		}
		else{
			return priceOfLowQualityMeal;
		}
	}

	public static void setPriceOfHighQualityMeal(double priceOfHighQualityMeal) {
		Dish.priceOfHighQualityMeal = priceOfHighQualityMeal;
	}

	public static void setPriceOfLowQualityMeal(double priceOfLowQualityMeal) {
		Dish.priceOfLowQualityMeal = priceOfLowQualityMeal;
	}
	
	
}
