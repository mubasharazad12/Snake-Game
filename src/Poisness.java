import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Poisness extends Food {
	ImageIcon img = new ImageIcon("enemy2.png");
	
	
	public void render(Graphics2D g2d) {
		/*g2d.fillRect(x + 1,  y + 1, size - 2, size - 2);*/
		img.paintIcon(this , g2d , x, y);
		
	}
	
}
