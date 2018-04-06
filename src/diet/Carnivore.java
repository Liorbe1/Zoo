package diet;

import food.EFoodType;
import food.IEdible;
import animals.Animal;
/**
 * 
 * @author asaf binder 308240407 and lior benisty 204513386
 *
 */
public class Carnivore implements IDiet {
	
	
	/**
	 * @return true if can eat EFoodType
	 */
	public boolean canEat (EFoodType food){
		return food == EFoodType.MEAT ;
	}
	/**
	 *@return true if animal ate
	 */
	public boolean eat(Animal animal, IEdible food)
	{
		if (this.canEat(food.getFoodType()))
		{
			animal.setWeight(animal.getWeight()*1.1);
			return true;
		}
			
		return false;
			
	}
	/**
	 * Carnivore toString
	 */
	public String toString()
	{
		return  "[Carnivore]";
	}
	

}
