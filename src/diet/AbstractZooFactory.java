package diet;

import animals.Animal;
 
public interface AbstractZooFactory {
	/**
	 * 
	 * @param type
	 * @return Animal
	 */
	public Animal produceAnimal(String type);
}
