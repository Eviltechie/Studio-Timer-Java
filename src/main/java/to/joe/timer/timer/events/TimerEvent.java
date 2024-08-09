package to.joe.timer.timer.events;

import to.joe.timer.events.Event;
import to.joe.timer.timer.Timer;

public abstract class TimerEvent extends Event {
	
	private Timer timer;
	
	public TimerEvent(Timer timer) {
		this.timer = timer;
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	@Override
	public String toString() {
		return timer.toString();
	}

}
