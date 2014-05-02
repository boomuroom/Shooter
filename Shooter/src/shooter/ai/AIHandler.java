package shooter.ai;

import shooter.model.ShipModel;

/**
 *
 * @author Steffen
 */
public interface AIHandler {
	public void update(double seconds);
	public void add(ShipModel ship);
	public void remove(ShipModel ship);
}
