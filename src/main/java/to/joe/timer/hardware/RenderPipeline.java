package to.joe.timer.hardware;

import java.util.ArrayList;
import java.util.List;

import to.joe.timer.Main;
import to.joe.timer.menu.Menu;

public class RenderPipeline {
	
	private SerialWriter serialWriter;
	private String line1 = "";
	private String line2 = "";
	
	public RenderPipeline(SerialWriter serialWriter) {
		this.serialWriter = serialWriter;
	}
	
	public void draw() {
		Menu currentMenu = Main.menuController.getCurrentMenu();
		ButtonColorState menuState = currentMenu.getButtonColorState();
		ButtonColorState timerState = Main.timer.getButtonColorState();
		
		List<ButtonColorState> colorStates = new ArrayList<ButtonColorState>();
		colorStates.add(timerState);
		colorStates.add(menuState);
		
		ButtonColorState finalState = ButtonColorState.over(colorStates);
		
		if (line1.equals(currentMenu.getLine1()) && line2.equals(currentMenu.getLine2())) {
			// Pass
		} else {
			line1 = currentMenu.getLine1();
			line2 = currentMenu.getLine2();
			serialWriter.add(LCD.clearScreen());
			serialWriter.add(LCD.write(line1));
			serialWriter.add(LCD.setPosition(1, 0));
			serialWriter.add(LCD.write(line2));
		}
		
		serialWriter.add(finalState.getCommands());
		serialWriter.add(LCD.color(currentMenu.getLCDColor()));
	}

}
