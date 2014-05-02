package shooter.events;

import shooter.model.GameModel;

public class MovePlayerRightEvent extends Event {
	private GameModel model;
	
	public MovePlayerRightEvent(GameModel model) {
		this.model = model;
	}
	
	@Override
	public void run(double seconds) {
		model.getPlayer().moveRight(seconds);
	}
}
