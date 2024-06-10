package to.joe.timer.menu;

import java.util.List;

import to.joe.timer.color.RGBColor;
import to.joe.timer.hardware.Button;
import to.joe.timer.hardware.Command;
import to.joe.timer.hardware.LED;
import to.joe.timer.logic.MenuController;

public class ButtonMenu extends Menu {
	
	private RGBColor button1Color;
	private RGBColor button2Color;
	private RGBColor button3Color;

	public ButtonMenu(MenuController menuController, String line1, String line2, RGBColor LCDColor, RGBColor button1Color, RGBColor button2Color, RGBColor button3Color) {
		super(menuController, line1, line2, LCDColor);
		
		this.button1Color = button1Color;
		this.button2Color = button2Color;
		this.button3Color = button3Color;
	}

	public RGBColor getButton1Color() {
		return button1Color;
	}

	public void setButton1Color(RGBColor button1Color) {
		this.button1Color = button1Color;
	}

	public RGBColor getButton2Color() {
		return button2Color;
	}

	public void setButton2Color(RGBColor button2Color) {
		this.button2Color = button2Color;
	}

	public RGBColor getButton3Color() {
		return button3Color;
	}

	public void setButton3Color(RGBColor button3Color) {
		this.button3Color = button3Color;
	}

	@Override
	public List<Command> display() {
		List<Command> l = super.display();
		l.add(LED.switchRGB(Button.SOFTKEY_1, button1Color));
		l.add(LED.switchRGB(Button.SOFTKEY_2, button2Color));
		l.add(LED.switchRGB(Button.SOFTKEY_3, button3Color));
		return l;
	}

}
