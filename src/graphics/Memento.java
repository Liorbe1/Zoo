package graphics;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import plants.Plant;
import animals.Animal;
import food.Meat;

public class Memento {

	private ArrayList<Animal> MementoAnimals;
	private ArrayList<Animal> MementoQueue;
	private Meat meat;
	private Plant plant;
	public Memento( ArrayList<Animal> arr,Animal[] an,Meat m,Plant p)
	{
		
		meat = m;
		plant = p;
		MementoQueue =new ArrayList<Animal>();
		MementoAnimals =new ArrayList<Animal>();
		for(int i=0;i<arr.size();i++)
		{
			MementoAnimals.add(arr.get(i).clone());
			MementoAnimals.get(i).setLocation(arr.get(i).getLocation());
			MementoAnimals.get(i).setXDir(arr.get(i).getXDir());
			MementoAnimals.get(i).setYDir(arr.get(i).getYDir());

		}
		for (int i=0;i<an.length;i++)
		{
			MementoQueue.add(an[i].clone());
			MementoQueue.get(i).setLocation(an[i].getLocation());
			MementoQueue.get(i).setXDir(an[i].getXDir());
			MementoQueue.get(i).setYDir(an[i].getYDir());
		}
		
	
	}
	/**
	 *  
	 * @return MementoAnimals - array list of animals
	 */
	public ArrayList<Animal> getAnimals()
	{
		return MementoAnimals;
	}
	
	/**
	 * 
	 * @return MementoQueue - array list of animals
	 */
	public ArrayList<Animal> getQueue()
	{
		return MementoQueue;
	}
	/**
	 * 
	 * @return meat
	 */
	public Meat getMeat(){return meat;}
	/**
	 * 
	 * @return plant
	 */
	public Plant getPlant(){return plant;}
}
