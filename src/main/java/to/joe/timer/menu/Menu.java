package to.joe.timer.menu;

import java.util.ArrayList;
import java.util.List;

import to.joe.timer.RGBColor;
import to.joe.timer.events.ButtonEvent;
import to.joe.timer.hardware.Command;
import to.joe.timer.hardware.LCD;

public class Menu {
	
	private String line1;
	private String line2;
	private RGBColor LCDColor;
	
	public Menu(String line1, String line2, RGBColor LCDColor) {
		this.line1 = line1;
		this.line2 = line2;
		this.LCDColor = LCDColor;
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

	public RGBColor getLCDColor() {
		return LCDColor;
	}

	public void setLCDColor(RGBColor lCDColor) {
		LCDColor = lCDColor;
	}

	public List<Command> display() {
		List<Command> l = new ArrayList<Command>();
		l.add(LCD.clearScreen());
		l.add(LCD.write(line1));
		l.add(LCD.setPosition(1, 0));
		l.add(LCD.write(line2));
		l.add(LCD.color(LCDColor));
		return l;
	}
	
	public void receiveEvent(ButtonEvent event) {
		
	}

}
