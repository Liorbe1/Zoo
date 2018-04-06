package graphics;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

import animals.Animal;
/**
 * 
 * @author asaf binder 308240407 and lior benisty 204513386
 *
 */
public class DuplicateDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Animal> AnimalsList;
	private ZooPanel pan;
	static final int SPEED_MIN = 0;
	static final int SPEED_MAX = 10;
	static final int SPEED_INIT = 5;
	JSlider sliderHorSpeed;
	JSlider sliderVerSpeed;
	
	public DuplicateDialog(String[] animals)
	{
		pan = ZooPanel.getZooPanelInstance();
		this.setSize(600,250);
		JPanel mainP = new JPanel( new GridLayout());
		mainP.setSize(600,250);
		JPanel left =new JPanel (new BorderLayout());
		left.setSize(300, 125);
		left.setBorder(BorderFactory.createTitledBorder("Select Animal to clone"));
	
		JPanel right=new JPanel(new GridLayout(4,0));
		right.setBorder(BorderFactory.createTitledBorder("speed may be changed"));
		right.setSize(300, 125);
		
		mainP.add(left);
		mainP.add(right);
		this.add(mainP);
		
		AnimalsList = new JComboBox(animals);
		left.add(AnimalsList,BorderLayout.NORTH);
		JButton OK = new JButton("OK");
		OK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg){
				if(AnimalsList.getSelectedIndex()==-1)
					JOptionPane.showMessageDialog(null, "must choose animal to duplicate");
				else
					DuplicateAnimal();
			}
		});
		left.add(OK,BorderLayout.SOUTH);
		
		JLabel labelhorSpeed=new JLabel("Horizontal speed");
		JLabel labelVerSpeed=new JLabel("Vertical speed");
		
		sliderHorSpeed = new JSlider(JSlider.HORIZONTAL, SPEED_MIN, SPEED_MAX, SPEED_INIT);
		sliderHorSpeed.setMajorTickSpacing(1);
		sliderHorSpeed.setMinorTickSpacing(10);
		sliderHorSpeed.setPaintTicks(true);
		sliderHorSpeed.setPaintLabels(true);
		right.add(labelhorSpeed);
		right.add(sliderHorSpeed);
		
		sliderVerSpeed = new JSlider(JSlider.HORIZONTAL, SPEED_MIN, SPEED_MAX, SPEED_INIT);
		sliderVerSpeed.setMajorTickSpacing(1);
		sliderVerSpeed.setMinorTickSpacing(10);
		sliderVerSpeed.setPaintTicks(true);
		sliderVerSpeed.setPaintLabels(true);
		right.add(labelVerSpeed);
		right.add(sliderVerSpeed);
		
		setModal(true);
		this.setLocationRelativeTo(null);
		
	}
	
	public void DuplicateAnimal()
	{
		this.dispose();
		pan.DuplicateAnimsl(AnimalsList.getSelectedIndex(), sliderVerSpeed.getValue(), sliderHorSpeed.getValue());
		
	}
}
