package shooter.events;

import shooter.model.GameModel;

public class MovePlayerLeftEvent extends Event {
	
	private GameModel model;
	
	public MovePlayerLeftEvent(GameModel model) {
		this.model = model;
	}
	
	@Override
	public void run(double seconds) {
		model.getPlayer().moveLeft(seconds);
	}
}
