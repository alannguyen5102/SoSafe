package sosafesystems;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Display {
	JFrame frameDisplay = new JFrame("Status");
	JFrame fireDisplay = new JFrame("Fire");
	JFrame intruderDisplay = new JFrame("Intruder");
	JLabel label3;
	public void Secured() {
		
		JPanel rootContainer = new JPanel();
		frameDisplay.add(rootContainer);
		rootContainer.setBackground(Color.GREEN);
		
		ImageIcon icon = new ImageIcon("security.gif","Your home is secured");
		JLabel label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
		label3 = new JLabel(icon);
		
		rootContainer.add(label3);
		
		frameDisplay.pack();
		frameDisplay.setLocation(200,100); 
		frameDisplay.setVisible(true);
		frameDisplay.setResizable(false);
	}
	
	public void Unsecured() {
		label3 = null;

		frameDisplay.dispose();
	}

	public void IntruderDetected() {
		
		label3 = null;
		JPanel rootContainer = new JPanel();
		intruderDisplay.add(rootContainer);
		rootContainer.setBackground(Color.RED);
		ImageIcon icon = new ImageIcon("burgler.gif","Intruder detected");
		JLabel label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
		JLabel label3 = new JLabel(icon);
		
		rootContainer.add(label3);
		
		intruderDisplay.pack();
		intruderDisplay.setLocation(200,600);
		intruderDisplay.setVisible(true);
		intruderDisplay.setResizable(false);

	}
	
	public void IntruderCaught() {
		label3 = null;

		intruderDisplay.dispose();

	}
	
public void FirerDetected() {
	
		label3 = null;
		JPanel rootContainer = new JPanel();
		fireDisplay.add(rootContainer);
		rootContainer.setBackground(Color.ORANGE);
		ImageIcon icon = new ImageIcon("fire.gif","Fire detected");
		JLabel label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
		JLabel label3 = new JLabel(icon);
		
		rootContainer.add(label3);
		fireDisplay.pack();
		fireDisplay.setLocation(800,100);
		fireDisplay.setVisible(true);
		fireDisplay.setResizable(false);

	}
	
	public void FireCaught() {
		fireDisplay.dispose();
	}

}
