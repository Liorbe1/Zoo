package animals;

import utilities.MessageUtility;
import food.IEdible;


public class Elephant extends AnimalsThatChews {
	private double trunkLength;
	/**
	 * Elephant constractor
	 * @param name
	 * @param trunkLength
	 */
	
	public Elephant()
	{
		super();
		
	}
	
	
	/**
	 * 
	 * @return Elephants TrunkLength
	 */
	public double getTrunkLength() {
		MessageUtility.logGetter(name, "getTrunkLength", trunkLength);
		return trunkLength;
	}
	/**
	 * 
	 * @param trunkLength
	 * @return true/false of set
	 */
	public boolean setTrunkLength(double trunkLength) {
		if (trunkLength>=0.5 && trunkLength<=3)
		{
			MessageUtility.logSetter(name, "setTrunkLength", trunkLength, true);
			this.trunkLength = trunkLength;
			return true;
		}
		MessageUtility.logSetter(name, "setTrunkLength", trunkLength, false);
		return false;
	}
	/**
	 * @return true if the animal ate
	 */
	public boolean eat(IEdible E){
		return super.eat(E);	
	}
	
	/**
	 * Elephant sound
	 */
	public void chew()
	{
		MessageUtility.logSound(name,"Trumpets with joy while flapping its ears, then chews");
	}
	/**
	 * Elephant toString
	 */
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] "+name;
	}
	
	
	
	

}
