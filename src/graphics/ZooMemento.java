package graphics;

import java.util.ArrayList;


public class ZooMemento {
	private ArrayList<Memento> list ;
	
	public ZooMemento()
	{
		list= new ArrayList<Memento>();
	}
	public Memento getMemento(int index)
	{
		if(index < list.size())
			return list.remove(index);
		
		return null;
		
	}
	public Boolean setMemento(Memento m)
	{
		if(list.size()<3)
			return list.add(m);
		
		return false;
	}
	
}
