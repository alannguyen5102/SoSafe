package sosafesystems;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Password {
	private String readPassword;
	
	public Password(String password) {
		this.readPassword = password;
		
		JFrame frame = new JFrame("Provide Password");
		JPanel rootContainer = new OpaquePanel();
		
		frame.add(rootContainer);
		rootContainer.setLayout(new GridLayout(3, 1));
		
		JPanel labelPanel = new OpaquePanel();
		JPanel fieldPanel = new OpaquePanel();
		JPanel closePanel = new OpaquePanel();
		
		JLabel passwordLabel = new JLabel("Please enter your password");
		JPasswordField passwordField = new JPasswordField(15);
		
		JButton okButton = new JButton("OK");
		JButton closeButton = new JButton("Close");
		
		rootContainer.add(labelPanel);
		rootContainer.add(fieldPanel);
		rootContainer.add(closePanel);
		
		labelPanel.add(passwordLabel);
		fieldPanel.add(passwordField);
		closePanel.add(okButton);
		closePanel.add(closeButton);

		closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(readPassword.equals(passwordField.getText()))
				{
					try {
						System.out.println(readPassword);
						System.out.println(passwordField.getText());
						ConfigureWindow cfwp = new ConfigureWindow(password);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		frame.setSize(400, 200);
		//frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	
	}

}
