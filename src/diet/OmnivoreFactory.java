package diet;

import animals.Animal;
import animals.Bear;

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
