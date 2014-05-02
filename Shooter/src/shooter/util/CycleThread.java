package shooter.util;

public class CycleThread extends Thread {

	public static interface Callback {
		public void update(long nanos);
	}

	private Callback cb;
	private volatile boolean running;
	private long nanosPerCycle;

	public CycleThread(Callback cb, double cps) {
		this.cb = cb;
		nanosPerCycle = (long)(1000000000L / cps);
		if(cb == null) throw new NullPointerException("Callback cannot be null");
	}

	@Override
	public void run() {
		boolean dead = false;
		running = true;
		long startTime = System.nanoTime();
		long sleepTime = nanosPerCycle;
		long diff = 0;
		while(!dead) {
			try {
				synchronized(this) {
					while(!running) {
						wait();
						startTime = System.nanoTime();
					}
				}
				cb.update(diff);
				if(sleepTime > 0) {
					Thread.sleep(sleepTime/1000000);
				}
				long endTime = System.nanoTime();
				diff = endTime - startTime;
				long variance = nanosPerCycle - diff;
				sleepTime += variance/2;
				startTime = endTime;
			} catch(InterruptedException ex) {
				dead = true;
			}
		}
	}

	public boolean isRunning() {
		return running;
	}

	public void stopCycles() {
		running = false;
	}

	public void resumeCycles() {
		running = true;
		synchronized(this) {
			notify();
		}
	}
}
