package to.joe.timer.menu;

import java.util.List;

import to.joe.timer.color.Color;
import to.joe.timer.hardware.Button;
import to.joe.timer.hardware.Command;
import to.joe.timer.hardware.LED;
import to.joe.timer.logic.MenuController;

public class ButtonMenu extends Menu {
	
	private Color button1Color;
	private Color button2Color;
	private Color button3Color;

	public ButtonMenu(MenuController menuController, String line1, String line2, Color LCDColor, Color button1Color, Color button2Color, Color button3Color) {
		super(menuController, line1, line2, LCDColor);
		
		this.button1Color = button1Color;
		this.button2Color = button2Color;
		this.button3Color = button3Color;
	}

	public Color getButton1Color() {
		return button1Color;
	}

	public void setButton1Color(Color button1Color) {
		this.button1Color = button1Color;
	}

	public Color getButton2Color() {
		return button2Color;
	}

	public void setButton2Color(Color button2Color) {
		this.button2Color = button2Color;
	}

	public Color getButton3Color() {
		return button3Color;
	}

	public void setButton3Color(Color button3Color) {
		this.button3Color = button3Color;
	}

	@Override
	public List<Command> display() {
		List<Command> l = super.display();
		l.add(LED.switchColor(Button.SOFTKEY_1, button1Color));
		l.add(LED.switchColor(Button.SOFTKEY_2, button2Color));
		l.add(LED.switchColor(Button.SOFTKEY_3, button3Color));
		return l;
	}

}
