package animals;
/**
 * 
 * @author asaf binder 308240407 and lior benisty 204513386
 *
 */
public class ColoredAnimalDecorator implements ColoredAnimal {
	private ColoredAnimal animal;
	
	/**
	 * 
	 * @param an
	 */
	public ColoredAnimalDecorator(ColoredAnimal an)
	{
		animal=an;
	}
	/**
	 * @param color
	 */
	public void paintAnimal(String color)
	{
		animal.paintAnimal(color);
	}
	
}
