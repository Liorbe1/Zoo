package animals;


public abstract class AnimalThatRoars extends Animal{
	
	
	/**
	 * AnimalThatRoars constractor
	 * @param name
	 * @param p
	 */

	public AnimalThatRoars(){super();}

	/**
	 * absract function
	 */
	public abstract void roar();
	/**
	 * make animal sound
	 */
	public void makeSound()
	{
		roar();
	}
}
