package sosafesystems;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Simulator {
	ArrayList<String> listSensors = new ArrayList<String>();
	JComboBox<String> sensorList;
	Display display = new Display();
	String sensorId = null;
	String sensorLocation = null;


	public void SimulateFire(AlarmSystem system) {
		
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
				if(tokens[1].equals("F"))
				{
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
				display.FireCaught();
				frame.dispose();
			}
		});
		
		
		testButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (system.checkTemperatureSensors(sensorList.getSelectedItem().toString())) {
				display.FirerDetected(sensorList.getSelectedItem().toString());
				
				JOptionPane.showInputDialog("Give Master Password");
				}

				
			}
		});

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
	
public void SimulateIntruder(AlarmSystem system) {
		
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
				if(tokens[1].equals("M"))
				{
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
				if (system.checkMotionSensors(sensorList.getSelectedItem().toString())) {
				display.IntruderDetected(sensorList.getSelectedItem().toString());
				JOptionPane.showInputDialog("Give Master Password");
				}

				
			}
		});
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		
		
		
	}

}
