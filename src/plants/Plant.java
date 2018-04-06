package plants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;




import javax.imageio.ImageIO;

import food.EFoodType;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooFrame;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;
import utilities.MessageUtility;

/**
 * @author asaf binder 308240407 and lior benisty 204513386
 *
 */
public abstract class Plant implements IEdible, ILocatable,IDrawable {
	/**
	 * 
	 */
	private double height;
	/**
	 * 
	 */
	private Point location;
	/**
	 * 
	 */
	private double weight;
	private BufferedImage image;
	//private ZooPanel panel;
	private ZooPanel panel;
	/**
	 * 
	 */

	public Plant()
	{
		panel=ZooPanel.getZooPanelInstance();
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see food.IFood#getFoodtype()
	 */
	@Override
	public EFoodType getFoodType() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getFoodType", EFoodType.VEGETABLE);
		return EFoodType.VEGETABLE;
	}

	/**
	 * @return
	 */
	public double getHeight() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getHeight", this.height);
		return this.height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mobility.ILocatable#getLocation()
	 */
	@Override
	public Point getLocation() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getLocation", this.location);
		return this.location;
	}

	/**
	 * @return
	 */
	public double getWeight() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getWeight", this.weight);
		return weight;
	}

	/**
	 * @param height
	 * @return
	 */
	public boolean setHeight(double height) {

		boolean isSuccess = (height >= 0);
		if (isSuccess) {
			this.height = height;
		} else {
			this.height = 0;
		}
		MessageUtility.logSetter(this.getClass().getSimpleName(), "setHeight", height, isSuccess);
		return isSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mobility.ILocatable#setLocation(mobility.Point)
	 */
	@Override
	public boolean setLocation(Point newLocation) {
		boolean isSuccess = Point.cheackBounderies(newLocation);
		if (isSuccess) {
			this.location = newLocation;
		}
		MessageUtility.logSetter(this.getClass().getSimpleName(), "setLocation", newLocation, isSuccess);
		return isSuccess;
	}

	/**
	 * @param weight
	 * @return
	 */
	public boolean setWeight(double weight) {
		boolean isSuccess = (weight >= 0);
		if (isSuccess) {
			this.weight = weight;
		} else {
			this.weight = 0;
		}
		MessageUtility.logSetter(this.getClass().getSimpleName(), "setWeight", weight, isSuccess);

		return isSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] ";
	}
	public void setImage(BufferedImage im)
	{
		image=im;
	}

	

	@Override
	public void drawObject (Graphics g)
	{
	   
		
	    g.drawImage(image,ZooFrame.sizeW/2-50 ,ZooFrame.sizeH/2-50,100,100,panel);	
	    
	
	}

	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return "Green";
	}
	
	@Override
	public void loadImages(String nm) {
		// TODO Auto-generated method stub
		try {setImage(ImageIO.read(new File(IDrawable.PICTURE_PATH+nm ))); } 
		  catch (IOException e) { System.out.println("Cannot load image"); }
	}

}
