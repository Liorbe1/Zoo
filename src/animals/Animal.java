package animals;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import mobility.Point;
import diet.Carnivore;
import diet.Herbivore;
import diet.IDiet;
import diet.Omnivore;
import food.EFoodType;
import food.IEdible;
import graphics.IAnimalBehavior;
import graphics.IDrawable;
import graphics.ZooFrame;
import graphics.ZooObserver;
import graphics.ZooPanel;
import utilities.MessageUtility;


public abstract  class Animal  extends  Observable  implements IEdible,IDrawable,IAnimalBehavior,Runnable,ColoredAnimal,Cloneable{
	
	protected String name;
	private double weight;
	private IDiet diet;
	/**/
	protected final int EAT_DISTANCE = 5;
	protected int size;
	protected Color col;
	protected int horSpeed;
	protected int verSpeed;
	protected double new_horSpeed;
	protected double new_verSpeed;
	protected boolean coordChanged;
	protected int x_dir;
	protected int y_dir;
	protected int eatCount;
	protected ZooPanel pan = ZooPanel.getZooPanelInstance();
	protected boolean threadSuspended;	 
	protected BufferedImage img1, img2;
	private boolean kill=false;
	protected Point location;
	private Observer observer;
	
	
	
	public Animal()
	{
		location= new Point(0,0);
		addObserver(pan.getObserver());
	}
	
