package to.joe.timer.debug;

import to.joe.timer.events.Event;

public class DebugEvent extends Event {
	
	private String command;
	
	public DebugEvent(String command) {
		this.command = command;
	}
	
	public String getCommand() {
		return command;
	}
	
	@Override
	public String toString() {
		return String.format("[Command: %s]", command);
	}

}
