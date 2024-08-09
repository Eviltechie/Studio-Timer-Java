package to.joe.timer.hardware.events;

import to.joe.timer.events.ConsumableEvent;
import to.joe.timer.hardware.Button;

public class ButtonEvent extends ConsumableEvent {
	
	public enum Action {
		PRESSED,
		RELEASED,
	}
	
	private Button button;
	private Action action;
	
	public ButtonEvent(Button button, Action action) {
		this.button = button;
		this.action = action;
	}
	
	public Button getButton() {
		return button;
	}
	
	public Action getAction() {
		return action;
	}
	
	@Override
	public String toString() {
		return String.format("[%s %s]", button, action);
	}

}
