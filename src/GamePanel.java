import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener , Runnable{
	
	ImageIcon map = new ImageIcon("grass.jpg");
	Food apple;
	Food apple2;
	int timecopyscore;
	int applex , appley;
	int apple2x , apple2y;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;
	//renering
	public Graphics2D g2d;
	public BufferedImage image ;
	//Game loop
	public Thread thread ;
	public boolean running;
	public long targettime;
	
	//game stuff
	public final int SIZE = 10;
	public Snake head ;// apple;
	public ArrayList<Snake> snake;
	public int score;
	public int level;
	public boolean gameover;
	// move
	public int dx , dy;
	//key events
	public boolean up,down,left,right,start;
	
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH , HEIGHT));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
	}
	
	public void addNotify(){
		super.addNotify();
		thread = new Thread(this);
		thread.start();
		
	}
	
	public void setFPS(int fps) {
		targettime = 1000/fps;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_UP) up = true;
		if(k == KeyEvent.VK_DOWN) down = true;
		if(k == KeyEvent.VK_LEFT) left = true;
		if(k == KeyEvent.VK_RIGHT) right = true;
		if(k == KeyEvent.VK_ENTER) start = true;
		
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_UP) up = false;
		if(k == KeyEvent.VK_DOWN) down = false;
		if(k == KeyEvent.VK_LEFT) left = false;
		if(k == KeyEvent.VK_RIGHT) right = false;
		if(k == KeyEvent.VK_ENTER) start = false;
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}

	@Override
	public void run() {
			if(running) {
				return;
			}
			init();
			long startTime;
			long elapsed;
			long wait;
			while(running) {
				startTime = System.nanoTime();
				update();
				requestrender();
				elapsed = System.nanoTime() - startTime;
				wait = targettime - elapsed / 100000 ;
				if (wait > 0) {
					try {
						Thread.sleep(wait);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		
	}

	public void init() {
		image = new BufferedImage(WIDTH , HEIGHT , BufferedImage.TYPE_INT_BGR);
		g2d = image.createGraphics();
		running = true;
		setUpLevel();
		
	}
	
	public void setUpLevel() {
		snake = new ArrayList<Snake>();
		head = new Snake(SIZE);
		head.setPosition(WIDTH/2, HEIGHT/2);
		snake.add(head);
		for(int i = 1 ; i < 3 ; i++) {
			Snake e = new Snake(SIZE);
			e.setPosition(head.getx() + (i * SIZE) , head.gety());
			snake.add(e);
		}
		//apple = new Entity(SIZE);
		
		apple = new Regular_Food();
		apple2 = new Poisness();
		
		setApple();
		//setApple2();
		score = 0; 
		gameover = false;
		level = 1;
		setFPS(level * 10);
	}
	
	public void setApple() {
		applex = (int)(Math.random() * (350 - 1));
		appley = (int)(Math.random() * (350 - 1));
		System.out.println(" x" + applex + " y " +  appley);
		applex = applex - (applex % SIZE);
		appley = appley - (appley % SIZE);
		//System.out.println(" x" + applex + " y " +  appley);
		while(applex < 10 || applex > 380 || appley < 10 || applex > 380) {
			applex = (int)(Math.random() * (350 - 1));
			appley = (int)(Math.random() * (350 - 1));
			System.out.println(" x" + applex + " y " +  appley);
			applex = applex - (applex % SIZE);
			appley = appley - (appley % SIZE);
		}
		
		apple.setPosition(applex, appley);
		
	}
	
	
	public void setApple2() {
		apple2x = (int)(Math.random() * (WIDTH - SIZE));
		apple2y = (int)(Math.random() * (WIDTH - SIZE));
		apple2x = apple2x - (apple2x % SIZE);
		apple2y = apple2y - (apple2y % SIZE);
		
		apple2.setPosition(apple2x, apple2y);
		
	}
	
	
	public void requestrender() {
		// TODO Auto-generated method stub
		render(g2d);
		Graphics g = getGraphics();
		g.drawImage(image , 0 , 0, null);
		g.dispose();
		
	}

	public void update() {
		if(gameover) {
			if(start) {
				setUpLevel();
			}
			return;
		}
		if (up && dy == 0) {
			dy = -SIZE;
			dx = 0;
		}if(down && dy == 0) {
			dy = SIZE;
			dx = 0;
		}if(left && dx == 0) {
			dy = 0;
			dx = -SIZE;
		}if(right && dx == 0 && dy != 0) {
			dy = 0;
			dx = SIZE;
		}
		if(dx != 0 || dy != 0) {
			for (int i = snake.size() - 1; i > 0 ; i--) {
				snake.get(i).setPosition(snake.get(i - 1).getx(), snake.get(i - 1).gety());
			}
			head.move(dx, dy);
		}
		for (Snake e : snake) {
			if (e.isCollision(head)) {
				gameover = true;
				break;
			}
		}
		
		
		
		if((head.getx() == applex) && head.gety() == appley) {
			score++;
			timecopyscore++;
			setApple();
			Snake e = new Snake(SIZE);
			e.setPosition(-100 , -100);
			snake.add(e);
			if(score % 10 == 0) {
				level++;
				if (level > 10) {
					level = 10;
					
				}
				setFPS(level += 10);
			}
			
		}
		if(head.gety() == 0 || head.getx() == 0 || head.getx() == 400 || head.gety() == 400) {
			gameover = true;
		}
		
		
		if(timecopyscore == 4) {
			setApple2();
			timecopyscore = 0;
		}
		
		if(head.getx() == apple2x && head.gety() == apple2y) {
			gameover = true;
			
		}

		if(head.getx() < 0) {head.setx(WIDTH);}
		if(head.getx() < 0) {head.sety(HEIGHT);}
		if(head.getx() > WIDTH) {head.setx(0);}
		if(head.gety() > HEIGHT) {head.sety(0);}
		
	}
	public void render(Graphics2D g) {
		g2d.clearRect(0, 0, WIDTH, HEIGHT);
		g2d.setColor(Color.red);
		for(Snake e : snake) {
			e.render(g2d);
		}
		apple.render(g2d);
		
		apple2.render(g2d);
		
		
		if(gameover) {
			g2d.drawString("Game over" , 150 , 200);
			
			
		}
		g2d.setColor(Color.WHITE);
		g2d.drawString("Score is :  " + score + " level is " + level, 10, 10);
		if(dx == 0 && dy == 0) {
			g2d.drawString("ready " , 150, 200);
		}
	}
}
