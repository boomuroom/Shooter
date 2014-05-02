package shooter.events;

import shooter.model.GameModel;
import shooter.model.ShotModel;

public class PlayerShootEvent extends Event {
	
	private GameModel model;
	
	public PlayerShootEvent(GameModel model) {
		this.model = model;
	}
	
	@Override
	public void run(double seconds) {
		ShotModel shot = model.getPlayer().getBuilder().build();
		if(shot != null) {
			model.getLevel().addShot(shot);
		}
	}
}
