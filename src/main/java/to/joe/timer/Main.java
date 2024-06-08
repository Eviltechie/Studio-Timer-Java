package to.joe.timer;

import to.joe.timer.hardware.Hardware;
import to.joe.timer.logic.MenuController;

public class Main {
	
	public static MenuController menuController;
	public static Hardware hardware;

	public static void main(String[] args) {
		hardware = new Hardware("COM7");
		menuController = new MenuController();
		
		//pass button events from hardware to menu controller, and then to the timer if it's not consumed
	}

}
