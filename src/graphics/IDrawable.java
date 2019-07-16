package graphics;

import java.awt.Graphics;

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
