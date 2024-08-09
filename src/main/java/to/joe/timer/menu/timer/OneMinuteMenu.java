package to.joe.timer.menu.timer;

import to.joe.timer.color.HSVColor;
import to.joe.timer.events.Event;
import to.joe.timer.hardware.Button;
import to.joe.timer.hardware.events.ButtonEvent;
import to.joe.timer.hardware.events.ButtonEvent.Action;
import to.joe.timer.main.TimerApplication;
import to.joe.timer.menu.Menu;
import to.joe.timer.timercontroller.TimerContainer;

public class OneMinuteMenu extends Menu {
	
	private TimerContainer timerContainer;
	
	public OneMinuteMenu(TimerApplication timerApplication) {
		super(timerApplication);
		setLine2("-1m    +1m     ~");
		getButtonColorState().setButtonColor(Button.SOFTKEY_1, HSVColor.WHITE_DIM);
		getButtonColorState().setButtonColor(Button.SOFTKEY_2, HSVColor.WHITE_DIM);
		getButtonColorState().setButtonColor(Button.SOFTKEY_3, HSVColor.WHITE_DIM);
	}
	
	@Override
	public void handleEvent(Event event) {
		if (event instanceof ButtonEvent) {
			ButtonEvent buttonEvent = (ButtonEvent) event;
			Button b = buttonEvent.getButton();
			if (buttonEvent.getAction() == Action.PRESSED) {
				if (b == Button.SOFTKEY_1) {
					buttonEvent.consume();
					try {
						timerContainer.getTimer().setTime(timerContainer.getTimer().getTime() - 60);
					} catch (Exception e) {
						// Pass
					}
				}
				if (b == Button.SOFTKEY_2) {
					buttonEvent.consume();
					try {
						timerContainer.getTimer().setTime(timerContainer.getTimer().getTime() + 60);
					} catch (Exception e) {
						// Pass
					}
				}
				if (b == Button.SOFTKEY_3) {
					buttonEvent.consume();
					getTimerApplication().getMenuController().nextMenu();
				}
			}
		}
	}
	
	@Override
	public void active() {
		timerContainer = getTimerApplication().getTimerController().getActiveTimer();
		setLine1(String.format("%s", timerContainer.getTimerName()));
		setLCDColor(timerContainer.getColor());
	}
	
	@Override
	public void inactive() {
		timerContainer = null;
	}

}
