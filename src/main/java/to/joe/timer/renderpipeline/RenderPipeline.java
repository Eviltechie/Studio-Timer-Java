package to.joe.timer.renderpipeline;

import java.util.ArrayList;
import java.util.List;

import to.joe.timer.hardware.Hardware;
import to.joe.timer.hardware.commands.LCD;
import to.joe.timer.main.TimerApplication;
import to.joe.timer.menu.Menu;

public class RenderPipeline { // TODO Opportunity to optimize by not re-sending buttons already the correct color.
	
	private TimerApplication timerApplication;
	private Hardware hardware;
	private String line1 = "";
	private String line2 = "";
	
	public RenderPipeline(TimerApplication timerApplication) {
		this.timerApplication = timerApplication;
		this.hardware = timerApplication.getHardware();
	}
	
	public void redraw() {
		Menu currentMenu = timerApplication.getMenuController().getCurrentMenu();
		
		ButtonColorState menuState = currentMenu.getButtonColorState();
		ButtonColorState timerState = timerApplication.getTimerController().getActiveTimer().getButtonColorState();
		
		List<ButtonColorState> colorStates = new ArrayList<ButtonColorState>();
		colorStates.add(timerState);
		colorStates.add(menuState);
		
		ButtonColorState finalState = ButtonColorState.over(colorStates);
		
		if (line1.equals(currentMenu.getLine1()) && line2.equals(currentMenu.getLine2())) {
			// Pass
		} else {
			line1 = currentMenu.getLine1();
			line2 = currentMenu.getLine2();
			hardware.add(LCD.clearScreen());
			hardware.add(LCD.write(line1));
			hardware.add(LCD.setPosition(1, 0));
			hardware.add(LCD.write(line2));
		}
		
		hardware.add(finalState.getCommands());
		hardware.add(LCD.color(currentMenu.getLCDColor()));
	}

}
