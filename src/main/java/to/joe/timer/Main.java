package to.joe.timer;

import to.joe.timer.color.HSVColor;
import to.joe.timer.hardware.Hardware;
import to.joe.timer.logic.MenuController;

public class Main {

	public static Hardware hardware;
	public static MenuController menuController;
	public static Timer timer;

	public static void main(String[] args) {
		hardware = new Hardware("COM7");
		timer = new Timer("Timer A", HSVColor.ORANGE);
		menuController = timer.getMenuController();
		hardware.getRenderPipeline().draw();
		
		//pass button events from hardware to menu controller, and then to the timer if it's not consumed
	}

}
