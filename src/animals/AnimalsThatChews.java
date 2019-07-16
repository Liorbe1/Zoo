package animals;


public abstract class AnimalsThatChews extends Animal{
	/**
	 * AnimalsThatChews constractor
	 * @param name
	 * @param point
	 */

	public AnimalsThatChews(){super();}
	
	/**
	 * abstract function
	 */
	public abstract void chew();
	/**
	 * make animal sound
	 */
	public void makeSound()
	{
		chew();
	}
}
