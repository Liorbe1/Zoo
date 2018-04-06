package graphics;

import java.awt.Graphics;
/**
 * 
 * @author asaf binder 308240407 and lior benisty 204513386
 *
 */
public interface IDrawable {
	 public final static String PICTURE_PATH ="C:\\Users\\Liorbe\\Documents\\pictures\\";
	 /**
	  * 
	  * @param nm
	  */
	 public void loadImages(String nm);
	 /**
	  * 
	  * @param g
	  */
	 public void drawObject (Graphics g);
	/**
	 * 
	 * @return color
	 */
	 public String getColor();

}