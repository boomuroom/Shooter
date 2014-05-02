package shooter;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JFrame;
import shooter.Game.FrameListener;
import shooter.implementations.SwingDisplay;

public class Shooter {
	
	private static int LEFT = 1;
	private static int RIGHT = 2;
	private static int SHOOT = 4;
	
	public static void main(String[] args) throws IOException {
		Game g = new Game();
		int[] state = {0};
		Display d = getDisplay(state, g);
		g.addDisplay(d);
		g.setCycleRate(60);
		g.addFrameListener(getListener(state));
		g.start();
	}
	
	private static FrameListener getListener(final int[] state) {
		return new FrameListener() {
			@Override
			public void update(Controls c) {
				if((state[0] & LEFT) == LEFT) {
					c.movePlayerLeft();
				}
				if((state[0] & RIGHT) == RIGHT) {
					c.movePlayerRight();
				}
				if((state[0] & SHOOT) == SHOOT) {
					c.playerShoot();
				}
			}
			
		};
	}
	
	private static Display getDisplay(final int[] state, final Game game) {
		final JFrame jf = new JFrame("Shooter thing");
		jf.addKeyListener(
			new KeyListener() {

				boolean full = true;
				
				@Override
				public void keyTyped(KeyEvent e) { }

				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_LEFT) {
						state[0] |= LEFT;
					}
					if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
						state[0] |= RIGHT;
					}
					if(e.getKeyCode() == KeyEvent.VK_SPACE) {
						state[0] |= SHOOT;
					}
				}

				@Override
				public void keyReleased(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_LEFT) {
						state[0] &= ~LEFT;
					}
					if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
						state[0] &= ~RIGHT;
					}
					if(e.getKeyCode() == KeyEvent.VK_SPACE) {
						state[0] &= ~SHOOT;
					}
					if(e.getKeyCode() == KeyEvent.VK_T) {
						Window frame = (full) ? null : jf;
						GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
						full = !full;
					}
					if(e.getKeyCode() == KeyEvent.VK_R) {
						game.reset();
					}
				}
				
			}
		);
		jf.setBackground(Color.black);
		jf.setIgnoreRepaint(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		jf.createBufferStrategy(3);
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(jf);
		return new SwingDisplay(jf);
	}
}
