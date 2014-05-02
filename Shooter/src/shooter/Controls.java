package shooter;

import java.util.HashSet;
import shooter.events.Event;
import shooter.events.MovePlayerLeftEvent;
import shooter.events.MovePlayerRightEvent;
import shooter.events.PlayerShootEvent;
import shooter.model.GameModel;

public class Controls {
		
	private HashSet<Event> events = new HashSet<>();
	private HashSet<Event> buffer = new HashSet<>();
	private GameModel model;
	Controls(GameModel model) {
		this.model = model;
	}
	
	HashSet<Event> getEvents() {
		buffer.clear();
		HashSet<Event> temp = events;
		events = buffer;
		buffer = temp;
		return temp;
	}
	
	public void playerShoot() {
		events.add(new PlayerShootEvent(model));
	}
	
	public void movePlayerLeft() {
		events.add(new MovePlayerLeftEvent(model));
	}
	
	public void movePlayerRight() {
		events.add(new MovePlayerRightEvent(model));
	}
}
