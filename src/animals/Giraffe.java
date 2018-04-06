package animals;


import food.IEdible;
import utilities.MessageUtility;

/**
 * 
 * @author asaf binder 308240407 and lior benisty 204513386
 *
 */
public class Giraffe extends AnimalsThatChews{
	private double neckLength;
	/**
	 * Giraffe constractor
	 * @param name
	 * @param neckLength
	 */
	
	public Giraffe()
	{
		super();

	
	
	}

	/**
	 * 
	 * @return Giraffe neckLength
	 */
	public double getNeckLength() {
		MessageUtility.logGetter(name, "getNeckLength", neckLength);
		return neckLength;
	}
	/**
	 * 
	 * @param neckLength
	 * @return true / false of set
	 */
	public boolean setNeckLength(double neckLength) {
		if (neckLength>=1 && neckLength<=2.5)
		{
			MessageUtility.logSetter(name, "setNeckLength", neckLength, true);
			this.neckLength = neckLength;
			return true;
		}
		MessageUtility.logSetter(name, "setNeckLength", neckLength, false);
		return false;
	}
	/**
	 * @return true if animal ate
	 */
	public boolean eat(IEdible E){
		return super.eat(E);
	}
	
	/**
	 * Giraffe sound
	 */
	public void chew()
	{
		MessageUtility.logSound(name,"Bleats and Stomps its legs, then chews");
	}
	/**
	 * Giraffe toString
	 */
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] "+name;
	}
	


}
