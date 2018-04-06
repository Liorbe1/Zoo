package food;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import mobility.ILocatable;
import mobility.Point;
import graphics.IDrawable;
import graphics.ZooFrame;
import graphics.ZooPanel;
/**
 * 
 * @author asaf binder 308240407 and lior benisty 204513386
 */
public class Meat implements IEdible, ILocatable,IDrawable {
	private static volatile Meat instance = null;
	private Point location;
	private BufferedImage img;
	/**
	 * constructor
	 * 
	 */
	private Meat(){
		this.location = new Point(ZooFrame.sizeW/2-50 ,ZooFrame.sizeH/2-50);
		this.loadImages("");
	}
	/**
	 * 
	 * @return instance
	 */
	public static Meat getMeatInstance()
	{
		if (instance == null)
		{
			synchronized(Meat.class)
			{
				if (instance == null)
					instance = new Meat();
			}
		}
		return instance;
	}
	@Override
	/**
	 * initiate bufferedImage variable
	 */
	public void loadImages(String c) {
		try { 
			img = ImageIO.read(new File(PICTURE_PATH+"meat.gif"));
		 } 
		  catch (IOException e) { System.out.println("Cannot load image"); }
		
	}
	/**
	 * @param Graphics
	 */
	@Override
	public void drawObject(Graphics g) {
		g.drawImage(img, ZooFrame.sizeW/2-50 ,ZooFrame.sizeH/2-50, 100, 100, ZooPanel.getZooPanelInstance());
		
	}
	/**
	 * @return null
	 */

	@Override
	public String getColor() {
		return null;
	}
	/**
	 * @return Point-location
	 */
	@Override
	public Point getLocation() {
		
		return this.location;
	}
	
	/**
	 * @param Point
	 * @return true if set successfully
	 */
	@Override
	public boolean setLocation(Point newLocation) {
		boolean isSuccess = Point.cheackBounderies(newLocation);
		if (isSuccess) {
			this.location = newLocation;
		}
		return isSuccess;
		
	}
	/**
	 * return EFoodType - Meat
	 */
	@Override
	public EFoodType getFoodType() {
		
		return EFoodType.MEAT;
	}

	public void setImageToNull(){
		this.img=null;
	}

	
}