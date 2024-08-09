package to.joe.timer.menu;

import to.joe.timer.color.Color;
import to.joe.timer.color.HSVColor;
import to.joe.timer.events.Event;
import to.joe.timer.main.TimerApplication;
import to.joe.timer.renderpipeline.ButtonColorState;

public class Menu {
	
	private String line1 = "";
	private String line2 = "";
	private Color LCDColor = HSVColor.BLACK;
	private ButtonColorState buttonColorState = new ButtonColorState();
	private TimerApplication timerApplication;
	
	public Menu(TimerApplication timerApplication) {
		this.timerApplication = timerApplication;
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
	
	protected TimerApplication getTimerApplication() {
		return timerApplication;
	}
	
	public void handleEvent(Event event) {
		
	}
	
	public void active() {
		
	}
	
	/**
	 * Marks the menu as no longer active, so it can clear any internal state.
	 */
	public void inactive() {
		
	}

}
