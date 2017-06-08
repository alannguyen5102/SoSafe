package sosafesystems;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Display {
	JFrame frameDisplay = new JFrame("Status");
	JLabel label3;
	JFrame fireDisplay;
	JFrame intruderDisplay;
	JFrame sprinklerDisplay;
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

	public void IntruderDetected(String location) {
		
		String info = "Intruder: " + location;
		System.out.println(info);
		intruderDisplay = new JFrame(info);
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

		intruderDisplay.dispose();
		intruderDisplay.setVisible(false);
	}
	
public void FirerDetected(String location) {
	
		
		String info = "Fire: " + location;
		fireDisplay = new JFrame(info);

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
		fireDisplay.setVisible(false);
	}
	
	
	public void SprinklerTurnOn() {
		sprinklerDisplay = new JFrame("Sprinkler");

		JPanel rootContainer = new JPanel();
		sprinklerDisplay.add(rootContainer);
		rootContainer.setBackground(Color.BLUE);
		ImageIcon icon = new ImageIcon("sprinkler.gif","Fire detected");
		JLabel label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
		JLabel label3 = new JLabel(icon);
		
		rootContainer.add(label3);
		sprinklerDisplay.pack();
		sprinklerDisplay.setLocation(999,300);
		sprinklerDisplay.setVisible(true);
		sprinklerDisplay.setResizable(false);
		
	}
	
	public void SprinklerTurnOff() {
		sprinklerDisplay.dispose();
		sprinklerDisplay.setVisible(false);
	}
	
	
}
