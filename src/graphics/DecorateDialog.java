package graphics;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


import animals.Animal;
/**
 * 
 * @author asaf binder 308240407 and lior benisty 204513386
 *
 */
public class DecorateDialog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Animal> AnimalsList;
	private ZooPanel pan;
	private String color=null;
	private int index;
	public DecorateDialog(String[] animals)
	{
		pan = ZooPanel.getZooPanelInstance();
		this.setSize(600,250);
		JPanel mainP = new JPanel( new GridLayout());
		mainP.setSize(600,250);
		JPanel left =new JPanel (new BorderLayout());
		left.setSize(300, 125);
		left.setBorder(BorderFactory.createTitledBorder("Select Animal to decorate"));
	
		JPanel right=new JPanel(new GridLayout());
		right.setBorder(BorderFactory.createTitledBorder("Choose decoration color"));
		right.setSize(300, 125);
		
		mainP.add(left);
		mainP.add(right);
		this.add(mainP);

		AnimalsList = new JComboBox(animals);
		left.add(AnimalsList,BorderLayout.NORTH);
		JButton OK = new JButton("OK");
		left.add(OK,BorderLayout.SOUTH);
		
		
		ButtonGroup bag = new ButtonGroup();
		JRadioButton red = new JRadioButton("Red");
		JRadioButton blue = new JRadioButton("Blue");
		bag.add(blue);
		bag.add(red);
		right.add(red);
		right.add(blue);
		
		OK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg){
				if((!red.isSelected() && !blue.isSelected())||(AnimalsList.getSelectedIndex()==-1))
				{
					JOptionPane.showMessageDialog(null, "must choose animal and color to decorate");
				}
				else
				{
					if(red.isSelected())
						color="Red";
					else
						color = "Blue";
					
					String x=AnimalsList.getSelectedItem().toString();
					String[] s =x.split(Pattern.quote("."));
					try
					{
						index=Integer.decode(s[0]).intValue();
					}
					catch(NumberFormatException e)
					{
						index=-1;
					}
					colorAnimal();
					
				}
					
			
			}
			});
		
		setModal(true);
		this.setLocationRelativeTo(null);	
		
	}

	private void colorAnimal()
	{
		this.dispose();
		pan.colorAnimal(index-1, color);
	}
	

}