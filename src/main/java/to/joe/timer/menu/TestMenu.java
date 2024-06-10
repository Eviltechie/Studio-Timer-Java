package to.joe.timer.menu;

import java.util.Random;

import to.joe.timer.Main;
import to.joe.timer.color.HSVColor;
import to.joe.timer.color.RGBColor;
import to.joe.timer.events.ButtonEvent;
import to.joe.timer.events.ButtonEvent.Action;
import to.joe.timer.hardware.Button;
import to.joe.timer.hardware.LED;
import to.joe.timer.logic.MenuController;

public class TestMenu extends ButtonMenu {
	
	private Random random = new Random();

	public TestMenu(MenuController menuController, String line1, RGBColor button1Color, RGBColor button2Color, RGBColor button3Color) {
		super(menuController, line1, "Left | ? | Right", new RGBColor(255, 255, 255), button1Color, button2Color, button3Color);
	}
	
	@Override
	public void handleEvent(ButtonEvent event) {
		Button b = event.getButton();
		if (b.isDigit()) {
			if (event.getAction() == Action.PRESSED) {
				Main.hardware.getSerialWriter().add(LED.switchHSV(b, new HSVColor(random.nextInt(255), 255, 255)));
			} else {
				Main.hardware.getSerialWriter().add(LED.switchHSV(b, new HSVColor(0, 0, 0)));
			}
		}
		if (b == Button.SOFTKEY_3 && event.getAction() == Action.PRESSED) {
			getMenuController().nextMenu();
		}
		if (b == Button.SOFTKEY_1 && event.getAction() == Action.PRESSED) {
			getMenuController().previousMenu();
		}
	}

}
