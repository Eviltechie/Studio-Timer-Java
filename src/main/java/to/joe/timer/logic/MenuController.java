package to.joe.timer.logic;

import java.util.ArrayDeque;
import java.util.Deque;

import to.joe.timer.Main;
import to.joe.timer.RGBColor;
import to.joe.timer.events.ButtonEvent;
import to.joe.timer.menu.Menu;
import to.joe.timer.menu.TestMenu;

public class MenuController {
	
	private Menu currentMenu;
	private Deque<Menu> menuElements = new ArrayDeque<Menu>();
	
	public MenuController() {
		currentMenu = new TestMenu(this, "Test Menu 1", new RGBColor(255, 0, 0), new RGBColor(0, 255, 0), new RGBColor(0, 0, 255));
		menuElements.addLast(currentMenu);
		menuElements.addLast(new TestMenu(this, "Test Menu 2", new RGBColor(0, 255, 0), new RGBColor(0, 0, 255), new RGBColor(255, 0, 0)));
		menuElements.addLast(new TestMenu(this, "Test Menu 3", new RGBColor(0, 0, 255), new RGBColor(255, 0, 0), new RGBColor(0, 255, 0)));
		menuElements.addLast(new TestMenu(this, "Test Menu 4", new RGBColor(0, 0, 255), new RGBColor(255, 0, 0), new RGBColor(0, 255, 0)));
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
