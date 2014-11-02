/**
 * @(#) Beverage.java
 */

public class Beverage extends Menuitem
{
	private static final double HIGH_QUALITY_BEVERAGE_INGREDIENT_COST = 3.0;
	private static final double LOW_QUALITY_BEVERAGE_INGREDIENT_COST = 1.0;
		
	private double volume;
	
	private static double priceOfLowQualityBeverage;

	private static double priceOfHighQualityBeverage;
	
	public Beverage(String name, double volume){
		setName(name);
		setVolume(volume);
	}

	public double getIngredientPrice() {
		if (getQuality() == Quality.HIGH){
			return HIGH_QUALITY_BEVERAGE_INGREDIENT_COST;
		}
		else{
			return LOW_QUALITY_BEVERAGE_INGREDIENT_COST;
		}
	}
	
	public double getPrice(){
		if (getQuality() == Quality.HIGH){
			return priceOfHighQualityBeverage;
		}
		else{
			return priceOfLowQualityBeverage;
		}
	}

	public static void setPriceOfLowQualityBeverage(double priceOfLowQualityBeverage) {
		Beverage.priceOfLowQualityBeverage = priceOfLowQualityBeverage;
	}


	public static void setPriceOfHighQualityBeverage(
			double priceOfHighQualityBeverage) {
		Beverage.priceOfHighQualityBeverage = priceOfHighQualityBeverage;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	
	
}
