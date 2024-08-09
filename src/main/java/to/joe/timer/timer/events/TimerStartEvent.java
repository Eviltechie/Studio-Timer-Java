package to.joe.timer.timer.events;

import to.joe.timer.timer.Timer;

public class TimerStartEvent extends TimerEvent {

	public TimerStartEvent(Timer timer) {
		super(timer);
	}

}
