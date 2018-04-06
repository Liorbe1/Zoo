package diet;
import animals.Animal;
import food.EFoodType;
import food.IEdible;
/**
 * 
 * @author asaf binder 308240407 and lior benisty 204513386
 *
 */
public class Omnivore implements IDiet  {
	/**
	 * @return true if can eat EFoodType
	 */
	private IDiet Her,Car;
	public Omnivore()
	{
		Her = new Herbivore();
		Car = new Carnivore();
	}
	public boolean canEat (EFoodType food){
		return food != EFoodType.NOTFOOD;
	}
	/**
	 * @return true if animal ate
	 */
	public boolean eat(Animal animal,IEdible food)
	{
		
		if (Her.eat(animal, food))
			return true;
	
		return (Car.eat(animal, food));
	}
	/**
	 * Omnivore toString
	 */
	public String toString()
	{
		return "[Omnivore]";
	}
}
