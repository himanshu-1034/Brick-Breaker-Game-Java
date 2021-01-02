package brickBreakerGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class gamePanel extends JPanel implements KeyListener,ActionListener{

	
	private boolean start = false;
	private int score = 0;
	private int bricks = 21;
	private Timer timer;
	private int delay = 4;
	private int playerX = 310;
	private int ballPosX = 120;
	private int ballPosY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	private tilesGenerator map;
	
	public gamePanel() {
		
		map = new tilesGenerator(3, 7);
		
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
		
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		// background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		//borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		
		
		//score
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(""+score,590,30);
		
		
		// putting tiles
		map.draw((Graphics2D)g);
		
		
		
		// paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		//ball
		g.setColor(Color.yellow);
		g.fillOval(ballPosX, ballPosY, 20, 20);
		
		
		if(bricks<=0) {
			start=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.green);
			g.setFont(new Font("serif",Font.BOLD,35));
			g.drawString("You Won , Score: "+score,190 ,300 );
			
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,25));
			g.drawString("Press Enter To Start",230 ,350 );
		}
		
		
		if(ballPosY>570) {
			start=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,35));
			g.drawString("GAME OVER , Score: "+score,190 ,300 );
			
			
			g.setFont(new Font("serif",Font.BOLD,25));
			g.drawString("Press Enter To Start",230 ,450 );
		}
		
		g.dispose();
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.start();
		
		if(start) {
			
			if(new Rectangle(ballPosX,ballPosY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
				ballYdir = -ballYdir;
			}
			
			A: for(int i=0;i<map.map.length;i++) {
				for(int j=0;j<map.map[0].length;j++) {
					if(map.map[i][j] > 0) {
						int brickx = j*map.brickWidth+80;
						int bricky = i*map.brickHeight+50;
						int brickwidth = map.brickWidth;
						int brickheight = map.brickHeight;
						
						
						Rectangle rect = new Rectangle(brickx,bricky,brickwidth,brickheight);
						Rectangle ballRect = new Rectangle(ballPosX,ballPosY,20,20);
						Rectangle tileRect = rect;
						
						
						if(ballRect.intersects(tileRect)) {
							map.setBrickValue(0,i,j);
							bricks--;
							score+=5;
							delay--;
							if(delay<=1) {
								delay=1;
							}
							
							if(ballPosX+19<=tileRect.x || ballPosX+1 >= tileRect.x+tileRect.width) {
								ballXdir = -ballXdir;
								
							}else {
								ballYdir = -ballYdir;
							}
							
							break A;
						}
						
						
						
					}
				}
			}
			
			
			ballPosX+=ballXdir;
			ballPosY+=ballYdir;
			
			if(ballPosX<0) {
				ballXdir = -ballXdir;
			}
			if(ballPosY<0) {
				ballYdir = -ballYdir;
			}
			if(ballPosX>670) {
				ballXdir = -ballXdir;
			}
		}
		
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(playerX>=600) {
				playerX=600;
			}else {
				moveRight();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(playerX<=10) {
				playerX=10;
			}else {
				moveLeft();
			}
		}
		if(e.getKeyCode()== KeyEvent.VK_ENTER) {
			if(start==false) {
				start = true;
				ballPosX = 120;
				ballPosY = 350;
				ballXdir = -1;
				ballYdir = -2;
				score =0;
				playerX = 310;
				bricks=21;
				map = new tilesGenerator(3,7);
				
				repaint();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void moveRight() {
		start = true;
		playerX+=20;
	}
	
	public void moveLeft() {
		start = true;
		playerX-=20;
	}
	
	
	

}
