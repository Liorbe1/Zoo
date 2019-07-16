package plants;

import graphics.IDrawable;
import graphics.ZooPanel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import utilities.MessageUtility;

public class Cabbage extends Plant {
	private static volatile Cabbage instance = null;
	
	private Cabbage()
	{
		super();
		loadImages("cabbage.png");
	}
	
	
	public static Cabbage getCabbageInstance()
	{
		if (instance == null)
		{
			synchronized(Cabbage.class)
			{
				if (instance == null)
					instance = new Cabbage();
			}
		}
		return instance;
	}
}
