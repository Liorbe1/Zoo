package diet;

import animals.Animal;
import animals.Elephant;
import animals.Giraffe;
import animals.Turtle;

public class HerbivoreFactory implements AbstractZooFactory {

	/**
	 * @param type
	 * @return Animal 
	 */
	@Override
	public Animal produceAnimal(String type) {
		// TODO Auto-generated method stub
		if(type.equals("Elephant"))
			return new Elephant();
		else if(type.equals("Giraffe"))
			return new Giraffe();
		else
			return new Turtle();
	}
}
