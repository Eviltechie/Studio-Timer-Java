package to.joe.timer.menu.timer;

import to.joe.timer.Timer;
import to.joe.timer.color.HSVColor;
import to.joe.timer.events.ButtonEvent;
import to.joe.timer.events.ButtonEvent.Action;
import to.joe.timer.hardware.Button;
import to.joe.timer.logic.MenuController;
import to.joe.timer.menu.Menu;

public class FiveSecondsMenu extends Menu {
	
	private Timer timer;
	
	public FiveSecondsMenu(MenuController menuController, Timer timer) {
		super(menuController);
		setLine1(String.format("Timer: %s", timer.getTimerName()));
		setLine2("-5s    +5s     ~");
		setLCDColor(timer.getTimerColor());
		getButtonColorState().setButtonColor(Button.SOFTKEY_1, HSVColor.WHITE);
		getButtonColorState().setButtonColor(Button.SOFTKEY_2, HSVColor.WHITE);
		getButtonColorState().setButtonColor(Button.SOFTKEY_3, HSVColor.WHITE);
		this.timer = timer;
	}
	
	@Override
	public void handleEvent(ButtonEvent event) {
		Button b = event.getButton();
		if (event.getAction() == Action.PRESSED) {
			if (b == Button.SOFTKEY_1) {
				timer.setTime(timer.getTime() - 5);
			}
			if (b == Button.SOFTKEY_2) {
				timer.setTime(timer.getTime() + 5);
			}
			if (b == Button.SOFTKEY_3) {
				event.consume();
				getMenuController().nextMenu();
			}
		}
	}

}
