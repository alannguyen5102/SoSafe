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

		try {
			userReader = new BufferedReader(new FileReader("user.txt"));
			String line;
			line = userReader.readLine();
			String tokens[] = line.split("\\*");
			
			userId = tokens[0];
			userName = tokens[1];
			password = tokens[2];
			address = tokens[3];
			ec1 = tokens[5];
			ec2 = tokens[6];
		
			
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (userReader != null) {
				try {
					userReader.close();
				} catch (IOException er) {
					er.printStackTrace();
				}
			}
		}
		
		
		totalLine = countLines("sensors.txt");
		setNameTextField.setText(userName);
		setPasswordTextField.setText(""); // setPasswordTextField.setText(password);
		setAddressTextField.setText(address);
		emergencyContactTextFieldOne.setText(ec1);
		emergencyContactTextFieldTwo.setText(ec2);
		
		totalLineString = totalLine.toString();
		
		showTotalFeild.setText(totalLineString);
		
		String sensorId = totalLine.toString();
		
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
				else{
					makeSensorArray.add("M");
				}
				makeSensorArray.add("*");
				makeSensorArray.add(totalLineString.toString());
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
		middlePanel.add(setAddressPanel);
		middlePanel.add(addSensorPanel);
		middlePanel.add(showTotalSensorPanel);
		middlePanel.add(includeFirePanel);
		middlePanel.add(emergencyContactPanel);
		
		JLabel setNameLabel = new JLabel("Set User Name:");
		setNameLabel.setForeground(Color.WHITE);
		
		//TO-DO have to validate
		setNameTextField = new JTextField(15);
		setNamePanel.add(setNameLabel);
		setNamePanel.add(setNameTextField);

		//set password
		
		JLabel setPasswordLabel = new JLabel("Set Password:");
		setPasswordLabel.setForeground(Color.WHITE);
		
		//TO-DO have to validate
		setPasswordTextField = new JPasswordField(15);
		setPasswordPanel.add(setPasswordLabel);
		setPasswordPanel.add(setPasswordTextField);
		
		JLabel setAddressLabel = new JLabel("Set Address:");
		setNameLabel.setForeground(Color.WHITE);
		
		//TO-DO have to validate
		setAddressTextField = new JTextField(15);
		setAddressPanel.add(setAddressLabel);
		setAddressPanel.add(setAddressTextField);
		setAddressLabel.setForeground(Color.WHITE);

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
		
		fireAlarmCheck = new JCheckBox("Include Fire Alarm System");
		addSensorPanel.add(fireAlarmCheck);
		fireAlarmCheck.setSelected(false);
		fireAlarmCheck.setBackground(Color.DARK_GRAY);
		fireAlarmCheck.setForeground(Color.WHITE);
		

		
		
		
		showTotalSensorPanel.setLayout(new FlowLayout());
		totalSelectedSectionLabel.setForeground(Color.WHITE);
		//showTotalLabel.setForeground(Color.WHITE);
		showTotalSensorPanel.add(totalSelectedSectionLabel);
		showTotalSensorPanel.add(showTotalFeild);
		
       
		JLabel emergencyContactLabelOne = new JLabel("Emergency Contact: 1");
		JLabel emergencyContactLabelTwo = new JLabel("Emergency Contact: 2");

		emergencyContactLabelOne.setForeground(Color.WHITE);
		emergencyContactLabelTwo.setForeground(Color.WHITE);
		
		emergencyContactTextFieldOne = new JTextField(15);
		emergencyContactTextFieldTwo = new JTextField(15);
		
		emergencyContactPanel.setLayout(new BoxLayout(emergencyContactPanel, BoxLayout.Y_AXIS));
		emergencyContactPanel.add(emergencyContactOne);
		emergencyContactPanel.add(emergencyContactTwo);
		
		emergencyContactOne.add(emergencyContactLabelOne);
		emergencyContactOne.add(emergencyContactTextFieldOne);
		
		emergencyContactTwo.add(emergencyContactLabelTwo);
		emergencyContactTwo.add(emergencyContactTextFieldTwo);
		
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
				if(setNameTextField.getText().equals("") || setAddressTextField.getText().equals("") || setPasswordTextField.getText().equals("") || emergencyContactTextFieldOne.getText().equals("") || emergencyContactTextFieldTwo.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Field cannot be empty");
				}
				else{int dialogueResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to save this configuration?");
				
				Integer userCount = 0;
				if(dialogueResult == 0)
				{
					//save result
					
					try {
						userCount = countLines("users.txt");
					} catch (IOException e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}
					userCount++;
					
					makeUserArray.add(userCount.toString());
					makeUserArray.add("*");
					makeUserArray.add(setNameTextField.getText());
					makeUserArray.add("*");
					
					//encrypting password
					HashPassword hp = new HashPassword();
					String enPass = null;
					try {
						enPass = hp.hashPassword(setPasswordTextField.getText());
					} catch (NoSuchAlgorithmException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					makeUserArray.add(enPass);
					makeUserArray.add("*");
					makeUserArray.add(setAddressTextField.getText());
					makeUserArray.add("*");
					if(fireAlarmCheck.isSelected())
					{
						makeUserArray.add("F");
					}
					else 
					{
						makeUserArray.add("M");
					}
					makeUserArray.add("*");
					makeUserArray.add(emergencyContactTextFieldOne.getText());
					makeUserArray.add("*");
					makeUserArray.add(emergencyContactTextFieldTwo.getText());
					
					BufferedWriter writer = null;
					try {
						writer = new BufferedWriter(new FileWriter("user.txt", true));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					for (String str : makeUserArray) {
						try {
							writer.write(str);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					try {
						writer.newLine();
						writer.close();
						JOptionPane.showMessageDialog(null, "Data successfully added");

					} catch (IOException e1) {
						e1.printStackTrace();
					}

					makeUserArray.clear();
					
					//go back to login page
					try {
						LogInWindow lgin = new LogInWindow();
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
		if(fireAlarmCheck.isSelected())
		{
			makeSensorArray.add("F");
		}
		else{
			makeSensorArray.add("M");
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
