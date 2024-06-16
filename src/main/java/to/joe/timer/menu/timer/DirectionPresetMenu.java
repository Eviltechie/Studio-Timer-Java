package to.joe.timer.menu.timer;

import java.util.List;

import to.joe.timer.Direction;
import to.joe.timer.Timer;
import to.joe.timer.color.HSVColor;
import to.joe.timer.events.ButtonEvent;
import to.joe.timer.events.ButtonEvent.Action;
import to.joe.timer.hardware.Button;
import to.joe.timer.hardware.Command;
import to.joe.timer.hardware.LED;
import to.joe.timer.logic.MenuController;
import to.joe.timer.menu.ButtonMenu;

public class DirectionPresetMenu extends ButtonMenu {
	
	private Timer timer;
	private boolean preset = false;
	
	public DirectionPresetMenu(MenuController menuController, Timer timer) {
		super(menuController, String.format("Timer: %s", timer.getTimerName()), "Down  Preset   ~", timer.getTimerColor(), HSVColor.BLACK, HSVColor.BLUE_DIM, HSVColor.WHITE_DIM);
		this.timer = timer;
		if (timer.getDirection() == Direction.UP) {
			setButton1Color(HSVColor.GREEN);
			setLine2("Up    Preset   ~");
		} else {
			setButton1Color(HSVColor.RED);
			setLine2("Down  Preset   ~");
		}
	}
	
	@Override
	public void handleEvent(ButtonEvent event) {
		Button b = event.getButton();
		if (event.getAction() == Action.PRESSED) {
			if (preset && b.isDigit()) {
				if (b.digitValue() == 0) {
					timer.setTime(30);
				} else {
					timer.setTime(b.digitValue() * 60);
				}
			}
			if (b == Button.SOFTKEY_1) {
				if (timer.getDirection() == Direction.UP) {
					timer.setDirection(Direction.DOWN);
					setLine2("Down  Preset   ~");
					setButton1Color(HSVColor.RED);
				} else {
					timer.setDirection(Direction.UP);
					setLine2("Up    Preset   ~");
					setButton1Color(HSVColor.GREEN);
				}
				event.consume();
				getMenuController().draw();
			}
			if (b == Button.SOFTKEY_2) {
				preset = true;
				event.consume();
				getMenuController().draw();
			}
			if (b == Button.SOFTKEY_3) {
				getMenuController().nextMenu();
				event.consume();
			}
		}
		if (event.getAction() == Action.RELEASED && b == Button.SOFTKEY_2) {
			preset = false;
			event.consume();
			getMenuController().draw();
		}
	}
	
	@Override
	public List<Command> display() {
		List<Command> commands = super.display();
		if (preset) {
			commands.add(LED.keypadColor(HSVColor.BLUE_DIM));
		} else {
			commands.add(LED.keypadColor(HSVColor.BLACK));
		}
		return commands;
	}

}
