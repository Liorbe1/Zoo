package animals;
 
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
