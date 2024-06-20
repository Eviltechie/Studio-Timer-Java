package to.joe.timer.logic;

import java.util.ArrayDeque;
import java.util.Deque;

import to.joe.timer.Main;
import to.joe.timer.events.ButtonEvent;
import to.joe.timer.menu.Menu;

public class MenuController {
	
	private Menu currentMenu = null;
	private Deque<Menu> menuElements = new ArrayDeque<Menu>();
	
	public void handleEvent(ButtonEvent event) {
		currentMenu.handleEvent(event);
	}
	
	public Menu getCurrentMenu() {
		return currentMenu;
	}
	
	public void addMenuElement(Menu menu) {
		if (currentMenu == null) {
			currentMenu = menu;
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
		
		Main.hardware.getRenderPipeline().draw();
	}
	
	public void previousMenu() {
		currentMenu.inactive();
		menuElements.addFirst(menuElements.removeLast());
		currentMenu = menuElements.getFirst();
		
		Main.hardware.getRenderPipeline().draw();
	}

}
