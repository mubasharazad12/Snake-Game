import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Stage  extends GamePanel{
	ArrayList<Poisness> apple2;
	Regular_Food apple;
	int sec = 0;
	Timer time = new Timer();
	boolean timerrunning = false;
	
	TimerTask task = new TimerTask() {

		@Override
		public void run() {
			 sec++;
			
		}
		
	};
	
	public void start() {
		time.scheduleAtFixedRate(task, 1000, 1000);
		timerrunning = true;
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
		apple2 = new ArrayList<Poisness>();
		setApple();
		score = 0; 
		gameover = false;
		level = 1;
		setFPS(level * 10);
		sec = 0;
		
		if(!timerrunning) {
			start();
		}
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
	
	public void setApple2(int a) {
		apple2x = (int)(Math.random() * (WIDTH - SIZE));
		apple2y = (int)(Math.random() * (WIDTH - SIZE));
		apple2x = apple2x - (apple2x % SIZE);
		apple2y = apple2y - (apple2y % SIZE);
		
//		apple2.setPosition(apple2x, apple2y);
		apple2.get(a).setPosition(apple2x, apple2y);
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
				setFPS(level * 10);
				
			}
		}
		
		if(head.gety() == 0 || head.getx() == 0 || head.getx() == 400 || head.gety() == 400) {
			gameover = true;
		}
		
		
		if(timecopyscore == 4) {
			for(int a = 0 ; a < 8 ; a++) {
				Poisness apple = new Poisness();
				apple2.add(apple);
				setApple2(a);
				System.out.println("apple " + a +  " is at " + apple2.get(a).x + " and " + apple2.get(a).y);
				
			}
			
			timecopyscore = 0;
		}
		
		for(int a = 0 ;a < apple2.size() ; a++) {
			if(head.getx() == apple2.get(a).x && head.gety() == apple2.get(a).y) {
					gameover = true;
				}
		}

		if(head.getx() < 0) {head.setx(WIDTH);}
		if(head.getx() < 0) {head.sety(HEIGHT);}
		if(head.getx() > WIDTH) {head.setx(0);}
		if(head.gety() > HEIGHT) {head.sety(0);}
	}
	
	public void render(Graphics2D g) {
		g2d.clearRect(0, 0, WIDTH, HEIGHT);
		g2d.setColor(Color.GREEN);
		for(Snake e : snake) {
			e.render(g2d);
		}
		
		apple.render(g2d);
		
		for(Poisness a : apple2) {
			a.render(g2d);
		}
		if(gameover) {
			g2d.drawString("Game over" , 150 , 200);
			
		}else {
			g2d.drawString("Timer is =  " + sec ,  300 , 10);
		}	
		if(sec == 60) {
			g2d.drawString("time up",150 , 250);
			gameover = true;
		}
		g2d.setColor(Color.WHITE);
		g2d.drawString("Score is :  " + score + " level is " + level, 10, 10);
		if(dx == 0 && dy == 0) {
			g2d.drawString("ready", 150, 200);
		}
	}
	
	
}
