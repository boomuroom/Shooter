package shooter.ai;

import java.util.ArrayList;
import java.util.List;
import shooter.model.ShipModel;

/**
 *
 * @author Steffen
 */
public class BasicAI implements AIHandler {
	private List<ShipModel> ships = new ArrayList<>();
	private boolean leftMove = false;

	@Override
	public void update(double seconds) {
		double timeRemaining = getTimeRemaining();
		if(seconds > timeRemaining) {
			double endHalf = seconds - timeRemaining;
			move(timeRemaining);
			shiftDown();
			move(endHalf);
		} else {
			move(seconds);
		}
	}
	
	private void shiftDown() {
		leftMove = !leftMove;
		for(ShipModel ship : ships) {
			ship.getLocation().y += 0.03;
		}
	}
	
	private void move(double seconds) {
		for(ShipModel ship : ships) {
			double dist = (leftMove) ? -ship.getSpeed() * seconds : ship.getSpeed() * seconds;
			ship.getLocation().x += dist;
		}
	}
	
	private double getTimeRemaining() {
		double timeRemaining = 1000;
		for(ShipModel ship : ships) {
			double myTimeRemaining = 1000;
			if(leftMove) {
				double left = ship.getLocation().getLeft();
				double speed = ship.getSpeed();
				myTimeRemaining = left/speed;
			} else {
				double right = ship.getLocation().getRight();
				double speed = ship.getSpeed();
				myTimeRemaining = (1-right)/speed;
			}
			if(myTimeRemaining < timeRemaining) {
				timeRemaining = myTimeRemaining;
			}
		}
		return timeRemaining;
	}

	@Override
	public void add(ShipModel ship) {
		ships.add(ship);
	}

	@Override
	public void remove(ShipModel ship) {
		ships.remove(ship);
	}
}
