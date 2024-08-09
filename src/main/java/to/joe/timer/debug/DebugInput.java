package to.joe.timer.debug;

import java.util.Scanner;

import to.joe.timer.events.Event;
import to.joe.timer.main.TimerApplication;
import to.joe.timer.timer.Direction;
import to.joe.timer.timer.Timer;

public class DebugInput extends Thread {
	
	private TimerApplication timerApplication;
	
	public DebugInput(TimerApplication timerApplication) {
		this.timerApplication = timerApplication;
	}
	
	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);
		while (!isInterrupted()) {
			String command = scanner.nextLine();
			timerApplication.add(new DebugEvent(command));
		}
		scanner.close();
	}
	
	public void handleEvent(Event event) {
		if (event instanceof DebugEvent) {
			String command = ((DebugEvent) event).getCommand();
			String[] split = command.split(" ");
			Timer t = timerApplication.getTimerController().getTimerContainer(split[0]).getTimer();
			if (split[1].equalsIgnoreCase("start")) {
				t.startTimer();
			} else if (split[1].equalsIgnoreCase("stop")) {
				t.stopTimer();
			} else if (split[1].equalsIgnoreCase("up")) {
				t.setDirection(Direction.UP);
			} else if (split[1].equalsIgnoreCase("down")) {
				t.setDirection(Direction.DOWN);
			} else if (split[1].equalsIgnoreCase("time")) {
				t.setTime(Integer.parseInt(split[2]));
			} else if (split[1].equalsIgnoreCase("rate")) {
				t.setRate(Integer.parseInt(split[2]));
			} else if (split[1].equalsIgnoreCase("active")) {
				timerApplication.getTimerController().setActiveTimer(split[0]);
			} else {
				System.out.println("[Unknown debug command]");
			}
		}
	}

}
