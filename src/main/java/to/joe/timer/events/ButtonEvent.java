package to.joe.timer.events;

import to.joe.timer.hardware.Button;

public class ButtonEvent implements Event {
	
	public enum Action {
		PRESSED,
		RELEASED,
	}
	
	private Button button;
	private Action action;
	private boolean consumed = false;
	
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
	
	public void consume() {
		consumed = true;
	}
	
	public boolean isConsumed() {
		return consumed;
	}
	
	@Override
	public String toString() {
		return String.format("[%s %s]", button, action);
	}

}
