package animals;

import java.util.Random;
import food.EFoodType;
import food.IEdible;
import utilities.MessageUtility;


public class Lion extends AnimalThatRoars{
	
	private int scarCount;
	/**
	 * Lion constractor
	 * @param name
	 */

	public Lion()
	{
		super();
		
	}

	/**
	 * @return true if Lion ate and set scar by random
	 */
	public boolean eat(IEdible E){
		if( super.eat(E))
		{
			
			Random rand = new Random();
			
			
			if (rand.nextInt(2)==1)
			{
				this.setScarCount(this.scarCount+1);
			}
			
			return true;
		}
		return false;	
	}
	/**
	 * 
	 * @return Lions scarCount
	 */
	public int  getScarCount()
	{
		MessageUtility.logGetter(name, "getScarCount", scarCount);
		return scarCount;
	}
	/**
	 * 
	 * @param scarCount
	 * @return true / false of set
	 */
	public  boolean setScarCount(int scarCount)
	{
		MessageUtility.logSetter(name, "setScarCount",scarCount, true);
		this.scarCount=scarCount;
		return true;
	}
	/**
	 * @return EFoodType
	 */
	public EFoodType getFoodType()
	{
		MessageUtility.logGetter(name, "getFoodtype","NOTFOOD");
		return EFoodType.NOTFOOD;
	}
	/**
	 * Lion sound
	 */
	public void roar()
	{
		MessageUtility.logSound(name,"Roars, then stretches and shakes its mane");
	}
	/**
	 * Lion toString
	 */
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] "+name;
	}
	
	
	
	
}
