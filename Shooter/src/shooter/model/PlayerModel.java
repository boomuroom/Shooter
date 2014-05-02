package shooter.model;

import shooter.util.Color;

public class PlayerModel extends ShipModel {
	
	private int lives = 3;
	
	public PlayerModel() {
		color = Color.green;
		builder.setSpeed(-0.6);
	}
	
	public boolean loseLife() {
		--lives;
		return lives > 0;
	}
	
	public void moveLeft(double seconds) {
		double tmp = location.x - speed * seconds;
		if(tmp < 0) {
			tmp = 0;
		}
		location.x = tmp;
	}
	
	public void moveRight(double seconds) {
		double tmp = location.x + speed * seconds;
		if(tmp +location.width > 1) {
			tmp = 1-location.width;
		}
		location.x = tmp;
	}
	

}
