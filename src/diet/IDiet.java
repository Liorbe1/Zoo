package diet;
import food.EFoodType;
import food.IEdible;
import animals.Animal;

/**
 * 
 * @author asaf binder 308240407 and lior benisty 204513386
 *
 */
public interface IDiet {
	public boolean canEat(EFoodType food);
	public boolean eat(Animal animal, IEdible food);
	public String toString();
}
