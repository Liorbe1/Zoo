package plants;

import graphics.IDrawable;
import graphics.ZooPanel;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import utilities.MessageUtility;

/**
 * @author asaf binder 308240407 and lior benisty 204513386
 * 
 */
public class Lettuce extends Plant {

	private static volatile Lettuce instance =null;
	
	private Lettuce()
	{
		super();
		loadImages("lettuce.png");
	}

	public static Lettuce getLettuceInstance()
	{
		if (instance == null)
		{
			synchronized(Lettuce.class)
			{
				if (instance == null)
					instance = new Lettuce();
			}
		}
		return instance;
	}

	
}
