package to.joe.timer.menu.timer;

import to.joe.timer.Direction;
import to.joe.timer.Timer;
import to.joe.timer.color.HSVColor;
import to.joe.timer.events.ButtonEvent;
import to.joe.timer.events.ButtonEvent.Action;
import to.joe.timer.hardware.Button;
import to.joe.timer.logic.MenuController;
import to.joe.timer.menu.ButtonMenu;

public class DirectionPresetMenu extends ButtonMenu {
	
	public DirectionPresetMenu(MenuController menuController, Timer timer) {
		super(menuController, String.format("Timer: %s", timer.getTimerName()), "Down  Preset   ~", timer.getTimerColor(), HSVColor.BLACK, HSVColor.BLUE, HSVColor.WHITE);
		if (timer.getDirection() == Direction.UP) {
			setButton1Color(HSVColor.GREEN);
			setLine2("Up    Preset   ~");
		} else {
			setButton1Color(HSVColor.RED);
			setLine2("Down  Preset   ~");
		}
	}
	
	@Override
	public void handleEvent(ButtonEvent event) {
		Button b = event.getButton();
		if (b == Button.SOFTKEY_3 && event.getAction() == Action.PRESSED) {
			getMenuController().nextMenu();
		}
	}

}
