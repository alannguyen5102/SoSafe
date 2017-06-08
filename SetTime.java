package sosafesystems;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SetTime {
	
	ArrayList<String> timeArray = new ArrayList<>();
	public SetTime() {
 
		JFrame frame = new JFrame("So-Safe: Set time");
		JPanel rootPanel = new OpaquePanel();
		frame.add(rootPanel);
		JPanel topPanel = new OpaquePanel();
		JPanel toTimePanel = new OpaquePanel();
		JPanel fromTimePanel = new OpaquePanel();
		JPanel closePanel = new OpaquePanel();
		
		rootPanel.setLayout(new GridLayout(4, 1));
		rootPanel.add(topPanel);
		rootPanel.add(toTimePanel);
		rootPanel.add(fromTimePanel);
		rootPanel.add(closePanel);
		
	
		timeArray.add("00:00");timeArray.add("01:00");timeArray.add("02:00");timeArray.add("03:00");timeArray.add("04:00");
		timeArray.add("05:00");timeArray.add("06:00");timeArray.add("07:00");timeArray.add("08:00");timeArray.add("09:00");
		timeArray.add("10:00");timeArray.add("11:00");timeArray.add("12:00");timeArray.add("13:00");timeArray.add("14:00");
		timeArray.add("15:00");timeArray.add("16:00");timeArray.add("17:00");timeArray.add("18:00");timeArray.add("19:00");
		timeArray.add("20:00");timeArray.add("21:00");timeArray.add("22:00");timeArray.add("23:00");


		JComboBox<String> toTimeCombo;
		JComboBox< String> fromTimeCombo;
		String[] time = timeArray.toArray(new String[timeArray.size()]);
		toTimeCombo = new JComboBox<>(time);
		fromTimeCombo = new JComboBox<>(time);
		
		JLabel toTimeLabel = new JLabel("To:");
		JLabel fromTimeLabel = new JLabel("From:");
		
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
				//save the info in file
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
