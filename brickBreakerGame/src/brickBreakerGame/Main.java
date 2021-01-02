package brickBreakerGame;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame object = new JFrame();
		gamePanel gamePanel = new gamePanel();
		object.setBounds(10,10,710,600);
		object.setResizable(false);
		object.setTitle("JAVA GAME");
		
		object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		object.add(gamePanel);
		object.setVisible(true);

	}

}
