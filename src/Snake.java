import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Snake {
	private int x , y , size;
	public Snake(int size) {
		this.size = size;
	}
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
	public void move(int dx , int dy) {
		x += dx;
		y += dy;
		
	}
	public Rectangle getBound() {
		return new Rectangle(x , y , size , size);	
	}
	
	public boolean isCollision(Snake o) {
		if(o == this ) {
			return false;
		}
		return getBound().intersects(o.getBound());
	}
	public void render(Graphics2D g2d) {
		g2d.fillRect(x  ,  y   , size - 2, size - 2);
	}
	
}
