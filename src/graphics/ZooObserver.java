package graphics;

import java.util.Observable;
import java.util.Observer;

public class ZooObserver extends Thread implements Observer {
	/**
	 * @param Observable
	 * @param Object
	 */

	@Override
	public void update(Observable arg0, Object arg1)
	{
		// TODO Auto-generated method stub
		synchronized(this){notify();}
	}
	
	@Override
	public void run()
	{
		while(true){
			synchronized(this)
			{
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				ZooPanel.getZooPanelInstance().animalMove();
				ZooPanel.getZooPanelInstance().eatAnimal();
		}
	}
}