	/**
	 * @return the animal location
	 */
	public Point getLocation(){
		return location;
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
	 * @return true/false if set
	 */
	public boolean setLocation(Point location)
	{
		Point p=new Point(0,0);
		
		if(p.setX(location.getX()) && p.setY(location.getY()))
			return (this.location.setX(location.getX()) && this.location.setY(location.getY()));
		return false;
	}
	
	
	/**
	 * 
	 * @return animal name
	 */
	public String getName() {
		MessageUtility.logGetter(name, "getName", name);
		return name;
	}
	/**
	 * 
	 * @param name
	 * @return true
	 */
	public boolean setName(String name) {
		MessageUtility.logSetter(name, "setName", name, true);
		this.name = name;
		return true;	
	}
	/**
	 * 
	 * @return animal weight
	 */
	public double getWeight() {
		MessageUtility.logGetter(name, "getWeight", weight);
		return weight;
	}
	/**
	 * 
	 * @param weight
	 * @return true if animal weight is set
	 */
	public boolean setWeight(double weight) {
		if(weight<0)
		{
			MessageUtility.logSetter(name, "setWeight", weight, false);
			return false;
		}
		MessageUtility.logSetter(name, "setWeight", weight, true);
		this.weight = weight;
		return true;
	}
	/**
	 * 
	 * @return animal diet
	 */
	public IDiet getDiet() {
		MessageUtility.logGetter(name, "getDiet", diet);
		return diet;
	}
	/**
	 * 
	 * @param diet
	 * @return true if animal diet is set
	 */
	public boolean setDiet(IDiet diet) {
		MessageUtility.logSetter(name, "setDiet", diet.toString(), true);
		this.diet = diet;
		return true;
	}
	/**
	 * abstract function
	 */
	public abstract void makeSound();
	/**
	 * 
	 * @param IEdible
	 * @return true if animal ate
	 */
	public  boolean eat(IEdible E)
	{
		
		if (diet.eat(this, E))
		{
			this.makeSound();
			MessageUtility.logBooleanFunction(name, "eat", E, true);
			return true;
		}
		else
		{
			MessageUtility.logBooleanFunction(name, "eat", E, false);
			return false;
		}
	}
	
	/**
	 * @return EFoodType
	 */
	public EFoodType getFoodType()
	{
		MessageUtility.logGetter(name, "getFoodtype","MEAT");
		return EFoodType.MEAT;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true)
		{
			if (kill)
			{
				return;
			}
				
				
			synchronized(this)
			{
				if(threadSuspended)
				{

					try{wait();}
					catch(InterruptedException e){System.out.println("Interrupted Exception");}
				}
			}
			
			try{ Thread.sleep(50);}
			catch(InterruptedException e){ System.out.println(getAnimalName() + " dead");
			return ;}
			

			/*check if exist food and if he can eat*/
			if ((pan.getMeat() != null && getDiet().canEat(EFoodType.MEAT))|| (getDiet().canEat(EFoodType.VEGETABLE) && (pan.getPlan() !=null))   )
			{
					directionToFood();
		
					
					
				
				if(getLocation().getX()>ZooFrame.sizeW/2-50 ){
					   x_dir=-1;
					}
					else  
					   x_dir=1;
					if(getLocation().getY()>ZooFrame.sizeH/2-50 )
					   y_dir=-1;
					else 
						y_dir=1;
					getLocation().setX(getLocation().getX() + (int)new_horSpeed*x_dir);
					getLocation().setY(getLocation().getY() + (int)new_verSpeed*y_dir);
					
					/*only one animal can eat*/
					synchronized(this)
					{
						if( calcDistance(new Point (((int)ZooFrame.sizeW/2-50),((int)ZooFrame.sizeH/2-50)))<=10 )
				    	{
				    		
				    			pan.eating();
				    			eatInc();
				    	}
					}

				
				
				
			}
			
			else
			{/*animals move*/
				if(!getLocation().setX(getLocation().getX()+horSpeed*x_dir))
					x_dir*=-1;
				if(!getLocation().setY(getLocation().getY()+ verSpeed*y_dir))
					y_dir*=-1;

				if(getLocation().getX() > pan.getWidth()-size)
					x_dir = -1;
				else if(getLocation().getX() < - size*0.25)
	 				x_dir = 1;
		        if(getLocation().getY() > (int) (pan.getHeight()-30 - size*9/10))
			    	y_dir = -1;
			 	else if(getLocation().getY() < size/10)
					y_dir = 1;

		        
			}
			observer.update(this, "");
			setChanges(true);
			
			
		}
		
	}
	@Override
	/**
	 * @return animal name
	 */
	public String getAnimalName() {
		// TODO Auto-generated method stub
		return getName();
	}
	@Override
	/**
	 * @return size
	 */
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}
	@Override

	public synchronized void eatInc() {
		// TODO Auto-generated method stub
		eatCount++;
	}
	@Override
	/**
	 * @return animal eat count
	 */
	public synchronized int getEatCount() {
		// TODO Auto-generated method stub
		return eatCount;
	}
	@Override
	/**
	 * @return coordChanged
	 */
	public synchronized boolean getChanges() {
		// TODO Auto-generated method stub
		return coordChanged;
	}
	@Override

	public void setSuspended() {
		// TODO Auto-generated method stub
		synchronized(this)
		{
			threadSuspended=true;
		}
	}
	@Override
	public void setResumed() {
		// TODO Auto-generated method stub
		synchronized(this)
		{
			threadSuspended=false;
			notify();
		}
	}
	@Override
	public synchronized void setChanges(boolean state) {
		// TODO Auto-generated method stub
		coordChanged=state;
	}
	/**
	 * 
	 * @returnverSpeed
	 */
	public int getVerSpeed() {return this.verSpeed;}
	/**
	 * 
	 * @return horSpeed
	 */
	public int getHorSpeed() {return this.horSpeed;}
	
	
	@Override
	/**
	 * @return color
	 */
	public String getColor() {
		// TODO Auto-generated method stub
		if (col == Color.red)
			return "Red";
		if (col == Color.blue)
			return "Blue";
		return "Natural" ;
	}
	
	public void drawObject (Graphics g)
	{
	  
	   if(x_dir==1){ // animal goes to the right side
		   
		g.drawImage(img1,this.getLocation().getX() , this.getLocation().getY(), size, size, pan);	
	   }
	   else {// animal goes to the left side
		   
		g.drawImage(img2, this.getLocation().getX() , this.getLocation().getY(), size, size, pan);
	   }
	}
	/**
	 * 
	 * @return thread
	 */
	//public Thread getThread(){return thread;}
	/**
	 * 
	 * @return coordChanged
	 */
	public boolean getState() {return coordChanged;}
	public void directionToFood(){
		
	
		double v_old=Math.sqrt(horSpeed*horSpeed+verSpeed*verSpeed);
		double dx,dy;
		
		dx=Math.abs(getLocation().getX() - ZooFrame.sizeW/2+50);
		dy=Math.abs(getLocation().getY()-ZooFrame.sizeH/2+50);
		double cos,sin;
		cos = dx/(Math.sqrt(dx*dx+dy*dy));
		sin = dy/(Math.sqrt(dx*dx+dy*dy));
		new_verSpeed=v_old*sin;
		new_horSpeed=v_old*cos;
		
		
		
		
		if(new_verSpeed > 10)
		{
		   new_verSpeed = 10;
		}
		else if(new_verSpeed < 1)
		{
		   if(getLocation().getY() != ZooFrame.sizeH/2-50)
			   new_verSpeed = 1;   
		   else
			   new_verSpeed = 0;  
		}


		if(new_horSpeed > 10)
		{
			new_horSpeed = 10;
		}
		else if(new_horSpeed < 1)
		{
		   if(getLocation().getY() != ZooFrame.sizeW/2-50)
			   new_horSpeed = 1;   
		   else
			   new_horSpeed = 0;  
		}

		
	}
	/**
	 * @param nm - animal name
	 */
	public void loadImages(String nm) {
		/*load animal images by color*/
		String color = getColor();
		if (color.equals("Red"))
		{
			try { img1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+ nm+"_r_1.png")); } 
			  catch (IOException e) { System.out.println("Cannot load image"); }
			  try { img2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+ nm + "_r_2.png")); } 
			  catch (IOException e) { System.out.println("Cannot load image"); }
		}
		else if (color.equals("Blue"))
		{
			try { img1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+ nm + "_b_1.png")); } 
			catch (IOException e) { System.out.println("Cannot load image"); }
			try { img2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+ nm + "_b_2.png")); } 
			catch (IOException e) { System.out.println("Cannot load image"); }
		}
		else
		{
			try { img1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+ nm + "_n_1.png")); } 
			catch (IOException e) { System.out.println("Cannot load image"); }
			try { img2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+ nm + "_n_2.png")); } 
			catch (IOException e) { System.out.println("Cannot load image"); }
		}
	}
	/**
	 * 
	 */
	public void Kill() {
		// TODO Auto-generated method stub
		kill=true;
	}

	/**
	 * 
	 * @param DietType
	 * @param name
	 * @param size
	 * @param horSpeed
	 * @param verSpeed
	 * @param col
	 */
	public void fillAnimal(String DietType,String name, int size, int horSpeed,int verSpeed, Color col) {
		// TODO Auto-generated method stub
		this.name=name;
		if (DietType.equals("Omnivore"))
			setDiet(new Omnivore());
		else if (DietType.equals("Herbivore"))
			setDiet(new Herbivore());
		else
			setDiet(new Carnivore());
		
		
		this.size=size;
		this.horSpeed=horSpeed;
		this.verSpeed=verSpeed;
	
		this.col=col;
		x_dir=1;
		y_dir=1;
		eatCount=0;
		threadSuspended=false;
		new_verSpeed=0;
		new_horSpeed=0;
		
		if (name.equals("Bear"))
		{
			setWeight(size*1.5);
			loadImages("bea");
		}
		else if (name.equals("Elephant"))
		{
			setWeight(size*3);
			loadImages("elf");
		}
		else if (name.equals("Giraffe"))
		{
			setWeight(size*2.2);
			loadImages("grf");
		}
		else if (name.equals("Lion"))
		{
			setWeight(size*0.8);
			loadImages("lio");
		}
		else
		{
			setWeight(size*0.5);
			loadImages("trt");
		}
		

	}
	/**
	 * 
	 * @param color
	 */
	public void setColor (String color)
	{
		if(color.equals("Red"))
			col = Color.red;
		else if(color.equals("Blue"))
			col = Color.blue;
		
		
	}
	/**
	 * @param color
	 */
	public void paintAnimal(String color)
	{
		setColor(color);
		if (getAnimalName().equals("Bear"))
			loadImages("bea");
		else if (getAnimalName().equals("Elephant"))
			loadImages("elf");
		else if (getAnimalName().equals("Giraffe"))
			loadImages("grf");
		else if (getAnimalName().equals("Lion"))
			loadImages("lio");
		else
			loadImages("trt");
		
		
	}
	/**
	 * @return Animal - clone animal
	 */
	public Animal clone()
	{
		Animal a=null;
		if(this.name=="Bear")
			a=new Bear();
		else if(name=="Elephant")
			a=new Elephant();
		else if(name=="Lion")
			a=new Lion();
		else if(name=="Giraffe")
			a= new Giraffe();
		else
			a=new Turtle();
		String d;
		
		if (diet instanceof Omnivore)
			d="Omnivore";
		else if (diet instanceof Carnivore)
				d="Carnivore";
		else
			d="Herbivore";
		a.fillAnimal(d, name, size, horSpeed, verSpeed, col);
	
		return a;
	}
	/**
	 * 
	 * @return x_dir
	 */
	public int getXDir() {return x_dir;}
	/**
	 * 
	 * @return y_dir
	 */
	public int getYDir() {return y_dir;}
	public void setXDir(int x){x_dir = x;}
	public void setYDir(int y){y_dir = y;}
	/**
	 * 
	 * @param hor
	 * @param var
	 */
	
	public void Upgrade(int hor, int var)
	{
		this.horSpeed=hor;
		this.verSpeed=var;
	}
	/**
	 * 
	 * @param o
	 */
	public void addObserver(Observer o)
	{
		observer=o;
	}
	/**
	 * 
	 * @return threadSuspended
	 */
	public boolean getSuspended() {
		// TODO Auto-generated method stub
		return threadSuspended;
	}
	

}
