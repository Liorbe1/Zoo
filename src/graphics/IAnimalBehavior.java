package graphics;

/**
 * 
 * @author Liorbe
 *
 */

public interface IAnimalBehavior {
	/**
	 * 
	 * @return animal name
	 */
	public String getAnimalName();
	/**
	 * 
	 * @return
	 */
	 public int getSize();
	 public void eatInc();
	 /**
	  * 
	  * @return eat count
	  */
	 public int getEatCount();
	 public boolean getChanges ();
	 public void setSuspended();
	 public void setResumed();
	 /**
	  * 
	  * @param state
	  */
	 public void setChanges (boolean state);


}
