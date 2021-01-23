import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public abstract class  Food extends JFrame {
	public int x , y;
	
	/*public Food(int size) {
		this.size = 1000;
	}*/
	
	public int getx() {
		return x;
		}
	
	public int gety() {
		return y;
	}
	
	public void setx(int x) {this.x = x;}
	public void sety(int y) {this.y = y;}
	
	public void setPosition(int x , int y) {
		this.x = x;
		this.y = y;
		
	}
	
	
	abstract public void render(Graphics2D g2d);
	
}
