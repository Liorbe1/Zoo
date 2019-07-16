package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import diet.AbstractZooFactory;
import diet.CarnivoreFactory;
import diet.HerbivoreFactory;
import diet.OmnivoreFactory;
import food.Meat;
import animals.Animal;
import animals.ColoredAnimalDecorator;
import plants.Cabbage;
import plants.Lettuce;
import plants.Plant;

public class ZooPanel extends JPanel implements ActionListener{
	
	private static volatile ZooPanel instance = null;
	private static final long serialVersionUID = 1L;
	private ArrayList<Animal> animals;
	private JButton AddAnimal,Sleep,WakeUp,Clear,Food,Info,Exit;
	private JButton Decorate,Duplicate,SaveState,RestoreState;
	private BufferedImage img;
	private ZooObserver Controller; 
	private Plant plan;
	private final String[] options={"Meat","Cabbage","Lettuce"};
	private final int ThreadSize=2;
	private ThreadPoolExecutor pool;
	private Meat meat;
	private ZooMemento ZMemento;
	
	/**
	 * zoo panel constructor
	 */
	private ZooPanel()
	{
		
		animals =new ArrayList<Animal>();
		setLayout(new BorderLayout());
		JPanel menuPanel = new JPanel(new GridLayout());
		JPanel menuPanel2 = new JPanel(new GridLayout());
		this.setPreferredSize(new Dimension(ZooFrame.sizeW,ZooFrame.sizeH));
		
		
		AddAnimal =new JButton("Add Animal");
		AddAnimal.addActionListener(this);
		menuPanel.add(AddAnimal);
		
		Sleep =new JButton("Sleep");
		Sleep.addActionListener(this);
		menuPanel.add(Sleep);
		
		
		WakeUp =new JButton("Wake Up");
		WakeUp.addActionListener(this);
		menuPanel.add(WakeUp);
		
		Clear =new JButton("Clear");
		Clear.addActionListener(this);
		menuPanel.add(Clear);
		
		Food =new JButton("Food");
		Food.addActionListener(this);
		menuPanel.add(Food);
		
		Info =new JButton("Info");
		Info.addActionListener(this);
		menuPanel.add(Info);
		
		Decorate = new JButton("Decorate");
		Decorate.addActionListener(this);
		menuPanel2.add(Decorate);

		
		Duplicate = new JButton("Duplicate");
		Duplicate.addActionListener(this);
		menuPanel2.add(Duplicate);

		SaveState = new JButton("Save state");
		SaveState.addActionListener(this);
		menuPanel2.add(SaveState);

		
		RestoreState = new JButton("Restore state");
		RestoreState.addActionListener(this);
		menuPanel2.add(RestoreState);

		
		
		
		
		Exit =new JButton("Exit");
		Exit.addActionListener(this);
		menuPanel2.add(Exit);
		
		
		JPanel panel = new JPanel ();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(menuPanel,BorderLayout.SOUTH);
		panel.add(menuPanel2,BorderLayout.NORTH);
		this.add(panel,BorderLayout.SOUTH);
		
		Controller = new ZooObserver();
		Controller.setPriority(Thread.MAX_PRIORITY);
		Controller.start();
		
		plan=null;
		pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(ThreadSize);
		meat =null;
		ZMemento = new ZooMemento();
		
	}
	/**
	 * 
	 * @return singleton instance of ZooPanel
	 */
	public static ZooPanel getZooPanelInstance()
	{
		if (instance == null)
		{
			synchronized(ZooPanel.class)
			{
				if (instance == null)
					instance = new ZooPanel();
			}
		}
		return instance;
	}

	

