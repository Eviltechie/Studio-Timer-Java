package to.joe.timer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import to.joe.timer.color.HSVColor;
import to.joe.timer.events.ConsumableEvent;
import to.joe.timer.events.Event;
import to.joe.timer.hardware.Hardware;
import to.joe.timer.hardware.LED;
import to.joe.timer.logic.MenuController;

public class Main {

	public static Hardware hardware;
	public static MenuController menuController;
	public static Timer timer;
	public static BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>();

	public static void main(String[] args) throws InterruptedException {
		hardware = new Hardware("COM7");
		timer = new Timer("Timer A", HSVColor.ORANGE);
		menuController = timer.getMenuController();
		hardware.getRenderPipeline().draw();
		hardware.getSerialWriter().add(LED.colons(128, 128, 128, 128));
		
		while (true) {
			Event event = eventQueue.take();
			menuController.handleEvent(event);
			
			if (event instanceof ConsumableEvent) {
				if (!((ConsumableEvent) event).isConsumed()) {
					Main.timer.handleEvent(event);
				}
			} else {
				Main.timer.handleEvent(event);
			}
		}
		
		//pass button events from hardware to menu controller, and then to the timer if it's not consumed
	}

}
