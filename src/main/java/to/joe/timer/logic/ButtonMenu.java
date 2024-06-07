package to.joe.timer.logic;

import java.util.List;

import to.joe.timer.RGBColor;
import to.joe.timer.hardware.Button;
import to.joe.timer.hardware.Command;
import to.joe.timer.hardware.LED;

public class ButtonMenu extends Menu {
	
	private RGBColor button1;
	private RGBColor button2;
	private RGBColor button3;

	public ButtonMenu(String line1, String line2, RGBColor LCDColor, RGBColor button1, RGBColor button2, RGBColor button3) {
		super(line1, line2, LCDColor);
		
		this.button1 = button1;
		this.button2 = button2;
		this.button3 = button3;
	}
	
	@Override
	public List<Command> display() {
		List<Command> l = super.display();
		l.add(LED.switchRGB(Button.SOFTKEY_1, button1));
		l.add(LED.switchRGB(Button.SOFTKEY_2, button2));
		l.add(LED.switchRGB(Button.SOFTKEY_3, button3));
		return l;
	}

}
