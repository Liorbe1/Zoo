package diet;

import animals.Animal;
/**
 * 
 * @author asaf binder 308240407 and lior benisty 204513386
 *
 */
public interface AbstractZooFactory {
	/**
	 * 
	 * @param type
	 * @return Animal
	 */
	public Animal produceAnimal(String type);
}
