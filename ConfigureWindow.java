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
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.SwingUtilities;

public class ConfigureWindow {
	Integer i;
	JFrame frame;
	JPanel rootContainer;
	JTextField setNameTextField;
	JPasswordField setPasswordTextField;
	JTextField setAddressTextField;
	JTextField emergencyContactTextFieldOne;
	JTextField emergencyContactTextFieldTwo;
	JButton closeButton;
    JButton saveButton;
    JButton addSectionButton;
    JTextField addSectionTextField;
    JCheckBox fireAlarmCheck;
    JCheckBox motionAlarmCheck;
    JTextField showTotalFeild = new JTextField(3);
	String totalLineString = null;

	ArrayList<String> makeSensorArray = new ArrayList<String>();
	ArrayList<String> makeUserArray = new ArrayList<String>();
	Integer startingSensorId;
	Integer totalLine;
	String userId = null;
	
	
    public ConfigureWindow(String readPassword) throws IOException
    {
    	MakeFrame();
    	BufferedReader userReader = null;
		String  userName = null, password = null, address = null, ec1 = null, ec2 = null;

		
		String sensorId = "1";
		
		addSectionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(addSectionTextField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Give sensor a location name");
				}
				else{
				try {
					totalLine = countLines("sensors.txt");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				makeSensorArray.clear();
				makeSensorArray.add(userId);
				makeSensorArray.add("*");
				if(fireAlarmCheck.isSelected())
				{
					makeSensorArray.add("F");
				}
				else if(motionAlarmCheck.isSelected())
				{
					makeUserArray.add("M");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Select sensor type");
				}
				makeSensorArray.add("*");
				makeSensorArray.add("1");
				makeSensorArray.add("*");
				makeSensorArray.add(addSectionTextField.getText());
				makeSensorArray.add("*");
				makeSensorArray.add("true");
				makeSensorArray.add("*");
				makeSensorArray.add("true");
				makeSensorArray.add("*");
				makeSensorArray.add("false");
				makeSensorArray.add("*");
				makeSensorArray.add("00:00");
				makeSensorArray.add("*");
				makeSensorArray.add("23:00");
	
							
				BufferedWriter writer = null;
				try {
					writer = new BufferedWriter(new FileWriter("sensors.txt", true));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				

				for (String str : makeSensorArray) {
					try {
						writer.write(str);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				try {
					writer.newLine();
					JOptionPane.showMessageDialog(null, "Sensor added");
					writer.close();
					totalLine++;
					String totalLineString = totalLine.toString();
					showTotalFeild.setText(totalLineString);
					

				} catch (IOException e1) {
					e1.printStackTrace();
				}

				makeSensorArray.clear();
				addSectionTextField.setText("");
				fireAlarmCheck.setSelected(false);
				motionAlarmCheck.setSelected(false);
			}
			}
		});
		
		
		

    	
    }
	
    
    
    public ConfigureWindow() throws IOException {
		MakeFrame();
		addSectionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MakeSensorData();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		

		
	}
	
	
	public void MakeFrame() throws IOException
	{
		frame = new JFrame("SoSafe Security System: Setting up");
		LayoutManager overlay = new OverlayLayout(frame.getContentPane());
		frame.getContentPane().setLayout(overlay);

		ImagePanel imagePanel = new ImagePanel("banner.png");
		imagePanel.setLocation(0, 0);

		rootContainer = new OpaquePanel();
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
		JPanel setPhonePanel = new OpaquePanel();
		JPanel setAddressPanel = new OpaquePanel();
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
		middlePanel.add(setPhonePanel);
		middlePanel.add(setAddressPanel);
		middlePanel.add(addSensorPanel);
		middlePanel.add(showTotalSensorPanel);
		middlePanel.add(includeFirePanel);
		middlePanel.add(emergencyContactPanel);
		
		


	
		



		//add sections to monitor
		JLabel addSectionLabel = new JLabel("Add section");
		addSectionTextField = new JTextField(15);
		
		addSectionButton = new JButton("Add");
		
		
		addSectionLabel.setForeground(Color.white);
		addSensorPanel.add(addSectionLabel);
		addSensorPanel.add(addSectionTextField);
		includeFirePanel.add(addSectionButton);
		
		JLabel totalSelectedSectionLabel = new JLabel("Total number of rooms newly selected: ");
		showTotalFeild.setText("0");
		
		fireAlarmCheck = new JCheckBox("Fire Sensor");
		motionAlarmCheck = new JCheckBox("Motion Sensor");
		addSensorPanel.add(fireAlarmCheck);
		addSensorPanel.add(motionAlarmCheck);
		fireAlarmCheck.setSelected(false);
		fireAlarmCheck.setBackground(Color.DARK_GRAY);
		fireAlarmCheck.setForeground(Color.WHITE);

		motionAlarmCheck.setBackground(Color.DARK_GRAY);
		motionAlarmCheck.setForeground(Color.WHITE);

		
		
		
		showTotalSensorPanel.setLayout(new FlowLayout());
		totalSelectedSectionLabel.setForeground(Color.WHITE);
		//showTotalLabel.setForeground(Color.WHITE);
		showTotalSensorPanel.add(totalSelectedSectionLabel);
		showTotalSensorPanel.add(showTotalFeild);
		
       

		
		bottomPanel.add(closePanel);
		
		closeButton = new JButton("Close");
        saveButton = new JButton("Save");
		closePanel.add(closeButton);
		closePanel.add(saveButton);
		
        closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
			}
		});
        saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = new String("Saving");
				JOptionPane.showMessageDialog(null, msg);
				
				
			}
		});
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

	private void MakeSensorData() throws IOException {
		
		Integer startingSensorId = countLines("sensors.txt");
		showTotalFeild.setText(startingSensorId.toString());
		startingSensorId++;
		
		Integer userCount = countLines("user.txt");
		userCount++;
		userId = userCount.toString();
		String sensorId = startingSensorId.toString();
		
		if(addSectionTextField.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Give sensor a location name");
		}
		makeSensorArray.add(userId);
		makeSensorArray.add("*");
		if(fireAlarmCheck.isSelected() && motionAlarmCheck.isSelected())
		{
			makeUserArray.add("B");
		}
		else if(fireAlarmCheck.isSelected())
		{
			makeUserArray.add("F");
		}
		else if(motionAlarmCheck.isSelected()) 
		{
			makeUserArray.add("M");
		}
		makeSensorArray.add("*");
		makeSensorArray.add(startingSensorId.toString());
		makeSensorArray.add("*");
		makeSensorArray.add(addSectionTextField.getText());
		makeSensorArray.add("*");
		makeSensorArray.add("true");
		makeSensorArray.add("*");
		makeSensorArray.add("true");
		makeSensorArray.add("*");
		makeSensorArray.add("false");
		makeSensorArray.add("*");
		makeSensorArray.add("00:00");
		makeSensorArray.add("*");
		makeSensorArray.add("23:00");
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("sensors.txt", true));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		

		for (String str : makeSensorArray) {
			try {
				writer.write(str);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			writer.newLine();
			JOptionPane.showMessageDialog(null, "Sensor added");
			writer.close();
			showTotalFeild.setText(sensorId);
			

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		makeSensorArray.clear();
		addSectionTextField.setText("");
		fireAlarmCheck.setSelected(false);
	
	}

	public static int countLines(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	        }
	       System.out.println("count" + count);
	        return (count == 0 && !empty) ? 1 : count;
	    } finally {
	        is.close();
	    }
	}

}
