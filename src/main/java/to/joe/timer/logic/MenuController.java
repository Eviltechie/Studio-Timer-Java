package to.joe.timer.logic;

import to.joe.timer.Main;
import to.joe.timer.RGBColor;
import to.joe.timer.events.ButtonEvent;
import to.joe.timer.menu.Menu;
import to.joe.timer.menu.TestMenu;

public class MenuController {
	
	private Menu currentMenu;
	
	public MenuController() {
		currentMenu = new TestMenu("Test Menu", new RGBColor(255, 0, 0), new RGBColor(0, 255, 0), new RGBColor(0, 0, 255));
		Main.hardware.getSerialWriter().add(currentMenu.display());
	}
	
	public void receiveEvent(ButtonEvent event) {
		currentMenu.receiveEvent(event);
	}

}
