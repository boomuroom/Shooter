package shooter.model;

import java.util.ArrayList;
import java.util.List;
import shooter.Display;
import shooter.util.Font;
import shooter.util.Rectangle;

public class GameModel {

	private PlayerModel player = new PlayerModel();
	private LevelModel level = new LevelModel();
	private double factor = 1;

	public enum State {

		GAME, GAME_OVER
	};
	private State state = State.GAME;

	public PlayerModel getPlayer() {
		return player;
	}

	public LevelModel getLevel() {
		return level;
	}

	public void draw(Display d) {
		level.draw(d);
		d.shadeRectangle(player.getLocation(), player.getColor());
		/*for(ShotModel shot : level.getShots()) {
			d.shadeRectangle(shot.getLocation(), shot.getColor());
		}*/
		if (state == State.GAME_OVER) {
			d.drawString("GAME OVER", Font.def, 0.5, 0.5);
		}
	}

	public State getState() {
		return state;
	}

	private void gameOver() {
		state = State.GAME_OVER;
		//new Exception().printStackTrace();
	}

	public void update(double seconds) {
		double accelerated = seconds * factor;
		level.update(accelerated);
		player.update(seconds);
		List<ShotModel> removeList = new ArrayList<>();
		List<ShipModel> removeList2 = new ArrayList<>();
		for(ShotModel shot : level.getShots()) {
			shot.move(accelerated);
			if(shot.isOutOfBounds()) {
				removeList.add(shot);
			} else {
				if (shot.speed > 0) {
					if (shot.getLocation().intersects(player.getLocation())) {
						player.explode();
						if (!player.loseLife()) {
							gameOver();
						}
						removeList.add(shot);
					}
				} else {
					Rectangle shotLoc = shot.getLocation();
					for(ShipModel badGuy : level.getEnemies()) {
						if (shotLoc.intersects(badGuy.getLocation())) {
							badGuy.explode();
							removeList2.add(badGuy);
							removeList.add(shot);
						}
					}
					for(ShipModel ship : removeList2) {
						level.remove(ship);
					}
					if(level.getEnemies().isEmpty()) {
						level = new LevelModel();
						player.shotsFired.clear();
						factor *= 1.1;
					}
					removeList2.clear();
				}
			}
		}
		for(ShotModel shot : removeList) {
			level.getShots().remove(shot);
			shot.clear();
		}
		removeList.clear();

	}
}