	@Override
	/**
	 * @param ActionEvent
	 * ActionListener method override
	 * this method listener to panel buttons
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/*add animal to panel*/
		if (e.getSource() == AddAnimal)
		{
			if (pool.getQueue().size()<5)
			{
				String diet=null;
				String []options1 = {"Herbivore","Omnivore","Carnivore"};
				int n=JOptionPane.showOptionDialog(null,
					    "Animal Factory",
					    "Please choose animal factory",
					    0,
					    JOptionPane.QUESTION_MESSAGE,
					    null,
					    options1,
					    null);
				if (n==0)
					diet = options1[0];
				else if (n==1)
					diet = options1[1];
				else if (n==2)
					diet = options1[2];
				else
				{
					JOptionPane.showMessageDialog(null, "Diet not Choosen");
					return;
				}
				new AddAnimalDialog(this,diet).setVisible(true);
			}
			else
				JOptionPane.showMessageDialog(null, "You cannot add more than 15 animals");
			
			
			
		}/*freeze all animals*/
		else if (e.getSource() == Sleep)
		{
			for(int i=0;i<animals.size();i++)
				animals.get(i).setSuspended();
		}
		/*wake up option*/
		else if (e.getSource() == WakeUp)
		{
			for(int i=0;i<animals.size();i++)
				animals.get(i).setResumed();
		
		}
		/*kill tasks on pool and get task from queue*/
		else if (e.getSource() == Clear)
		{
			for(int i=0;i<animals.size();i++)
			{
				animals.get(i).setResumed();
				animals.get(i).Kill();
			}
			animals.clear();
			Object[] arr =pool.getQueue().toArray();
			for (int i=0;i<arr.length && i<ThreadSize;i++)
				animals.add((Animal)arr[i]);
			

				
			repaint();
		}
		/*add food to panel*/
		else if (e.getSource() == Food)
		{
			int op = getOption();
			
			if (plan == null && meat == null)
			{
				if(op==0)
					meat = Meat.getMeatInstance();
				else if (op==1)
					
					plan=Cabbage.getCabbageInstance();
					
					
				else if (op==2)
					plan = Lettuce.getLettuceInstance();
				else
					JOptionPane.showMessageDialog(null,"Food isnt choosen");
					
			}
			else
				JOptionPane.showMessageDialog(null,"Allready have a food");
			
			repaint();
			
		}
		/*info option*/
		else if (e.getSource() == Info)
		{
			createTable();
		
			
		}
		/*exit option*/
		if (e.getSource() == Exit)
		{
			pool.getQueue().clear();
			for(int i=0;i<animals.size();i++)
				animals.get(i).Kill();
			this.Controller.stop();
			
			System.exit(0);
		}
		/*decorate option*/
		if (e.getSource() == Decorate)
		{
			ArrayList<String> animalsProper = new ArrayList<String>();
			animalsProper.add("No Animal");
			for (int i=0;i<animals.size();i++)
			{
				if (animals.get(i).getColor().equals("Natural"))
				{
					
					animalsProper.add( i+1+".[" +animals.get(i).getAnimalName() +
							":running=true,weight="+ animals.get(i).getWeight() +
							",color=" + animals.get(i).getColor()+"]"); 

				}
					
			}
			
			Object [] ani = pool.getQueue().toArray();
			for (int i=0;i<ani.length;i++)
			{
				if (((Animal)ani[i]).getColor().equals("Natural"))
				{
					
					animalsProper.add( i+1+ThreadSize+".[" +((Animal)ani[i]).getAnimalName() +
							":running=false,weight="+ ((Animal)ani[i]).getWeight() +
							",color=" + ((Animal)ani[i]).getColor()+"]"); 

				}
			}
		
			
			if(animalsProper.size()>1)
				new DecorateDialog(animalsProper.toArray(new String[0])).setVisible(true);
			else
				JOptionPane.showMessageDialog(null, "You have no animal to decorate");
			
			
		}
		
		if (e.getSource() == Duplicate)
		{
			ArrayList<String> animalsProper = new ArrayList<String>();
			for (int i=0;i<animals.size();i++)
			{
				animalsProper.add( i+1+".[" +animals.get(i).getAnimalName() +
				":running=true,weight="+ animals.get(i).getWeight() +
				",color=" + animals.get(i).getColor()+"]"); 
			}
		
			Object [] ani = pool.getQueue().toArray();
			for (int i=0;i<ani.length;i++)
			{
				animalsProper.add( i+1+ThreadSize+".[" +((Animal)ani[i]).getAnimalName() +
				":running=false,weight="+ ((Animal)ani[i]).getWeight() +
				",color=" + ((Animal)ani[i]).getColor()+"]"); 
			}
			if(animalsProper.size()>0 && ani.length<5)
				new DuplicateDialog(animalsProper.toArray(new String[0])).setVisible(true);
			else
				JOptionPane.showMessageDialog(null, "No animals to duplicate or queue is full");
		}
		
