package to.joe.timer;

import to.joe.timer.color.HSVColor;
import to.joe.timer.hardware.Hardware;
import to.joe.timer.logic.MenuController;

public class Main {

	public static Hardware hardware;
	public static MenuController menuController;

	public static void main(String[] args) {
		hardware = new Hardware("COM7");
		Timer timerA = new Timer("Timer A", HSVColor.ORANGE);
		menuController = timerA.getMenuController();
		hardware.getSerialWriter().add(menuController.display());
		
		//pass button events from hardware to menu controller, and then to the timer if it's not consumed
	}

}
