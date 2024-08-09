package to.joe.timer.main;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import to.joe.timer.debug.DebugInput;
import to.joe.timer.events.ConsumableEvent;
import to.joe.timer.events.Event;
import to.joe.timer.hardware.Hardware;
import to.joe.timer.hardware.commands.LED;
import to.joe.timer.menu.MenuController;
import to.joe.timer.renderpipeline.RenderPipeline;
import to.joe.timer.timercontroller.TimerController;

public class TimerApplication {
	
	private BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>();
	private Hardware hardware;
	private TimerController timerController;
	private DebugInput debugInput;
	private RenderPipeline renderPipeline;
	private MenuController menuController;
	
	public TimerApplication(String serialPort) {
		hardware = new Hardware(serialPort, this);
		hardware.add(LED.colons(50, 50, 50, 50));
		hardware.add(LED.sevenSegment(0));
		
		timerController = new TimerController(this);
		
		debugInput = new DebugInput(this);
		debugInput.start();
		
		renderPipeline = new RenderPipeline(this);
		
		menuController = new MenuController(this);
		
		renderPipeline.redraw();
		
		boolean running = true;
		while (running) {
			try {
				Event event = eventQueue.take();
				System.out.println(event.getClass().getSimpleName() + " " + event.toString());
				debugInput.handleEvent(event);
				menuController.handleEvent(event);
				if (event instanceof ConsumableEvent) {
					if (((ConsumableEvent) event).isConsumed()) {
						continue;
					}
				}
				timerController.handleEvent(event);
			} catch (InterruptedException e) {
				running = false;
			}
		}
	}
	
	public Hardware getHardware() {
		return hardware;
	}
	
	public TimerController getTimerController() {
		return timerController;
	}
	
	public RenderPipeline getRenderPipeline() {
		return renderPipeline;
	}
	
	public MenuController getMenuController() {
		return menuController;
	}
	
	public void add(Event event) {
		eventQueue.add(event);
	}

}
