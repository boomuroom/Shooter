package shooter.model;

import shooter.Display;

public class EnemyModel extends ShipModel {

	private boolean leftMove = false;
	private double cycleTime = 3;
	private double currentCycle = 0;
	private double threshold = 2;
	private double shootTicks = Math.random() * threshold;
	
	public EnemyModel() {
		speed = 0.05;
		builder.setSpeed(0.6);
	}
	
	@Override
	public boolean canShoot() {
		boolean superRes = super.canShoot();
		boolean myRes = shootTicks > threshold;
		boolean res= superRes && myRes;
		if(res) {
			shootTicks -= threshold;
		}
		return res;
	}
	
	@Override
	public void update(double seconds) {
		super.update(seconds);
		shootTicks += Math.random() * seconds;
	}
}
