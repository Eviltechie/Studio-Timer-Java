package to.joe.timer.events;

public abstract class ConsumableEvent extends Event {
	
private boolean consumed = false;
	
	public void consume() {
		consumed = true;
	}
	
	public boolean isConsumed() {
		return consumed;
	}

}
