package sosafesystems;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.OverlayLayout;
import javax.swing.SwingUtilities;

public class ControlPanelGUI {
	Thread aWorker;
	String userId = null, userName = null, password = null, hsPass = null;
	Display display = new Display();

	Simulator simulate = new Simulator();
	AlarmSystem soSafe;
	LocalTime fromTime = LocalTime.MIN;
	LocalTime toTime = LocalTime.MAX;
	  
	public ControlPanelGUI(AlarmSystem soSafe) throws IOException, NoSuchAlgorithmException {
		this.soSafe = soSafe;

		JFrame frame = new JFrame("SoSafe Security System: Control Panel");
		LayoutManager overlay = new OverlayLayout(frame.getContentPane());
		frame.getContentPane().setLayout(overlay);

		ImagePanel imagePanel = new ImagePanel("banner.png");
		imagePanel.setLocation(0, 0);

		JPanel rootContainer = new OpaquePanel();
		rootContainer.setBackground(new Color(0, 0, 0, 1));

		rootContainer.setLayout(new GridLayout(3, 1));
		JPanel topPanel = new OpaquePanel();
		JPanel middlePanel = new OpaquePanel();
		middlePanel.setLayout(new FlowLayout());
		JPanel bottoPanel = new OpaquePanel();

		frame.add(rootContainer);
		frame.add(imagePanel);

		rootContainer.add(topPanel);
		rootContainer.add(middlePanel);
		rootContainer.add(bottoPanel);

		middlePanel.setLayout(new GridLayout(2, 1));
		JToggleButton jtb = new JToggleButton("Turn On");
		// jtb.setBackground(Color.red);

		JPasswordField passwordTextField = new JPasswordField(15);
		JLabel scheduleLabel = new JLabel("Schedule:");
		scheduleLabel.setForeground(Color.WHITE);

		// JLabel currentStateLabel = new JLabel("System has not started yet");

		topPanel.add(passwordTextField);
		topPanel.add(jtb);

		JPanel schedulePanel = new OpaquePanel();
		JPanel monitorPanel = new OpaquePanel();

		middlePanel.add(schedulePanel);
		middlePanel.add(monitorPanel);

		schedulePanel.add(scheduleLabel);

		JButton setScheduleButton = new JButton("Set");
		JButton itrutionButton = new JButton("Simulate Intrution");
		JButton fireButton = new JButton("Simulate Fire");
		JButton configureButton = new JButton("Re-Configure");

		JRadioButton setAlwaysRadioButtion = new JRadioButton("Always");
		JRadioButton setTimeRadioaButton = new JRadioButton("Set time");
		setAlwaysRadioButtion.setBounds(75, 50, 100, 30);
		setTimeRadioaButton.setBounds(75, 100, 100, 30);

		ButtonGroup buttonGroupRadio = new ButtonGroup();
		buttonGroupRadio.add(setAlwaysRadioButtion);
		buttonGroupRadio.add(setTimeRadioaButton);

		schedulePanel.add(setAlwaysRadioButtion);
		schedulePanel.add(setTimeRadioaButton);
		schedulePanel.add(setScheduleButton);

		monitorPanel.add(configureButton);
		monitorPanel.add(itrutionButton);
		monitorPanel.add(fireButton);
		
		HashPassword hsp = new HashPassword();

		// retrieving password for validation
		BufferedReader userReader = null;
		try {
			userReader = new BufferedReader(new FileReader("user.txt"));
			String line;
			while ((line = userReader.readLine()) != null) {

				String tokens[] = line.split("\\*");
				userId = tokens[0];
				userName = tokens[1];
				password = tokens[2];

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



		itrutionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				aWorker = new Thread() {

					public void run() {

						SwingUtilities.invokeLater(new Runnable() {

							public void run() {
								if(jtb.isSelected())
								{
									simulate.SimulateIntruder(soSafe);
									

								}
								else 
								{
									JOptionPane.showMessageDialog(null, "Alarm system offline");
								}

							}
						});// End of SwingUtilities.invokeLater
					}
				};// anonymous-class for aWorker

				aWorker.start(); // So we don’t hold up the event	
				
			}
		});
		
		fireButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aWorker = new Thread() {

					public void run() {

						SwingUtilities.invokeLater(new Runnable() {

							public void run() {
								if(jtb.isSelected())
								{
									simulate.SimulateFire(soSafe);

								}
								else 
								{
									JOptionPane.showMessageDialog(null, "Alarm system offline");
								}

							}
						});// End of SwingUtilities.invokeLater
					}
				};// anonymous-class for aWorker

				aWorker.start(); // So we don’t hold up the event	
				
			}			
		});
		
		rootContainer.add(bottoPanel);
		JButton billButton = new JButton("Billing");
		
		
		bottoPanel.add(billButton);
		
		billButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JButton closeButton = new JButton("Close");
		bottoPanel.add(closeButton);
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					LogInWindow lg = new LogInWindow();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		setScheduleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (setAlwaysRadioButtion.isSelected()) {
					JOptionPane.showMessageDialog(null, "Always selected");
					soSafe.setFromTime("00:00");
					soSafe.setToTime("23:59:59.999999999");
					
					//by default set to always
					//no need to save in file

				} else if (setTimeRadioaButton.isSelected()) {
					SetTime setTime = new SetTime(soSafe);

				} else {
					JOptionPane.showMessageDialog(null, "Please select a schedule mode");

				}
			}
			
		});
		
		configureButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

		
				Password pass = new Password(password);
				

			}
		});

		jtb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				try {
					//encripted password
					hsPass = hsp.hashPassword(passwordTextField.getText());
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (hsPass.equals(password)) {
					if (ev.getStateChange() == ItemEvent.SELECTED) {

						aWorker = new Thread() {

							public void run() {

								SwingUtilities.invokeLater(new Runnable() {

									public void run() {
										jtb.setText("Turn Off");
										passwordTextField.setText("");
										display.Secured();

									}
								});// End of SwingUtilities.invokeLater
							}
						};// anonymous-class for aWorker

						aWorker.start(); // So we don’t hold up the event

					} else if (ev.getStateChange() == ItemEvent.DESELECTED) {
						jtb.setText("Turn On");
						display.Unsecured();
						passwordTextField.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Password doesn't match");
					passwordTextField.setText("");
				}
			}
		});

		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
