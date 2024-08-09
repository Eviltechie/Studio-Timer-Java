package to.joe.timer.timercontroller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import to.joe.timer.color.HSVColor;
import to.joe.timer.events.Event;
import to.joe.timer.hardware.Button;
import to.joe.timer.hardware.commands.LED;
import to.joe.timer.hardware.events.ButtonEvent;
import to.joe.timer.hardware.events.ButtonEvent.Action;
import to.joe.timer.main.TimerApplication;
import to.joe.timer.timer.Direction;
import to.joe.timer.timer.Timer;
import to.joe.timer.timer.events.TimerEvent;
import to.joe.timer.timer.events.TimerStartEvent;
import to.joe.timer.timer.events.TimerStopEvent;

public class TimerController {
	
	private TimerApplication timerApplication;
	private LinkedHashMap<String, TimerContainer> timers = new LinkedHashMap<String, TimerContainer>();
	private TimerContainer activeTimer;
	private boolean inputStackDefault = true;
	private List<Integer> inputStack = new ArrayList<Integer>();
	
	public TimerController(TimerApplication timerApplication) {
		this.timerApplication = timerApplication;
		
		timers.put("TimerA", new TimerContainer(timerApplication, "TimerA", HSVColor.ORANGE_DIM));
		timers.put("TimerB", new TimerContainer(timerApplication, "TimerB", HSVColor.YELLOW_DIM));
		timers.put("TimerC", new TimerContainer(timerApplication, "TimerC", HSVColor.AQUA_DIM));
		timers.put("TimerD", new TimerContainer(timerApplication, "TimerD", HSVColor.BLUE_DIM));
		timers.put("TimerE", new TimerContainer(timerApplication, "TimerE", HSVColor.PURPLE_DIM));
		timers.put("TimerF", new TimerContainer(timerApplication, "TimerF", HSVColor.PINK_DIM));
		
		activeTimer = timers.get("TimerA");
		
		resetInputStack();
	}
	
	public TimerContainer getActiveTimer() {
		return activeTimer;
	}
	
	private void pushInputStack(int i) {
		inputStack.add(i);
		inputStack.removeFirst();
		inputStackDefault = false;
	}
	
	private void resetInputStack() {
		inputStack.clear();
		for (int i = 0; i < 6; i++) {
			inputStack.add(0);
		}
		inputStackDefault = true;
	}
	
	public void setActiveTimer(String timer) {
		activeTimer = timers.get(timer);
		resetInputStack();
		timerApplication.getHardware().add(LED.sevenSegment(activeTimer.getTimer().getTime()));
		timerApplication.getRenderPipeline().redraw();
	}
	
	public TimerContainer getTimerContainer(String timerName) {
		TimerContainer timerContainer = timers.get(timerName);
		if (timerContainer == null) {
			throw new IllegalArgumentException("Timer not found");
		}
		return timerContainer;
	}
	
	private TimerContainer getTimerContainer(Timer timer) {
		for (TimerContainer tc : timers.values()) {
			if (timer.equals(tc.getTimer())) {
				return tc;
			}
		}
		throw new IllegalArgumentException("Timer not found");
	}
	
	public void handleEvent(Event event) {
		if (event instanceof TimerEvent) {
			TimerEvent timerEvent = (TimerEvent) event;
			TimerContainer tc = getTimerContainer(timerEvent.getTimer());
			if (timerEvent instanceof TimerStartEvent) {
				tc.getButtonColorState().setButtonColor(Button.START_STOP, HSVColor.GREEN);
				tc.getButtonColorState().setKeypadColor(HSVColor.BLACK);
			} else if (timerEvent instanceof TimerStopEvent) {
				tc.getButtonColorState().setButtonColor(Button.START_STOP, HSVColor.RED);
				tc.getButtonColorState().setKeypadColor(tc.getColor());
			}
			
			if (activeTimer.getTimer().equals(timerEvent.getTimer())) {
				timerApplication.getHardware().add(LED.sevenSegment(timerEvent.getTimer().getTime()));
				if (timerEvent instanceof TimerStartEvent || timerEvent instanceof TimerStopEvent) {
					timerApplication.getRenderPipeline().redraw();
				}
			}
		} else if (event instanceof ButtonEvent) {
			ButtonEvent buttonEvent = (ButtonEvent) event;
			if (buttonEvent.getButton() == Button.START_STOP && buttonEvent.getAction() == Action.PRESSED) {
				if (activeTimer.getTimer().isRunning()) {
					activeTimer.getTimer().stopTimer();
				} else {
					if (!inputStackDefault) {
						int hours = inputStack.get(0) * 10 + inputStack.get(1);
						int minutes = inputStack.get(2) * 10 + inputStack.get(3);
						int seconds = inputStack.get(4) * 10 + inputStack.get(5);
						try {
							getActiveTimer().getTimer().setTime(hours * 3600 + minutes * 60 + seconds);
						} catch (IllegalArgumentException e) {
							resetInputStack();
							timerApplication.getHardware().add(LED.sevenSegment(0));
							return;
						}
						resetInputStack();
					}
					activeTimer.getTimer().startTimer();
				}
			}
			if (buttonEvent.getButton() == Button.RESET && buttonEvent.getAction() == Action.PRESSED && getActiveTimer().getTimer().getDirection() == Direction.UP) {
				resetInputStack();
				getActiveTimer().getTimer().setTime(0);
				getActiveTimer().getTimer().setRate(1000);
				timerApplication.getHardware().add(LED.sevenSegment(0));
			}
			if (buttonEvent.getButton() == Button.RESET && buttonEvent.getAction() == Action.PRESSED && getActiveTimer().getTimer().getDirection() == Direction.DOWN) {
				getActiveTimer().getTimer().recallSetTime();
				getActiveTimer().getTimer().setRate(1000);
				timerApplication.getHardware().add(LED.sevenSegment(getActiveTimer().getTimer().getTime()));
			}
			if (buttonEvent.getButton().isDigit() && buttonEvent.getAction() == Action.PRESSED && !getActiveTimer().getTimer().isRunning()) {
				pushInputStack(buttonEvent.getButton().digitValue());
				StringBuilder sb = new StringBuilder();
				for (Integer i : inputStack) {
					sb.append(i);
				}
				timerApplication.getHardware().add(LED.sevenSegment(sb.toString()));
			}
		}
	}

}
