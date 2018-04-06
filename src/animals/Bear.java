package animals;



import food.IEdible;
import utilities.MessageUtility;

/**
 * 
 * @author asaf binder 308240407 and lior benisty 204513386
 *
 */


public class Bear extends AnimalThatRoars{
	
	private String furColor;
	/**
	 * Bear coonstractor
	 * @param name
	 * @param color
	 */
	
	public Bear()
	{
		super();
		
		
	}
	
	/**
	 * 
	 * @return FurColor
	 */
	public String getFurColor() {
		MessageUtility.logGetter(name, "getFurColor", furColor);
		return furColor;
	}
	/**
	 * 
	 * @param furColor
	 * @return true/false if the set is done
	 */
	public boolean setFurColor(String furColor) {
		if(furColor != "WHITE" && furColor != "BLACK" && furColor != "GRAY" )
		{
			MessageUtility.logSetter(name, "setFurColor", furColor, false);
			return false;
		}
		MessageUtility.logSetter(name, "setFurColor", furColor, true);
		this.furColor = furColor;
		return true;
	}
	/**
	 * @param IEdible
	 * return true/false if animal ate
	 */
	public boolean eat(IEdible E){
	
		return super.eat(E);
		
	}
	
	/**
	 * bear sound
	 */
	public void roar()
	{
		MessageUtility.logSound(name,"Stands on its hind legs, roars and scratches its belly");
	}
	/**
	 * bear toString
	 */
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] "+name;
	}
	
	
	


}
