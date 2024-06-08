package to.joe.timer.menu;

import java.util.Random;

import to.joe.timer.HSVColor;
import to.joe.timer.Main;
import to.joe.timer.RGBColor;
import to.joe.timer.events.ButtonEvent;
import to.joe.timer.events.ButtonEvent.Action;
import to.joe.timer.hardware.Button;
import to.joe.timer.hardware.LED;

public class TestMenu extends ButtonMenu {
	
	private Random random = new Random();

	public TestMenu(String line1, RGBColor button1Color, RGBColor button2Color, RGBColor button3Color) {
		super(line1, "Left | ? | Right", new RGBColor(255, 255, 255), button1Color, button2Color, button3Color);
	}
	
	@Override
	public void receiveEvent(ButtonEvent event) {
		Button b = event.getButton();
		if (b.isDigit()) {
			if (event.getAction() == Action.PRESSED) {
				Main.hardware.getSerialWriter().add(LED.switchHSV(b, new HSVColor(random.nextInt(255), 255, 255)));
			} else {
				Main.hardware.getSerialWriter().add(LED.switchHSV(b, new HSVColor(0, 0, 0)));
			}
		}
	}

}
