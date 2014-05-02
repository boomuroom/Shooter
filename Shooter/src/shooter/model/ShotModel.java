package shooter.model;

import shooter.util.Color;
import shooter.util.Rectangle;

public class ShotModel {
	
	protected final ShipModel host;
	protected final Rectangle location = new Rectangle();
	protected double speed;
	protected Color color = Color.cyan;
	public ShotModel(ShipModel host, double speed) {
		this.host = host;
		location.copy(host.getLocation());
		location.width = location.width / 10;
		location.x = location.x + location.width * 4.5;
		this.speed = speed;
	}
	
	public void clear() {
		host.shotsFired.remove(this);
	}
	
	public Color getColor() {
		return color;
	}
	
	public Rectangle getLocation() { 
		return location;
	}
	
	public void move(double seconds) {
		location.y += speed * seconds;
	}
	
	public boolean isOutOfBounds() {
		return location.y > 1
				|| location.getBottom() < 0
				|| location.getRight() < 0
				|| location.x > 1;
	}
}
