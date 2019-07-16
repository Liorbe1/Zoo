package mobility;

public class Mobile implements ILocatable{
	private Point location;
	private double totalDistance;
	/**
	 * Mobile constractor
	 * @param loc
	 */
	public Mobile(Point loc)
	{
		location = new Point(loc);
	}
	/**
	 * add dis to totalDistance
	 * @param dis
	 */
	public void addTotalDistance(double dis){
		totalDistance+=dis;
	}
	/**
	 * 
	 * @param point
	 * @return the calculated distance
	 */
	public double calcDistance(Point p){
		return Math.sqrt(Math.pow(location.getX()- p.getX(),2)+Math.pow(location.getY()-p.getY(), 2));
	
	}
	/**
	 * 
	 * @param point
	 * @return the calculated distance
	 */
	public double move(Point p){
		
		double d = this.calcDistance(p);
		if(this.setLocation(p))
		{
			addTotalDistance(d);
			return d;
		}
		
		return -1;
	}
	/**
	 * @return the animal location
	 */
	public Point getLocation(){
		return location;
	}
	/**
	 * @return true/false if set
	 */
	public boolean setLocation(Point location)
	{
		Point p=new Point(0,0);
		
		if(p.setX(location.getX()) && p.setY(location.getY()))
			return (this.location.setX(location.getX()) && this.location.setY(location.getY()));
		return false;
	}

}
