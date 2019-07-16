package graphics;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ZooFrame extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	public final static int sizeW =800;
	public final static int sizeH =600;
	private JMenuBar bar;
	private JMenu file,backGround,help;
	private JMenuItem iExit ,image,green,iNone,iHelp;
	private ZooPanel panel=null;
	/**
	 * 
	 * @param p
	 */
	public ZooFrame(ZooPanel p)
	{
		
		panel=p;
		setPreferredSize(new Dimension(sizeW,sizeH));
		/*items*/
		iExit=new JMenuItem("Exit");
		iExit.addActionListener(this);
		image=new JMenuItem("Image");
		image.addActionListener(this);
		green=new JMenuItem("Green");
		green.addActionListener(this);
		iNone=new JMenuItem("None");
		iNone.addActionListener(this);
		iHelp=new JMenuItem("Help");
		iHelp.addActionListener(this);
		/*menus*/
		file = new JMenu("File");
		backGround=new JMenu("background");
		help=new JMenu("Help");
		/**/
		bar = new JMenuBar();
		
		file.add(iExit);
		backGround.add(image);
		backGround.add(green);
		backGround.add(iNone);
		help.add(iHelp);

		bar.add(file);
		bar.add(backGround);
		bar.add(help);
		
		setJMenuBar(bar);
		
		setName("Zoo");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(panel);
		pack();
		this.setLocationRelativeTo(null);
	}
	public static void main(String[] args) 
	{
		
		ZooFrame f=new ZooFrame(ZooPanel.getZooPanelInstance());
		
		f.setVisible(true);
		
		
		
	}
	@Override
	/**
	 * @param ActionEvent
	 * ActionListener method override
	 * this method listener to menu bar 
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == iExit)
			System.exit(0);
		else if (e.getSource() == iHelp)
			JOptionPane.showMessageDialog(null, "Home Work 3\n GUI @ Threads");
		
		else if(e.getSource()== image)
		{
			panel.setImage();
			repaint();
		}
		else if (e.getSource()== iNone)
		{
			panel.setImageToNull();
			panel.setBackground(Color.WHITE);
			repaint();

		
		}
		
		else if (e.getSource() == green)
		{
			panel.setImageToNull();
			panel.setBackground(Color.green);
			repaint();
		}
	}
}