		if (e.getSource() == SaveState)
		{
			Memento m = new Memento(animals,pool.getQueue().toArray(new Animal[0]),meat,plan);

			if(!ZMemento.setMemento(m))
				JOptionPane.showMessageDialog(null, "Only 3 states are available");
			else
				JOptionPane.showMessageDialog(null,"State saved");
		}
		if (e.getSource() == RestoreState)
		{
			String []options1 = {"State 1","State 2","State 3","Cancel"};
			int n=JOptionPane.showOptionDialog(null,
				    "Saved states",
				    "Please choose state for restore",
				    0,
				    JOptionPane.QUESTION_MESSAGE,
				    null,
				    options1,
				    null);
			if (n != 0 && n!=1 && n!=2)
				return;
				
			
			
			
			Memento m=ZMemento.getMemento(n);
	
			if(m==null)
			{
				JOptionPane.showMessageDialog(null, "Empty state");
				return;
			}
			pool.getQueue().clear();
			
			
			
			for (int i=0;i<animals.size();i++)
			{
				animals.get(i).setResumed();
				animals.get(i).Kill();
				
			}
			animals.clear();
			animals = new ArrayList<Animal>(m.getAnimals());
			
			
			for(int i=0;i<animals.size();i++)
			{
				pool.execute(animals.get(i));
			}

			for(int i=0;i<m.getQueue().size();i++)
			{
				pool.execute( m.getQueue().get(i));
			}
			meat = m.getMeat();
			plan=m.getPlant();
			
			repaint();
		}
		
	}
	
	
	public void colorAnimal(int index,String col)
	{
		if(index ==-1)
		{
			JOptionPane.showMessageDialog(null, "error convert to int");
			return;
		}
		if (index<ThreadSize)
		{
			new ColoredAnimalDecorator(animals.get(index)).paintAnimal(col);

		}
		else
		
		{
			index-=ThreadSize;
			Object [] arr = pool.getQueue().toArray();
			new ColoredAnimalDecorator(((Animal)arr[index])).paintAnimal(col);

		}
		
	}
	public void DuplicateAnimsl(int index,int var,int hor)
	{
		if (index<ThreadSize)
		{
			Animal a=animals.get(index).clone();
			a.Upgrade(hor, var);
			if(animals.size() <ThreadSize)
				animals.add(a);
			pool.execute(a);
		}
		else
		{
			index-=ThreadSize;
			Object [] arr = pool.getQueue().toArray();
			Animal a= ((Animal) arr[index]).clone();
			a.Upgrade(hor, var);
			pool.execute(a);
		}
	}

	/**
	 * @param Graphics g
	 */
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		if (img!=null) //draw image
			g.drawImage(img, 0, 0,getWidth(),getHeight() ,null); 
		if(animals!=null)
		{

			for(int i=0;i<animals.size();i++){ // draw animals
			
				animals.get(i).drawObject(g);
			}
		}
		

			
		if (meat !=null)
			meat.drawObject(g);
		else if (plan != null )
			plan.drawObject(g);
		
			
	
	
		
	 }
    public void setImage()
    {						
    	try { img = ImageIO.read(new File(IDrawable.PICTURE_PATH +"savanna.jpg")); } 
		  catch (IOException e) { System.out.println("Cannot load image"); }
    	repaint();
    }
    public void setImageToNull(){img = null;}
    
    /**
     * 
     * @return foodType
     */
    public AbstractZooFactory createAnimalFactory(String DietType)
    {
    	if (DietType.equals("Herbivore"))
    		return new HerbivoreFactory();
    	else if (DietType.equals("Omnivore"))
    		return new OmnivoreFactory();
    	else
    		return new CarnivoreFactory();
    	
    	
		
    	
    	
    }
    /**
     * 
     * @param type
     * @param size
     * @param horSpeed
     * @param verSpeed
     * @param color
     */
    public  void  addAnimal(String DietType,String type,int size,int horSpeed,int verSpeed,String color) {
    	
    	Animal temp = null;
		Color col=null;
		if(color.equals("Red"))
			col=Color.red;
		if (color.equals("Blue"))
			col=Color.blue;
		
		AbstractZooFactory factory = createAnimalFactory(DietType);
		temp = factory.produceAnimal(type);
		temp.fillAnimal(DietType,type,size,horSpeed,verSpeed,col);
		
		if(animals.size() <ThreadSize)
			animals.add(temp);
		pool.execute(temp);
		
		

    }
   
    public void createTable()
    {
    	/*create info table*/
    		
    	int eatCounter=0;
		JFrame frame_table=new JFrame("Informetion about animals");
		Object [] arr = pool.getQueue().toArray();
		String[][] rowData=new String[animals.size()+1+arr.length][7];
	
		String[] columnNames = { "Animal","State", "Color", "Weight","Hor.speed","Ver.speed","Eat counter"};
		for(int i=0;i<animals.size();i++)
		{
			rowData[i][0]=animals.get(i).getAnimalName();
			rowData[i][1]="Running";
			rowData[i][2]=animals.get(i).getColor();
			rowData[i][3]="" +animals.get(i).getWeight();
			rowData[i][4]="" +animals.get(i).getVerSpeed();
			rowData[i][5]="" +animals.get(i).getHorSpeed();
			rowData[i][6]=""+animals.get(i).getEatCount();
			eatCounter+=animals.get(i).getEatCount();
			
		}
		int j=0;
		for(int i=ThreadSize;i<ThreadSize+arr.length; i++)
		{
			rowData[i][0]=((Animal)arr[j]).getAnimalName().toString();
			rowData[i][1]="Blocked";
			rowData[i][2]=((Animal)arr[j]).getColor().toString();
			rowData[i][3]="" +((Animal)arr[j]).getWeight();
			rowData[i][4]="" +((Animal)arr[j]).getVerSpeed();
			rowData[i][5]="" +((Animal)arr[j]).getHorSpeed();
			rowData[i][6]=""+((Animal)arr[j]).getEatCount();
			eatCounter+=((Animal)arr[j]).getEatCount();
			j++;
		}
		int x =animals.size()+pool.getQueue().size();
		rowData[animals.size()+arr.length][0]="Total:"+x ;
		rowData[animals.size()+arr.length][1]="Run="+animals.size()+",Blc="+arr.length;
		rowData[animals.size()+arr.length][6]=""+eatCounter;
		
		JTable table = new JTable(rowData, columnNames);
	    JScrollPane scrollPane = new JScrollPane(table);
	    frame_table.add(scrollPane, BorderLayout.CENTER);
	    frame_table.setVisible(true);
	    frame_table.setSize(600,400);
	    frame_table.setLocationRelativeTo(null); 
	  
	    
    }
    /**
     * 
     * @return option index
     */
    public int getOption()
    {
    	return JOptionPane.showOptionDialog(null,
			    "Please choose food ",
			    "Food for animals",
			    0,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    null);
    }
    public Plant getPlan() {return plan;}

	public void eating() {
		// TODO Auto-generated method stub
	
		meat = null;
		plan=null;
				
		repaint();
	}
	public ZooObserver getObserver(){return Controller;}


	public Meat getMeat() {
		// TODO Auto-generated method stub
		return meat;
	}
	public synchronized boolean eatAnimal()
	{
	
			for (int i=0;i<animals.size();i++)
			{
				for (int j=0;j<animals.size();j++)
				{
					if (animals.get(i).getDiet().canEat(animals.get(j).getFoodType()))
					{
						if(animals.get(i).getWeight() >= animals.get(j).getWeight()*2)
						{
							if(animals.get(i).calcDistance(animals.get(j).getLocation()) < animals.get(j).getSize())
							{
								animals.get(i).eatInc();
								animals.get(j).Kill();
								animals.remove(j);
								if(pool.getQueue().peek() != null)
									animals.add((Animal) pool.getQueue().peek());
								return true;
							
							}
						}
					}
				}
			}
			return false;
		
	}
	public synchronized void animalMove()
	{
		
		for (int i=0;i<animals.size();i++)
		{
			if (animals.get(i).getState())
			{
				repaint();
			}
		}
	}
	
	
	
}
