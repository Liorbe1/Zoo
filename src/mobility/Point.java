package mobility;

import graphics.ZooFrame;

/**
 * 
 * @author asaf binder 308240407 and lior benisty 204513386
 *
 */
public class Point {
	
	
	public final int highXLimit = 800;
	public final int  highYLimit =600;
	public final int  lowXLimit = 0;
	public final int  lowYLimit = 0;

	
	private int x,y;
	/**
	 * 
	 * @param x 
	 * @param y 
	 */
	public Point(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	/**
	 * 
	 * @param other
	 */
	public Point(Point other)
	{
		this.x=other.x;
		this.y=other.y;
	}
	/**
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}
	/**
	 * 
	 * @param x
	 * @return true/false
	 */
	public boolean setX(int x) {


		if (x>=0 && x<= ZooFrame.sizeW)
		{
			
			this.x=x;
			return true;
		}
		else
			return false;
		
		
		
	}
	/**
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}
	/**
	 * 
	 * @param y
	 * @return true/false
	 */
	public boolean setY(int y) {

		if (y>=0 && y<= ZooFrame.sizeW)
		{
			this.y=y;
			return true;
		}
		else
			return false;
	
	}
	
	public static boolean cheackBounderies(Point other)
	{
		Point p=new Point(0,0);
		
		return p.setX(other.x) && p.setY(other.y);
	}
	/**
	 * @return string
	 */
	public String toString(){
		return x+","+y;
	}
	
	
	
	
	

}
