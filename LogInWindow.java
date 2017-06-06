package sosafesystems;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;

public class LogInWindow implements ActionListener {

	public LogInWindow() throws IOException {
		
		JFrame frame = new JFrame("SoSafe Security System");
	    LayoutManager overlay = new OverlayLayout(frame.getContentPane());
	    frame.getContentPane().setLayout(overlay);
	    
	    ImagePanel imagePanel = new ImagePanel("banner.png");
	    imagePanel.setLocation(0,0);

		JPanel rootContainer = new OpaquePanel();
	    frame.add(rootContainer);
	    frame.add(imagePanel);

	    
	    rootContainer.setLocation(0, 0);
	    rootContainer.setBackground(new Color(0, 0, 0, 1));
        rootContainer.setLayout(new GridLayout(4, 1));
        
        //creating 3 panels
        JPanel topPanel = new OpaquePanel();
        JPanel topTwoPanel = new OpaquePanel();
        JPanel middlePanel = new OpaquePanel();
        JPanel bottomPanel = new OpaquePanel();
        
        //adding 3 panels to rootContainer
        rootContainer.add(topPanel);
        rootContainer.add(topTwoPanel);
        rootContainer.add(middlePanel);
        rootContainer.add(bottomPanel);
        
        JPanel mUserPanel = new OpaquePanel();
        JPanel mPassPanel = new OpaquePanel();
        JPanel loginPanel = new OpaquePanel();
        
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.add(mUserPanel);
        middlePanel.add(mPassPanel);
        middlePanel.add(loginPanel);
        
        JLabel userNameLabel = new JLabel("User Name:");
        userNameLabel.setForeground(Color.white);
        JTextField userNameTextField = new JTextField(15);
        

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.white);
        JTextField passwordTextField = new JTextField(15);
        
        JButton loginButton = new JButton("Login");
        mUserPanel.add(userNameLabel);
        mUserPanel.add(userNameTextField);
        
        mPassPanel.add(passwordLabel);
        mPassPanel.add(passwordTextField);
        
        loginPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//if username pass matches
				try {
					ControlPanelGUI cpGUI = new ControlPanelGUI();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
				
			}
		});
        
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        JPanel signupPanel = new OpaquePanel();
        JPanel closePanel = new OpaquePanel();
        
        bottomPanel.add(signupPanel);
        bottomPanel.add(closePanel);
        
        JLabel signupLabel = new JLabel("Not registered yet?");
        signupLabel.setForeground(Color.white);
        JButton signupButton = new JButton("Sign Up Now");
        
        signupPanel.add(signupLabel);
        signupPanel.add(signupButton);
        
        signupButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ConfigureWindow cw = new ConfigureWindow();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
				
			}
		});
        
        JButton closeButton = new JButton("Close");
        closePanel.add(closeButton);
        closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
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