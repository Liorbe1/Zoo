package diet;

import animals.Animal;
import animals.Lion;
/**
 * 
 * @author asaf binder 308240407 and lior benisty 204513386
 *
 */
public class CarnivoreFactory  implements AbstractZooFactory{

	/**
	 * @param type
	 * @return Animal 
	 */
	@Override
	public Animal produceAnimal(String type) {
		// TODO Auto-generated method stub
		if(type.equals("Lion"))
			return new Lion();
		return null;
	}

}
