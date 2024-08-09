package to.joe.timer.menu;

import java.util.ArrayDeque;
import java.util.Deque;

import to.joe.timer.events.Event;
import to.joe.timer.main.TimerApplication;
import to.joe.timer.menu.timer.DirectionPresetMenu;
import to.joe.timer.menu.timer.FiveSecondsMenu;
import to.joe.timer.menu.timer.OneMinuteMenu;
import to.joe.timer.menu.timer.SpeedMenu;
import to.joe.timer.menu.timer.TimerSelectMenu;

public class MenuController {
	
	private TimerApplication timerApplication;
	private Menu currentMenu = null;
	private Deque<Menu> menuElements = new ArrayDeque<Menu>();
	
	public MenuController(TimerApplication timerApplication) {
		this.timerApplication = timerApplication;
		
		addMenuElement(new DirectionPresetMenu(timerApplication));
		addMenuElement(new FiveSecondsMenu(timerApplication));
		addMenuElement(new OneMinuteMenu(timerApplication));
		addMenuElement(new SpeedMenu(timerApplication));
		addMenuElement(new TimerSelectMenu(timerApplication));
	}
	
	public void handleEvent(Event event) {
		currentMenu.handleEvent(event);
	}
	
	public Menu getCurrentMenu() {
		return currentMenu;
	}
	
	public void addMenuElement(Menu menu) {
		if (currentMenu == null) {
			currentMenu = menu;
			currentMenu.active();
		}
		menuElements.addLast(menu);
	}
	
	public boolean removeMenuElement(Menu menu) {
		return menuElements.remove(menu);
	}
	
	public void nextMenu() {
		currentMenu.inactive();
		menuElements.addLast(menuElements.removeFirst());
		currentMenu = menuElements.getFirst();
		currentMenu.active();
		
		timerApplication.getRenderPipeline().redraw();
	}
	
	public void previousMenu() {
		currentMenu.inactive();
		menuElements.addFirst(menuElements.removeLast());
		currentMenu = menuElements.getFirst();
		currentMenu.active();
		
		timerApplication.getRenderPipeline().redraw();
	}

}
