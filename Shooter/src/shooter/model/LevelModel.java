package shooter.model;

import java.util.ArrayList;
import java.util.List;
import shooter.Display;
import shooter.ai.AIHandler;
import shooter.ai.BasicAI;
import shooter.util.Rectangle;

public class LevelModel {
	
	private List<EnemyModel> enemies = new ArrayList<>();
	private List<ShotModel> shots = new ArrayList<>();
	private AIHandler aiHandler = new BasicAI();
	
	public LevelModel() {
		for(int i=0;i<9;++i) {
			for(int j=0;j<3;++j) {
					Rectangle loc = new Rectangle(i*0.1, j*.1, 0.05, 0.05);
					EnemyModel enemy = new EnemyModel();
					enemy.setLocation(loc);
					enemies.add(enemy);
					aiHandler.add(enemy);
			}
		}
	}
	
	public List<EnemyModel> getEnemies() {
		return enemies;
	}
	
	public void remove(ShipModel ship) {
		aiHandler.remove(ship);
		enemies.remove(ship);
	}
	
	public void draw(Display d) {
		for(EnemyModel e : enemies) {
			d.shadeRectangle(e.getLocation(), e.getColor());
		}
		for(ShotModel shot : shots) {
			d.shadeRectangle(shot.getLocation(), shot.getColor());
		}
	}
	
	public void update(double seconds) {
		for(EnemyModel e : enemies) {
			e.update(seconds);
			Rectangle loc = e.getLocation();
			double cx = loc.x + loc.width/2;
			ShotModel shot = e.getBuilder().setLocation(cx - loc.width/10, loc.y, loc.width/10, loc.height/2).build();
			if(shot != null) {
				shots.add(shot);
			}
		}
		aiHandler.update(seconds);
	}
	
	public List<ShotModel> getShots() {
		return shots;
	}

	public void addShot(ShotModel shot) {
		shots.add(shot);
	}
}
