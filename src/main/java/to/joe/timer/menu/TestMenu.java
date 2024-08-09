package to.joe.timer.menu;

import java.util.Random;

import to.joe.timer.color.HSVColor;
import to.joe.timer.events.Event;
import to.joe.timer.hardware.Button;
import to.joe.timer.hardware.events.ButtonEvent;
import to.joe.timer.hardware.events.ButtonEvent.Action;
import to.joe.timer.main.TimerApplication;
import to.joe.timer.timer.Direction;

public class TestMenu extends Menu {
	
	private static Random random = new Random();

	public TestMenu(TimerApplication timerApplication, String line1) {
		super(timerApplication);
		
		setLine1(line1);
		setLine2("Prev |    | Next");
		setLCDColor(HSVColor.RED);
		getButtonColorState().setButtonColor(Button.SOFTKEY_1, new HSVColor(random.nextInt(256), 255, 255));
		getButtonColorState().setButtonColor(Button.SOFTKEY_2, new HSVColor(random.nextInt(256), 255, 255));
		getButtonColorState().setButtonColor(Button.SOFTKEY_3, new HSVColor(random.nextInt(256), 255, 255));
	}
	
	@Override
	public void handleEvent(Event event) {
		if (event instanceof ButtonEvent) {
			ButtonEvent buttonEvent = (ButtonEvent) event;
			if (buttonEvent.getAction() == Action.PRESSED) {
				if (buttonEvent.getButton() == Button.SOFTKEY_3) {
					getTimerApplication().getMenuController().nextMenu();
					buttonEvent.consume();
				}
				if (buttonEvent.getButton() == Button.SOFTKEY_1) {
					getTimerApplication().getMenuController().previousMenu();
					buttonEvent.consume();
				}
				if (buttonEvent.getButton() == Button.SOFTKEY_2) {
					if (getTimerApplication().getTimerController().getActiveTimer().getTimer().getDirection() == Direction.UP) {
						getTimerApplication().getTimerController().getActiveTimer().getTimer().setDirection(Direction.DOWN);
						System.out.println("down");
					} else {
						getTimerApplication().getTimerController().getActiveTimer().getTimer().setDirection(Direction.UP);
						System.out.println("up");
					}
					buttonEvent.consume();
				}
			}
		}
	}

}
