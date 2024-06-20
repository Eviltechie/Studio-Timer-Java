package to.joe.timer;

import to.joe.timer.color.Color;
import to.joe.timer.hardware.ButtonColorState;
import to.joe.timer.logic.MenuController;
import to.joe.timer.menu.timer.DirectionPresetMenu;
import to.joe.timer.menu.timer.FiveSecondsMenu;
import to.joe.timer.menu.timer.OneMinuteMenu;
import to.joe.timer.menu.timer.SpeedMenu;
import to.joe.timer.menu.timer.TimerSelectMenu;

public class Timer implements Runnable {
	
	private static int MAX_TIME = 359999; // We can't display higher than 99h 59m 59s.
	
	private String timerName;
	private Color timerColor;
	private volatile Direction direction = Direction.UP;
	private volatile boolean running = false;
	private volatile int timeSeconds = 0;
	private volatile int rateMiliseconds = 1000;
	private MenuController menuController;
	private ButtonColorState buttonColorState = new ButtonColorState();
	
	public Timer(String name, Color timerColor) {
		this.timerName = name;
		this.timerColor = timerColor;
		menuController = new MenuController();
		menuController.addMenuElement(new DirectionPresetMenu(menuController, this));
		menuController.addMenuElement(new FiveSecondsMenu(menuController, this));
		menuController.addMenuElement(new OneMinuteMenu(menuController, this));
		menuController.addMenuElement(new SpeedMenu(menuController, this));
		menuController.addMenuElement(new TimerSelectMenu(menuController, this));
		buttonColorState.setKeypadColor(timerColor);
	}
	
	public String getTimerName() {
		return timerName;
	}
	
	public void setTimerName(String timerName) {
		this.timerName = timerName;
	}
	
	public Color getTimerColor() {
		return timerColor;
	}
	
	public void setTimerColor(Color timerColor) {
		this.timerColor = timerColor;
	}
	
	public MenuController getMenuController() {
		return menuController;
	}
	
	public ButtonColorState getButtonColorState() {
		return buttonColorState;
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
	
	public int getRate() {
		return rateMiliseconds;
	}
	
	public void changeRate(int miliseconds) {
		rateMiliseconds = miliseconds;
	}
	
	public int getTime() {
		return timeSeconds;
	}
	
	// TODO Silently validate or throw exception? (Don't want seconds going out of range.)
	public void setTime(int seconds) {
		timeSeconds = seconds;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	@Override
	public String toString() {
		return String.format("Name: %s, Direction: %s, Time: %s, Running: %s, Rate: %s", timerName, direction, timeSeconds, running, rateMiliseconds);
	}

}
