package to.joe.timer.events;

import to.joe.timer.Main;

public class EventHandler {
	
	public void sendButtonEvent(ButtonEvent event) {
		Main.menuController.handleEvent(event);
		if (!event.isConsumed()) {
			Main.timer.handleEvent(event);
		}
	}

}
