package to.joe.timer.logic;

import java.util.ArrayDeque;
import java.util.Deque;

import to.joe.timer.Main;
import to.joe.timer.events.ButtonEvent;
import to.joe.timer.menu.Menu;

public class MenuController {
	
	private Menu currentMenu;
	private Deque<Menu> menuElements = new ArrayDeque<Menu>();
	
	public MenuController(Menu initialMenu) {
		currentMenu = initialMenu;
		menuElements.addLast(initialMenu);
		Main.hardware.getSerialWriter().add(currentMenu.display());
	}
	
	public void handleEvent(ButtonEvent event) {
		currentMenu.handleEvent(event);
	}
	
	public void addMenuElement(Menu menu) {
		menuElements.addLast(menu);
	}
	
	public boolean removeMenuElement(Menu menu) {
		return menuElements.remove(menu);
	}
	
	public void nextMenu() {
		menuElements.addLast(menuElements.removeFirst());
		currentMenu = menuElements.getFirst();
		
		Main.hardware.getSerialWriter().add(currentMenu.display());
	}
	
	public void previousMenu() {
		menuElements.addFirst(menuElements.removeLast());
		currentMenu = menuElements.getFirst();
		
		Main.hardware.getSerialWriter().add(currentMenu.display());
	}

}
