package shooter.util;

import shooter.model.ShipModel;
import shooter.model.ShotModel;

public class ShotBuilder {
	public enum Type { BASIC };
	
	private ShipModel host;
	private Type type = Type.BASIC;
	private Rectangle location = new Rectangle(0.5, 1-0.05, 0.05, 0.05);
	private double speed = 1.0;
	
	public ShotBuilder(ShipModel host) {
		this.host = host;
	}

	public ShotBuilder setType(Type type) {
		this.type = type;
		return this;
	}
	
	public ShotBuilder setLocation(Rectangle r) {
		location.height = r.height;
		location.width = r.width;
		location.x = r.x;
		location.y = r.y;
		return this;
	}
	
	public ShotBuilder setLocation(double x, double y, double width, double height) {
		location.height = height;
		location.width = width;
		location.x = x;
		location.y = y;
		return this;
	}

	public ShotBuilder setSpeed(double speed) {
		this.speed = speed;
		return this;
	}
	
	public ShotModel build() {
		if(host.canShoot()) {
			ShotModel res;
			switch(type) {
				case BASIC:
				default:
					res = new ShotModel(host, speed);
			}
			host.addShot(res);
			return res;
		} else {
			return null;
		}
	}
}
