package to.joe.timer.menu;

import to.joe.timer.color.Color;
import to.joe.timer.color.HSVColor;
import to.joe.timer.events.ButtonEvent;
import to.joe.timer.hardware.ButtonColorState;
import to.joe.timer.logic.MenuController;

public class Menu {
	
	private String line1 = "";
	private String line2 = "";
	private Color LCDColor = HSVColor.BLACK;
	private ButtonColorState buttonColorState = new ButtonColorState();
	private MenuController menuController;
	
	public Menu(MenuController menuController) {
		this.menuController = menuController;
	}
	
	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public Color getLCDColor() {
		return LCDColor;
	}

	public void setLCDColor(Color lCDColor) {
		LCDColor = lCDColor;
	}
	
	public ButtonColorState getButtonColorState() {
		return buttonColorState;
	}
	
	public MenuController getMenuController() {
		return menuController;
	}
	
	public void handleEvent(ButtonEvent event) {
		
	}
	
	public void inactive() {
		
	}

}
