
package graphics;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * 
 * @author asaf binder 308240407 and lior benisty 204513386
 *
 */

public class AddAnimalDialog extends JDialog {
	// define numbers to sliders
	static final int SIZE_MIN = 50;
	static final int SIZE_MAX = 300;
	static final int SIZE_INIT = 175;    //initial 
	static final int SPEED_MIN = 1;
	static final int SPEED_MAX = 10;
	static final int SPEED_INIT = 5;    //initial 
	
	private AddAnimalDialog thisObject;
	private ZooPanel panel;
	private String diet;
	private static final long serialVersionUID = 1L;
	/*sliders*/
	JComboBox<String> comboType;
	JSlider sliderSize;
	JSlider sliderHorSpeed;
	JSlider sliderVerSpeed;
	JComboBox<String> comboColor;
	/**
	 * 
	 * @param pan
	 */
	public AddAnimalDialog(ZooPanel pan,String d){
		diet= d;
		thisObject=this;
		this.panel=pan;
		this.setSize(500,400);
		
		JPanel p=new JPanel();
		this.add(p);
		
		String sColor[]=new String[]{"Natural","Blue","Red"}; //array of colors
	
		String Omnivores[]=new String[] {"Bear"}; //array of types
		String Carnivores[]=new String[]{"Lion"};
		String Herbivores[]=new String []{"Giraffe","Elephant","Turtle"};
		String [] ch;
		p.setLayout(new GridLayout(6,2));
		// labels of panel
		JLabel labelType=new JLabel("Type");
		JLabel labelSize=new JLabel("Size");
		JLabel labelhorSpeed=new JLabel("Horizontal speed");
		JLabel labelVerSpeed=new JLabel("Vertical speed");
		JLabel labelColor=new JLabel("Color");
		if (diet.equals("Omnivore"))
			ch = Omnivores;
		else if(diet.equals("Carnivore"))
			ch=Carnivores;
		else
			ch=Herbivores;
		comboType=new JComboBox<String>(ch); //combo of types
		p.add(labelType);
		p.add(comboType);

		//add size label and size slider to the panel
		p.add(labelSize);
		sliderSize = new JSlider(JSlider.HORIZONTAL, SIZE_MIN, SIZE_MAX, SIZE_INIT);
		sliderSize.setMajorTickSpacing(50);
		sliderSize.setMinorTickSpacing(10);
		sliderSize.setPaintTicks(true);
		sliderSize.setPaintLabels(true);
		p.add(sliderSize);

		//add horizontal speed label and horizontal speed slider to the panel
		p.add(labelhorSpeed);
		sliderHorSpeed = new JSlider(JSlider.HORIZONTAL, SPEED_MIN, SPEED_MAX, SPEED_INIT);
		sliderHorSpeed.setMajorTickSpacing(1);
		sliderHorSpeed.setMinorTickSpacing(10);
		sliderHorSpeed.setPaintTicks(true);
		sliderHorSpeed.setPaintLabels(true);
		p.add(sliderHorSpeed);
		
		//add vertical speed label and vertical speed slider to the panel
		p.add(labelVerSpeed);
		sliderVerSpeed = new JSlider(JSlider.HORIZONTAL, SPEED_MIN, SPEED_MAX, SPEED_INIT);
		sliderVerSpeed.setMajorTickSpacing(1);
		sliderVerSpeed.setMinorTickSpacing(10);
		sliderVerSpeed.setPaintTicks(true);
		sliderVerSpeed.setPaintLabels(true);
		p.add(sliderVerSpeed);
		
		//add color label and color combo to the the panel
		p.add(labelColor);
		comboColor=new JComboBox<String>(sColor);
		p.add(comboColor);
		
		//add back button to the panel
		JButton bBack=new JButton("Back");
		bBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg){
				thisObject.dispose();
			}
		});
		p.add(bBack);
		
		//add save button to the panel
		JButton bSave=new JButton("Save");
		bSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg){
				saveAnimal();
			}
		});
		p.add(bSave);
		
		setModal(true);
		this.setLocationRelativeTo(null);	
	}
	/**
	 * insert a new animal to list of animals in aquaPanel
	 */
	private void saveAnimal(){
		panel.addAnimal(diet,comboType.getSelectedItem().toString(), sliderSize.getValue(), sliderHorSpeed.getValue(), sliderVerSpeed.getValue(), comboColor.getSelectedItem().toString());
		thisObject.dispose();
	}

}
