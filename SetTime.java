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
		
	
		timeArray.add("00");timeArray.add("01");timeArray.add("02");timeArray.add("03");timeArray.add("04");
		timeArray.add("05");timeArray.add("06");timeArray.add("07");timeArray.add("08");timeArray.add("09");
		timeArray.add("10");timeArray.add("11");timeArray.add("12");timeArray.add("13");timeArray.add("14");
		timeArray.add("15");timeArray.add("16");timeArray.add("17");timeArray.add("18");timeArray.add("19");
		timeArray.add("20");timeArray.add("21");timeArray.add("22");timeArray.add("23");


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
