package diet;
import animals.Animal;
import food.EFoodType;
import food.IEdible;
/**
 * 
 * @author asaf binder 308240407 and lior benisty 204513386
 *
 */
public class Herbivore implements IDiet{
	/**
	 * @return true if can eat EFoodType
	 */
	public boolean canEat (EFoodType food){
		return food == EFoodType.VEGETABLE  ;
	}
	/**
	 * @return true if animal ate
	 */
	public boolean eat(Animal animal, IEdible food)
	{
		if (!(canEat(food.getFoodType())))
			return false;
		if (food.getFoodType() == EFoodType.VEGETABLE)
			animal.setWeight(animal.getWeight()*1.07);
		return true;
	}
	/**
	 * Herbivore toString
	 */
	public String toString()
	{
		return "[Herbivore]";
	}

}
