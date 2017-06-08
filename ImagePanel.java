package sosafesystems;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class ImagePanel extends JPanel {

	  /**
	 *  Class responsible for reading the background image and setting it to background
	 *  sets the window to image size, also handles resizing
	 */
	private static final long serialVersionUID = 1L;
	private Image img;

	  public ImagePanel(String img) throws IOException {
		  this(ImageIO.read(new File("banner.png")));
	  }

	  public ImagePanel(Image img) {
		this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    this.setPreferredSize(size);
	  }

	  public void paintComponent(Graphics g) {
	    g.drawImage(this.img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);

	  }

	}

