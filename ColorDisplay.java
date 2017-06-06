package sosafesystems;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class ColorDisplay{
	Timer timer; 
	Display display; 
	int colorIndex;
	
	public ColorDisplay() {
		JFrame frame = new JFrame("Status");
		
        display = new Display();       
        
        frame.getContentPane().add(display, BorderLayout.CENTER);
        frame.pack();
	    frame.setLocation(100,100);
	    frame.setVisible(true);

	}

	void startAnimation() {
       
 
        if (timer == null) {
        	colorIndex++;  // A number between 0 and 100.
            if (colorIndex > 100)
               colorIndex = 0;
            float hue = colorIndex / 100.0F;  // Between 0.0F and 1.0F.
            display.setColor( Color.getHSBColor(hue,1,1)); 
           timer = new Timer(50, null);
           timer.start();  // Make the time start running.
        }
    }
	
	
	void stopAnimation() {
        // Stop the animation by stopping the timer, unless the
          // animation is not running.

       if (timer != null) {
          timer.stop();   // Stop the timer.
          timer = null;   // Set timer variable to null, so that we
                          //   can tell that the animation isn't running.
       }
    }
}

class Display extends JPanel {
	
	public void setColor(Color color){
		
		setBackground(color);
	}	
}