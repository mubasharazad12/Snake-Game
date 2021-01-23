import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Regular_Food extends Food {
	
	ImageIcon img = new ImageIcon("apple.png");
	
	
	public void render(Graphics2D g2d) {
		/*g2d.fillRect(x + 1,  y + 1, size - 2, size - 2);*/
		img.paintIcon(this, g2d, x, y);
		
	}

}
