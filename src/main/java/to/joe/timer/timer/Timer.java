package to.joe.timer.timer;

import to.joe.timer.main.TimerApplication;
import to.joe.timer.timer.events.TimerChangeEvent;
import to.joe.timer.timer.events.TimerStartEvent;
import to.joe.timer.timer.events.TimerStopEvent;
import to.joe.timer.timer.events.TimerTickEvent;

public class Timer implements Runnable {
	
	private static final int MAX_TIME = 359999; // Equal to 99h 59m 59s, the max we can show on a six digit display.
	private static final int MIN_TIME = -35999; // Equal to -9h 59m 59s the min we can show on a six digit display, assuming the first digit is a "-".
	
	private TimerApplication timerApplication;
	
	private volatile Direction direction = Direction.UP;
	private volatile boolean running = false;
	private volatile int timeSeconds = 0;
	private volatile int lastSetSeconds = 0;
	private volatile int rateMiliseconds = 1000;
	private volatile boolean negativeAllowed = false;
	
	private Thread timerThread;
	
	public Timer(TimerApplication timerApplication) {
		this.timerApplication = timerApplication;
	}
	
	/**
	 * Attempts to start this timer ticking.
	 * @return <code>true</code> if started by this call, <code>false</code> if out of range or already running.
	 */
	public boolean startTimer() {
		if (direction == Direction.DOWN && negativeAllowed && timeSeconds <= MIN_TIME) { // Timer cannot run below MIN_TIME.
			return false;
		}
		
		if (direction == Direction.DOWN && !negativeAllowed && timeSeconds <= 0) { // Timer cannot run below 0.
			return false;
		}
		
		if (direction == Direction.UP && timeSeconds >= MAX_TIME) { // Timer cannot run above MAX_TIME.
			return false;
		}
		
		if (running) { // Cannot start a second time.
			return false;
		}
		
		timerThread = new Thread(this);
		timerThread.start();
		
		running = true;
		
		timerApplication.add(new TimerStartEvent(this));
		
		return true;
	}
	
	public void stopTimer() {
		if (running) {
			timerThread.interrupt();
		}
		running = false;
	}
	
	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(rateMiliseconds);
				if (running) {
					if (direction == Direction.DOWN) {
						if (negativeAllowed && timeSeconds <= MIN_TIME) {
							running = false;
						} else if (!negativeAllowed && timeSeconds <= 0) {
							running = false;
						} else {
							timeSeconds--;
						}
						if (negativeAllowed && timeSeconds <= MIN_TIME) {
							running = false;
						} else if (!negativeAllowed && timeSeconds <= 0) {
							running = false;
						} else {
							timerApplication.add(new TimerTickEvent(this));
						}
					} else { // Up
						if (timeSeconds >= MAX_TIME) {
							running = false;
						} else {
							timeSeconds++;
						}
						if (timeSeconds >= MAX_TIME) {
							running = false;
						} else {
							timerApplication.add(new TimerTickEvent(this));
						}
					}
				}
			} catch (InterruptedException e) {
				running = false;
			}
		}
		timerApplication.add(new TimerStopEvent(this));
	}
	
	public int getRate() {
		return rateMiliseconds;
	}
	
	public void setRate(int miliseconds) {
		if (miliseconds < 500 || miliseconds > 2000) {
			throw new IllegalArgumentException("Rate out of range");
		}
		rateMiliseconds = miliseconds;
	}
	
	public int getTime() {
		return timeSeconds;
	}
	
	public void setTime(int seconds) {
		/*if (direction == Direction.DOWN && negativeAllowed && seconds < MIN_TIME) { // Timer cannot run below MIN_TIME.
			throw new IllegalArgumentException("Time out of range");
		}
		
		if (direction == Direction.DOWN && !negativeAllowed && seconds < 0) { // Timer cannot run below 0.
			throw new IllegalArgumentException("Time out of range");
		}
		
		if (direction == Direction.UP && seconds > MAX_TIME) { // Timer cannot run above MAX_TIME.
			throw new IllegalArgumentException("Time out of range");
		}*/
		
		if (negativeAllowed && seconds < MIN_TIME) { // Timer cannot run below MIN_TIME.
			throw new IllegalArgumentException("Time out of range");
		}
		
		if (!negativeAllowed && seconds < 0) { // Timer cannot run below 0.
			throw new IllegalArgumentException("Time out of range");
		}
		
		if (seconds > MAX_TIME) { // Timer cannot run above MAX_TIME.
			throw new IllegalArgumentException("Time out of range");
		}
		
		timeSeconds = seconds;
		lastSetSeconds = seconds;
		timerApplication.add(new TimerChangeEvent(this));
	}
	
	public void recallSetTime() {
		timeSeconds = lastSetSeconds;
		timerApplication.add(new TimerChangeEvent(this));
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void setDirection(Direction direction) {
		if (this.direction == Direction.UP && direction == Direction.DOWN) {
			lastSetSeconds = timeSeconds;
		}
		this.direction = direction;
	}
	
	public boolean getNegativeAllowed() {
		return negativeAllowed;
	}
	
	public void setNegativeAllowed(boolean allowed) {
		negativeAllowed = allowed;
	}
	
	@Override
	public String toString() {
		return String.format("[Direction: %s, Time: %s, Running: %s, Rate: %s]", direction, timeSeconds, running, rateMiliseconds);
	}

}
