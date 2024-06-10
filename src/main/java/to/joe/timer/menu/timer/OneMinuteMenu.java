package to.joe.timer.menu.timer;

import to.joe.timer.Timer;
import to.joe.timer.color.HSVColor;
import to.joe.timer.events.ButtonEvent;
import to.joe.timer.events.ButtonEvent.Action;
import to.joe.timer.hardware.Button;
import to.joe.timer.logic.MenuController;
import to.joe.timer.menu.ButtonMenu;

public class OneMinuteMenu extends ButtonMenu {
	
	public OneMinuteMenu(MenuController menuController, Timer timer) {
		super(menuController, String.format("Timer: %s", timer.getTimerName()), "-1m    +1m     ~", timer.getTimerColor(), HSVColor.WHITE, HSVColor.WHITE, HSVColor.WHITE);
	}
	
	@Override
	public void handleEvent(ButtonEvent event) {
		Button b = event.getButton();
		if (b == Button.SOFTKEY_3 && event.getAction() == Action.PRESSED) {
			getMenuController().nextMenu();
		}
	}

}