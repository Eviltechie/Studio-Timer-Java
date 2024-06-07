package to.joe.timer;

public class Timer implements Runnable {
	
	private static int MAX_TIME = 359999; // We can't display higher than 99h 59m 59s.
	
	private String name;
	private volatile Direction direction = Direction.UP;
	private volatile boolean running = false;
	private volatile int timeSeconds = 0;
	private volatile int rateMiliseconds = 1000;
	
	public Timer(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(rateMiliseconds);
				if (running) { // We check if still running after sleeping in case we've stopped since then.
					if (direction == Direction.DOWN) {
						if (timeSeconds <= 0) {
							stopTimer();
						} else {
							timeSeconds--;
						}
						if (timeSeconds <= 0) {
							stopTimer();
						}
					} else { // UP
						if (timeSeconds >= MAX_TIME) {
							stopTimer();
						} else {
							timeSeconds++;
						}
						if (timeSeconds >= MAX_TIME) {
							stopTimer();
						}
					}
					System.out.println(this);
				}
			} catch (InterruptedException e) {
				running = false;
			}
		}
	}
	
	public boolean startTimer() {
		if (direction == Direction.DOWN && timeSeconds <= 0) { // Can't run negative
			return false;
		}
		
		if (direction == Direction.UP && timeSeconds >= MAX_TIME) {
			return false;
		}
		
		if (running) { // Already running
			return true; // TODO Do we actually want to do this?
		}
		
		new Thread(this).start();
		running = true;
		return true;
	}
	
	public void stopTimer() {
		running = false;
	}
	
	public void changeRate(int miliseconds) {
		rateMiliseconds = miliseconds;
	}
	
	public void setTime(int seconds) {
		timeSeconds = seconds;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	@Override
	public String toString() {
		return String.format("Name: %s, Direction: %s, Time: %s, Running: %s, Rate: %s", name, direction, timeSeconds, running, rateMiliseconds);
	}

}
