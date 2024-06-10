package to.joe.timer.menu.timer;

import to.joe.timer.Timer;
import to.joe.timer.logic.MenuController;
import to.joe.timer.menu.ButtonMenu;

public class DirectionPresetMenu extends ButtonMenu {
	
	public DirectionPresetMenu(MenuController menuController, Timer timer) {
		super(menuController, "Timer 1", "Down  Preset   ~", getLCDColor(), getButton1Color(), getButton2Color(), getButton3Color());
	}

}
