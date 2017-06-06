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
import java.io.IOException;

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

	public ControlPanelGUI() throws IOException {
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

		JToggleButton jtb = new JToggleButton("Turn On");
		// jtb.setBackground(Color.red);

		JPasswordField passwordTextField = new JPasswordField(15);
		JLabel scheduleLabel = new JLabel("Schedule:");
		scheduleLabel.setBackground(Color.WHITE);

		// JLabel currentStateLabel = new JLabel("System has not started yet");

		topPanel.add(passwordTextField);
		topPanel.add(jtb);

		JPanel schedulePanel = new OpaquePanel();
		JPanel monitorPanel = new OpaquePanel();
		middlePanel.add(schedulePanel);
		middlePanel.add(monitorPanel);

		schedulePanel.add(scheduleLabel);

		JButton setScheduleButton = new JButton("Set");
		JButton monitorButton = new JButton("Monitor");
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

		monitorPanel.add(monitorButton);

		rootContainer.add(bottoPanel);

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

				} else if (setTimeRadioaButton.isSelected()) {
					SetTime setTime = new SetTime();

				} else {
					JOptionPane.showMessageDialog(null, "Please select a schedule mode");

				}
			}
		});
		// bottoPanel.add(currentStateLabel);
		jtb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (passwordTextField.getText().equals("123")) {
					if (ev.getStateChange() == ItemEvent.SELECTED) {

						Thread aWorker = new Thread() {

							public void run() {

								SwingUtilities.invokeLater(new Runnable() {

									public void run() {
										jtb.setText("Turn Off");
										passwordTextField.setText("");
										ColorDisplay colorDisplay = new ColorDisplay();
										
									}
								});// End of SwingUtilities.invokeLater
							}
						};// anonymous-class for aWorker

						aWorker.start(); // So we don�t hold up the event

					} else if (ev.getStateChange() == ItemEvent.DESELECTED) {
						jtb.setText("Turn On");
						// jtb.setBackground(Color.red);
						// currentStateLabel.setText("Stopped");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Password doesn't match");
				}
			}
		});

		// frame.setSize(700, 700);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
