package to.joe.timer.menu.timer;

import to.joe.timer.Timer;
import to.joe.timer.color.HSVColor;
import to.joe.timer.events.ButtonEvent;
import to.joe.timer.events.ButtonEvent.Action;
import to.joe.timer.hardware.Button;
import to.joe.timer.logic.MenuController;
import to.joe.timer.menu.Menu;

public class TimerSelectMenu extends Menu {
	
	public TimerSelectMenu(MenuController menuController, Timer timer) {
		super(menuController);
		setLine1(String.format("Timer: %s", timer.getTimerName()));
		setLine2("Select         ~");
		setLCDColor(timer.getTimerColor());
		getButtonColorState().setButtonColor(Button.SOFTKEY_1, HSVColor.WHITE);
		getButtonColorState().setButtonColor(Button.SOFTKEY_3, HSVColor.WHITE);
	}
	
	@Override
	public void handleEvent(ButtonEvent event) {
		Button b = event.getButton();
		if (b == Button.SOFTKEY_3 && event.getAction() == Action.PRESSED) {
			event.consume();
			getMenuController().nextMenu();
		}
	}

}
