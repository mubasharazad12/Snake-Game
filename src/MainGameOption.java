import java.awt.Dimension;

import javax.swing.JFrame;

public class MainGameOption extends JFrame {
			GamePanel gp;
			JFrame frame = new JFrame("snake game");
	public void StartRegularGame() {
		
		gp = new GamePanel();
		frame.setContentPane(gp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.pack();
		frame.setPreferredSize(new Dimension(GamePanel.WIDTH  ,GamePanel.HEIGHT));
		frame.setLocationRelativeTo(null);
		
	}
	public void TimeLaps() {
		
		 gp = new Stage();
		frame.setContentPane(gp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.pack();
		frame.setPreferredSize(new Dimension(GamePanel.WIDTH  ,GamePanel.HEIGHT));
		frame.setLocationRelativeTo(null);
	}
	public void Exit() {
		System.exit(0);
	}
	
	
	
	
}
