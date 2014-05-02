package shooter.model;

import java.util.ArrayList;
import java.util.List;
import shooter.util.Color;
import shooter.util.Rectangle;
import shooter.util.ShotBuilder;

public class ShipModel {
	protected ShotBuilder builder = new ShotBuilder(this).setSpeed(-0.3);
	protected Rectangle location = new Rectangle(0.5, 1-0.05, 0.05, 0.05);
	protected List<ShotModel> shotsFired = new ArrayList<>();
	protected double speed = 0.15;
	protected int maxShots = 3; //Integer.MAX_VALUE;
	protected double reloadRate = 0.4;
	protected double lastFired = 0;
	protected double time = 0;
	protected Color color = Color.red;
	
	public ShotBuilder getBuilder() {
		return builder;
	}
	
	public double getSpeed() {
		return speed;
	}

	public void addShot(ShotModel shot) {
		lastFired = time;
		shotsFired.add(shot);
	}
	
	public Color getColor() {
		return color;
	}

	public void explode() {
		
	}
		
	public void update(double seconds) {
		time += seconds;
		List<ShotModel> removeList = new ArrayList<>();
		for(ShotModel shot : shotsFired) {
			if(shot.isOutOfBounds()) {
				removeList.add(shot);
			}
		}
		for(ShotModel shot : removeList) {
			shotsFired.remove(shot);
		}
	}
	
	public boolean canShoot() {
		return (maxShots > shotsFired.size())
				&& (time > reloadRate + lastFired);
	}
	
	public void setLocation(double x, double y, double width, double height) {
		location.width = width;
		location.height = height;
		location.x = x;
		location.y = y;
	}
	
	public void setLocation(Rectangle r) {
		location.width = r.width;
		location.height = r.height;
		location.x = r.x;
		location.y = r.y;
	}

	public Rectangle getLocation() {
		return location;
	}
}
