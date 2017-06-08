package sosafesystems;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SetTime {

	ArrayList<String> timeArray = new ArrayList<>();
	ArrayList<String> makeSensorArray = new ArrayList<String>();
	ArrayList<String> listSensors = new ArrayList<String>();
	AlarmSystem soSafe;

	public SetTime(AlarmSystem soSafe) {
		this.soSafe = soSafe;
		JFrame frame = new JFrame("So-Safe: Set time");
		JPanel rootPanel = new OpaquePanel();
		frame.add(rootPanel);

		JPanel topPanel = new OpaquePanel();
		JPanel sensorListPanel = new OpaquePanel();
		JPanel toTimePanel = new OpaquePanel();
		JPanel fromTimePanel = new OpaquePanel();
		JPanel closePanel = new OpaquePanel();

		rootPanel.setLayout(new GridLayout(5, 1));
		rootPanel.add(topPanel);
		rootPanel.add(sensorListPanel);
		rootPanel.add(toTimePanel);
		rootPanel.add(fromTimePanel);
		rootPanel.add(closePanel);

		timeArray.add("00:00");
		timeArray.add("01:00");
		timeArray.add("02:00");
		timeArray.add("03:00");
		timeArray.add("04:00");
		timeArray.add("05:00");
		timeArray.add("06:00");
		timeArray.add("07:00");
		timeArray.add("08:00");
		timeArray.add("09:00");
		timeArray.add("10:00");
		timeArray.add("11:00");
		timeArray.add("12:00");
		timeArray.add("13:00");
		timeArray.add("14:00");
		timeArray.add("15:00");
		timeArray.add("16:00");
		timeArray.add("17:00");
		timeArray.add("18:00");
		timeArray.add("19:00");
		timeArray.add("20:00");
		timeArray.add("21:00");
		timeArray.add("22:00");
		timeArray.add("23:00");

		JComboBox<String> sensorList;
		JComboBox<String> toTimeCombo;
		JComboBox<String> fromTimeCombo;

		String[] time = timeArray.toArray(new String[timeArray.size()]);
		toTimeCombo = new JComboBox<>(time);
		fromTimeCombo = new JComboBox<>(time);

		// populating sensorList from file
		BufferedReader userReader = null;
		String location = null;

		try {
			userReader = new BufferedReader(new FileReader("sensors.txt"));
			String line;
			while ((line = userReader.readLine()) != null) {
				String tokens[] = line.split("\\*");
				location = tokens[3];
				listSensors.add(location);
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

		String selectedLocation = (String) sensorList.getSelectedItem();

		// populating sensorList from file
		userReader = null;
		String  userId = null, sensorId = null, mf = null, powerStatus = null, alarmStatus = null,
				manualStatus = null;

		try {
			userReader = new BufferedReader(new FileReader("sensors.txt"));
			String line;
			while ((line = userReader.readLine()) != null) {
				String tokens[] = line.split("\\*");

				userId = tokens[0];
				mf = tokens[1];
				sensorId = tokens[2];
				location = tokens[3];
				powerStatus = tokens[4];
				manualStatus = tokens[5];
				alarmStatus = tokens[6];
				

				if (location.equals(selectedLocation)) {
					makeSensorArray.add(userId);
					makeSensorArray.add("*");
					makeSensorArray.add(mf);
					makeSensorArray.add("*");
					makeSensorArray.add(sensorId);
					makeSensorArray.add("*");
					makeSensorArray.add(location);
					makeSensorArray.add("*");
					makeSensorArray.add(powerStatus);
					makeSensorArray.add("*");
					makeSensorArray.add(manualStatus);
					makeSensorArray.add("*");
					makeSensorArray.add(alarmStatus);
					makeSensorArray.add("*");

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

		JLabel sensorListLabel = new JLabel("Sensors");
		JLabel toTimeLabel = new JLabel("To:");
		JLabel fromTimeLabel = new JLabel("From:");

		sensorListPanel.add(sensorListLabel);
		sensorListPanel.add(sensorList);
		toTimePanel.add(toTimeLabel);
		toTimePanel.add(toTimeCombo);

		fromTimePanel.add(fromTimeLabel);
		fromTimePanel.add(fromTimeCombo);

		JButton doneButton = new JButton("Done");
		JButton closeButton = new JButton("Close");

		closePanel.add(doneButton);
		closePanel.add(closeButton);

		doneButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// save the info in file
				soSafe.setToTime(toTimeCombo.getSelectedItem().toString());
				soSafe.setFromTime(fromTimeCombo.getSelectedItem().toString());

				

				JOptionPane.showMessageDialog(null, "Sensor scheduled");
				makeSensorArray.clear();

				frame.dispose();

			}
		});

		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}
		});
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
