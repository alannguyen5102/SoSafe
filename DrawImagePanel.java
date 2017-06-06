package sosafesystems;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class DrawImagePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image image;
	public void setBackground(Image image){
		this.image = image;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
}
