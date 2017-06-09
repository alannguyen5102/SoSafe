package sosafesystems;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Simulator {
	ArrayList<String> listSensors = new ArrayList<String>();
	JComboBox<String> sensorList;
	Display display = new Display();
	String sensorId = null;
	String sensorLocation = null;
	Boolean passwordCheck = false;
	LocalTime currentTime;

	public void SimulateFire(AlarmSystem system) {
		currentTime = LocalTime.now();
		

		JFrame frame = new JFrame("Simulator");
		JPanel rootPanel = new OpaquePanel();
		frame.add(rootPanel);

		rootPanel.setLayout(new GridLayout(2, 1));
		JPanel listPanel = new JPanel();
		JPanel closePanel = new JPanel();

		JButton testButton = new JButton("Test");
		JButton closeButton = new JButton("Close");

		rootPanel.add(listPanel);
		rootPanel.add(closePanel);
		// populating sensorList from file
		BufferedReader userReader = null;
		String location = null;

		try {
			userReader = new BufferedReader(new FileReader("sensors.txt"));
			String line;
			while ((line = userReader.readLine()) != null) {
				String tokens[] = line.split("\\*");
				location = tokens[3];
				sensorLocation = location;
				if (tokens[1].equals("F") || tokens[1].equals("B")){
					listSensors.add(location);
					sensorId = tokens[2];

				}
			}
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

		String[] locationList = listSensors.toArray(new String[listSensors.size()]);
		sensorList = new JComboBox<>(locationList);
		listPanel.add(sensorList);

		closePanel.add(testButton);
		closePanel.add(closeButton);

		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (listSensors.isEmpty()) {
					frame.dispose();
				} else {
					listSensors.clear();
					display.FireCaught();
					frame.dispose();
				}
			}
		});
		
		testButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Thread passwordThread = new Thread() {
					public void run() {
						//Call Costumer
						try {
						
							
							Thread.sleep(10000);
							if(!passwordCheck)
							{
								System.out.println("CALLING EMERGNY");
								system.callMonitoringService("FIRE");
								String msg = new String("Calling Emergency");
								JOptionPane.showMessageDialog(null, msg);
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						SwingUtilities.invokeLater(new Runnable() {

							public void run() {
							
							
							

							}
						});// End of SwingUtilities.invokeLater
					}
				};
				
				if (listSensors.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No fire sensors found");
				} else if (system.checkTemperatureSensors(sensorList.getSelectedItem().toString())) {
					String msg = new String("Fire" + "| Calling: " + system.getBillingFire().getCustomerContact() + ", " + system.getBillingFire().getContactNumber().get(0) + ", " + system.getBillingFire().getContactNumber().get(1) + " at " + currentTime.toString());
					JOptionPane.showMessageDialog(null, msg);
					display.FirerDetected(sensorList.getSelectedItem().toString());
					system.callContacts("FIRE");
					while(!passwordCheck) {
						passwordThread.start();
						Thread aWorker = new Thread() {
						 
				         public void run(){       // Report the result using invokeLater()

				         SwingUtilities.invokeLater( new Runnable(){
				           
				           public void run(){
				        	   display.SprinklerTurnOn();
				           }
				          });// End of SwingUtilities.invokeLater
				         }
				         };// anonymous-class for aWorker

				          aWorker.start();  // So we don�t hold up the event dispatch thread

					
					JTextField jpassword = new JPasswordField();
			        Object[] ob = {jpassword};
					 int result = JOptionPane.showConfirmDialog(null, ob, "Please input password for JOptionPane showConfirmDialog", JOptionPane.OK_CANCEL_OPTION);
					String whatTheUserEntered = jpassword.getText();
	
					
					BufferedReader userReader = null;
					String userId = null, userName = null, password = null;
					try {
						userReader = new BufferedReader(new FileReader("user.txt"));
						String line;
						while ((line = userReader.readLine()) != null) {

							String tokens[] = line.split("\\*");
							userId = tokens[0];
							userName = tokens[1];
							password = tokens[2];

							// hashing password
							HashPassword hp = new HashPassword();
							String enPass = null;
							try {
								enPass = hp.hashPassword(whatTheUserEntered);
							} catch (NoSuchAlgorithmException e3) {
								// TODO Auto-generated catch block
								e3.printStackTrace();
							}
							if (enPass.equals(password)) {

								frame.dispose();
								display.SprinklerTurnOff();
								display.FireCaught();
								passwordCheck = true;
								
								JOptionPane.showMessageDialog(null, "Alarm is off");

							}
						}
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
					}

				}

			}
		});

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public void SimulateIntruder(AlarmSystem system) {
		currentTime = LocalTime.now();
		JFrame frame = new JFrame("Simulator");
		JPanel rootPanel = new OpaquePanel();
		frame.add(rootPanel);

		rootPanel.setLayout(new GridLayout(2, 1));
		JPanel listPanel = new JPanel();
		JPanel closePanel = new JPanel();

		JButton testButton = new JButton("Test");
		JButton closeButton = new JButton("Close");

		rootPanel.add(listPanel);
		rootPanel.add(closePanel);
		// populating sensorList from file
		BufferedReader userReader = null;
		String location = null;

		try {
			userReader = new BufferedReader(new FileReader("sensors.txt"));
			String line;
			while ((line = userReader.readLine()) != null) {
				String tokens[] = line.split("\\*");
				location = tokens[3];
				sensorLocation = location;
				if (tokens[1].equals("M") || tokens[1].equals("B")) {
					listSensors.add(location);
					sensorId = tokens[2];
				}
			}
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

		String[] locationList = listSensors.toArray(new String[listSensors.size()]);
		sensorList = new JComboBox<>(locationList);
		listPanel.add(sensorList);

		closePanel.add(testButton);
		closePanel.add(closeButton);

		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listSensors.clear();
				frame.dispose();
				display.IntruderCaught();
			}
		});

		testButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Thread passwordThread = new Thread() {
					public void run() {
						//Call Costumer
						try {
							
							Thread.sleep(10000);
							if(!passwordCheck)
							{
								System.out.println("CALLING EMERGNY");
								system.callMonitoringService("INTRUDER");
								String msg = new String("Calling Emergency");
								JOptionPane.showMessageDialog(null, msg);
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch blockca
							e.printStackTrace();
						}
						SwingUtilities.invokeLater(new Runnable() {

							public void run() {
							
							
							

							}
						});// End of SwingUtilities.invokeLater
					}
				};
				if (system.checkMotionSensors(sensorList.getSelectedItem().toString())) {
					display.IntruderDetected(sensorList.getSelectedItem().toString());
					system.callContacts("INTRUDER");
					String msg = new String("Intrusion" + "| Calling: " + system.getBillingIntrusion().getCustomerContact() + ", " + system.getBillingIntrusion().getContactNumber().get(0) + ", " + system.getBillingIntrusion().getContactNumber().get(1) + " at " + currentTime.toString());
					JOptionPane.showMessageDialog(null, msg);
					while (!passwordCheck) {
						passwordThread.start();
					JTextField jpassword = new JPasswordField();
			        Object[] ob = {jpassword};
					 int result = JOptionPane.showConfirmDialog(null, ob, "Please input password for JOptionPane showConfirmDialog", JOptionPane.OK_CANCEL_OPTION);
					String whatTheUserEntered = jpassword.getText();

					BufferedReader userReader = null;
					String userId = null, userName = null, password = null;
					try {
						userReader = new BufferedReader(new FileReader("user.txt"));
						String line;
						while ((line = userReader.readLine()) != null) {

							String tokens[] = line.split("\\*");
							userId = tokens[0];
							userName = tokens[1];
							password = tokens[2];

							// hashing password
							HashPassword hp = new HashPassword();
							String enPass = null;
							try {
								enPass = hp.hashPassword(whatTheUserEntered);
							} catch (NoSuchAlgorithmException e3) {
								// TODO Auto-generated catch block
								e3.printStackTrace();
							}
							if (enPass.equals(password)) {

								frame.dispose();
								display.IntruderCaught();
								passwordCheck = true;
								JOptionPane.showMessageDialog(null, "Alarm is off");

							}
						}
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
					}

				}

			}
		});

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
