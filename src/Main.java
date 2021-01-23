import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
public class Main extends JFrame /*implements ActionListener*/{
	
	JButton playArcade , playClasic , exit;
	
	public Main() {
		setLayout(null);
		this.setSize(400 , 400);
		this.setVisible(true);
		playArcade = new JButton("playArcade");
		playClasic = new JButton("playClasic");
		exit = new JButton("exit");
		playArcade.setBounds(100 , 100 , 150 , 50);
		playClasic.setBounds(100 , 150 , 150 , 50);
		exit.setBounds(100 , 200 , 150 , 50);
		Handler h = new Handler();
		playArcade.addActionListener(h);
		playClasic.addActionListener(h);
		exit.addActionListener(h);
		add(playArcade);
		add(playClasic);
		add(exit);
	}
	
	
	
	
	
	public static void main(String args[]) {
		Main m = new Main();
		
		
	}


	}
	
	class Handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "playArcade") {
				MainGameOption mgp = new MainGameOption();
				mgp.StartRegularGame();
			
			
		}else if(e.getActionCommand() == "playClasic") {
			MainGameOption mgo = new MainGameOption();
			mgo.TimeLaps();
			
		}else {
			MainGameOption mgo = new MainGameOption();
			mgo.Exit();
			
		}
			
		}
		
	
	
}




