package diet;

import animals.Animal;
import animals.Bear;
/**
 * 
 * @author asaf binder 308240407 and lior benisty 204513386
 *
 */
public class OmnivoreFactory implements AbstractZooFactory{

	/**
	 * @param type
	 * @return Animal 
	 */
	@Override
	public Animal produceAnimal(String type) {
		if(type.equals("Bear"))
			return new Bear();
		return null;
		
			
	}

}
