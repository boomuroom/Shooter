package shooter;

import java.util.ArrayList;
import java.util.List;
import shooter.events.Event;
import shooter.model.GameModel;
import shooter.util.Color;
import shooter.util.CycleThread;
import shooter.util.Rectangle;

public class Game {
	
	public interface FrameListener {
		public void update(Controls c);
	}

	private static final Rectangle fullScreen = new Rectangle(0, 0, 1, 1);
	private CycleThread cycleThread;
	private GameModel model = new GameModel();
	private Controls controls = new Controls(model);
	private List<Display> displays = new ArrayList<>();
	private List<FrameListener> listeners = new ArrayList<>();
	private int cycleRate = 30;
	
	public void addFrameListener(FrameListener l) {
		listeners.add(l);
	}
	
	public void addDisplay(Display d) {
		displays.add(d);
	}
	
	public void setCycleRate(int rate) {
		if(cycleThread == null) {
			if(rate < 1) {
				rate = 10000;
			}
			cycleRate = rate;
		}
	}
	
	public int getCycleRate() {
		return cycleRate;
	}
	
	private void frameUpdate(double seconds) {
		if(model.getState() != GameModel.State.GAME_OVER) {
			//Read Input
			for(FrameListener f : listeners) {
				f.update(controls);
			}
		}
		//Move
		for(Event e : controls.getEvents()) {
			e.run(seconds);
		}
		model.update(seconds);

		//Draw
		for(Display d : displays) {
			draw(d);
		}
	}
	
	public void reset() {
		model = new GameModel();
		controls = new Controls(model);
	}
	
	public void draw(Display d) {
		d.start();
		d.shadeRectangle(fullScreen, Color.black);
		model.draw(d);
		d.finish();
	}
	
	public void start() {
		cycleThread = new CycleThread(
			new CycleThread.Callback() {
				@Override
				public void update(long nanos) {
					Game.this.frameUpdate(nanos/1000000000.0);
				}
			},
			cycleRate
		);
		cycleThread.start();
		//throw new RuntimeException("Not Implemented");
	}
}
