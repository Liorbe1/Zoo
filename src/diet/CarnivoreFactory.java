package diet;

import animals.Animal;
import animals.Lion;
 
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
