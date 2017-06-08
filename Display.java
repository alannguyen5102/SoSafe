package sosafesystems;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Display {
	JFrame frame = new JFrame("Status");
	public void Secured() {
		
		JPanel rootContainer = new JPanel();
		frame.add(rootContainer);
		rootContainer.setBackground(Color.GREEN);
		
		ImageIcon icon = new ImageIcon("security.gif","Your home is secured");
		JLabel label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
		JLabel label3 = new JLabel(icon);
		
		rootContainer.add(label3);
		
		frame.pack();
		frame.setLocation(200,100);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public void Unsecured() {
		frame.dispose();
	}

	public void IntruderDetected() {
		
		frame.dispose();
		frame.setVisible(false);
		JPanel rootContainer = new JPanel();
		frame.add(rootContainer);
		rootContainer.setBackground(Color.GREEN);
		ImageIcon icon = new ImageIcon("burgler.gif","Intruder detected");
		JLabel label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
		JLabel label3 = new JLabel(icon);
		
		rootContainer.add(label3);
		
		frame.pack();
		frame.setLocation(200,100);
		frame.setVisible(true);
		frame.setResizable(false);

	}
	
	public void IntruderCaught() {
		frame.dispose();

	}
	
public void FirerDetected() {
		frame.dispose();
		frame.setVisible(false);
		JPanel rootContainer = new JPanel();
		frame.add(rootContainer);
		rootContainer.setBackground(Color.GREEN);
		ImageIcon icon = new ImageIcon("fire.gif","Fire detected");
		JLabel label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
		JLabel label3 = new JLabel(icon);
		
		rootContainer.add(label3);
		System.out.println("inside fire method");
		frame.pack();
		frame.setLocation(200,100);
		frame.setVisible(true);
		frame.setResizable(false);

	}
	
	public void FireCaught() {
		frame.dispose();
	}

}
