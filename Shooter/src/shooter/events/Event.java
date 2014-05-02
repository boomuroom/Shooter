package shooter.events;

public abstract class Event {
	public abstract void run(double seconds);

	@Override
	public boolean equals(Object o) {
		return this.getClass() == o.getClass();
	}
}
