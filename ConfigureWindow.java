package sosafesystems;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.SwingUtilities;

public class ConfigureWindow implements ActionListener {

	public ConfigureWindow() throws IOException {

		JFrame frame = new JFrame("SoSafe Security System: Setting up");
		LayoutManager overlay = new OverlayLayout(frame.getContentPane());
		frame.getContentPane().setLayout(overlay);

		ImagePanel imagePanel = new ImagePanel("banner.png");
		imagePanel.setLocation(0, 0);

		JPanel rootContainer = new OpaquePanel();
		frame.add(rootContainer);
		frame.add(imagePanel);

		rootContainer.setLayout(new GridLayout(4, 1));
		
		
		//creating configure window
		JPanel topPanel = new OpaquePanel();
        JPanel topTwoPanel = new OpaquePanel();
		JPanel middlePanel = new OpaquePanel();
		JPanel bottomPanel = new OpaquePanel();
		
		rootContainer.add(topPanel);
		rootContainer.add(topTwoPanel);
		rootContainer.add(middlePanel);
		rootContainer.add(bottomPanel);
		
		
		//middle panel
		JPanel setNamePanel = new OpaquePanel();
		JPanel setPasswordPanel = new OpaquePanel();
		JPanel addSensorPanel = new OpaquePanel();
		JPanel showTotalSensorPanel = new OpaquePanel();
		JPanel includeFirePanel = new OpaquePanel();
		JPanel emergencyContactPanel = new OpaquePanel();
		JPanel emergencyContactOne = new OpaquePanel();
		JPanel emergencyContactTwo = new OpaquePanel();
		JPanel closePanel = new OpaquePanel();

		
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
		
		middlePanel.add(setNamePanel);
		middlePanel.add(setPasswordPanel);
		middlePanel.add(addSensorPanel);
		middlePanel.add(showTotalSensorPanel);
		middlePanel.add(includeFirePanel);
		middlePanel.add(emergencyContactPanel);
		
		JLabel setNameLabel = new JLabel("Set User Name:");
		setNameLabel.setForeground(Color.WHITE);
		
		//TO-DO have to validate
		JTextField setNameTextField = new JTextField(15);
		setNamePanel.add(setNameLabel);
		setNamePanel.add(setNameTextField);

		//set password
		
		JLabel setPasswordLabel = new JLabel("Set Password:");
		setPasswordLabel.setForeground(Color.WHITE);
		
		//TO-DO have to validate
		JTextField setPasswordTextField = new JTextField(15);
		setPasswordPanel.add(setPasswordLabel);
		setPasswordPanel.add(setPasswordTextField);
		
		
		//add sections to monitor
		JLabel addSectionLabel = new JLabel("Add section");
		JTextField addSectionTextField = new JTextField(15);
		JButton addSectionButton = new JButton("Add");
		
		
		addSectionLabel.setForeground(Color.white);
		addSensorPanel.add(addSectionLabel);
		addSensorPanel.add(addSectionTextField);
		addSensorPanel.add(addSectionButton);
		
		showTotalSensorPanel.setLayout(new FlowLayout());
		JLabel totalSelectedSectionLabel = new JLabel("Total number of rooms selected: ");
		JLabel showTotalLabel = new JLabel("5");
		totalSelectedSectionLabel.setForeground(Color.WHITE);
		showTotalLabel.setForeground(Color.WHITE);
		showTotalSensorPanel.add(totalSelectedSectionLabel);
		showTotalSensorPanel.add(showTotalLabel);
		
		JCheckBox fireAlarmCheck = new JCheckBox("Include Fire Alarm System");
		includeFirePanel.add(fireAlarmCheck);
		fireAlarmCheck.setSelected(false);
		fireAlarmCheck.setBackground(Color.DARK_GRAY);
		fireAlarmCheck.setForeground(Color.WHITE);
       
		JLabel emergencyContactLabelOne = new JLabel("Emergency Contact: 1");
		JLabel emergencyContactLabelTwo = new JLabel("Emergency Contact: 2");

		emergencyContactLabelOne.setForeground(Color.WHITE);
		emergencyContactLabelTwo.setForeground(Color.WHITE);
		
		JTextField emergencyContactTextFieldOne = new JTextField(15);
		JTextField emergencyContactTextFieldTwo = new JTextField(15);
		
		emergencyContactPanel.setLayout(new BoxLayout(emergencyContactPanel, BoxLayout.Y_AXIS));
		emergencyContactPanel.add(emergencyContactOne);
		emergencyContactPanel.add(emergencyContactTwo);
		
		emergencyContactOne.add(emergencyContactLabelOne);
		emergencyContactOne.add(emergencyContactTextFieldOne);
		
		emergencyContactTwo.add(emergencyContactLabelTwo);
		emergencyContactTwo.add(emergencyContactTextFieldTwo);
		
		bottomPanel.add(closePanel);
		
		JButton closeButton = new JButton("Close");
        JButton saveButton = new JButton("Save");
		closePanel.add(closeButton);
		closePanel.add(saveButton);
        closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
        saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogueResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to save this configuration?");
				// TODO properly save configuration into file
				if(dialogueResult == 0)
				{
					//save result
					//start the system
					try {
						ControlPanelGUI cpGUI = new ControlPanelGUI();
						frame.dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(dialogueResult == 1)
				{
					//do nothing
				}
				else if(dialogueResult == 2)
				{
					//do nothing
				}
				
			}
		});
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
