package animals;

import food.IEdible;
import utilities.MessageUtility;

public class Turtle extends AnimalsThatChews{
	private int age;
	/**
	 * Turtle constractor
	 * @param name
	 * @param age
	 */

	public Turtle()
	{
		super();
		
	}
	
	/**
	 * 
	 * @return Turtle age
	 */
	public double getAge() {
		MessageUtility.logGetter(name, "getAge", age);
		return age;
	}
	/**
	 * 
	 * @param age
	 * @return true/false of set
	 */
	public boolean setAge(int age) {
		if (age>=0 && age<=500)
		{
			MessageUtility.logSetter(name, "setAge", age, true);
			this.age = age;
			return true;
		}
		MessageUtility.logSetter(name, "setAge", age, false);
		return false;
	}
	/**
	 * @return true if animal ate
	 */
	public boolean eat(IEdible E){
		return super.eat(E);
	}

	/**
	 * Turtle sound
	 */
	public void chew()
	{
		MessageUtility.logSound(name,"Retracts its head in then eats quietly");
	}
	/**
	 * Turtle toString
	 */
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] "+name;
	}
	
	
	
	
	
	


}
