package to.joe.timer.menu.timer;

import to.joe.timer.Direction;
import to.joe.timer.Main;
import to.joe.timer.Timer;
import to.joe.timer.color.HSVColor;
import to.joe.timer.events.ButtonEvent;
import to.joe.timer.events.ButtonEvent.Action;
import to.joe.timer.events.Event;
import to.joe.timer.hardware.Button;
import to.joe.timer.logic.MenuController;
import to.joe.timer.menu.Menu;

public class DirectionPresetMenu extends Menu {
	
	private Timer timer;
	private boolean preset = false;
	
	public DirectionPresetMenu(MenuController menuController, Timer timer) {
		super(menuController);
		setLine1(String.format("Timer: %s", timer.getTimerName()));
		setLCDColor(timer.getTimerColor());
		getButtonColorState().setButtonColor(Button.SOFTKEY_2, HSVColor.BLUE_DIM);
		getButtonColorState().setButtonColor(Button.SOFTKEY_3, HSVColor.WHITE_DIM);
		this.timer = timer;
		if (timer.getDirection() == Direction.UP) {
			getButtonColorState().setButtonColor(Button.SOFTKEY_1, HSVColor.GREEN);
			setLine2("Up    Preset   ~");
		} else {
			getButtonColorState().setButtonColor(Button.SOFTKEY_1, HSVColor.RED);
			setLine2("Down  Preset   ~");
		}
	}
	
	@Override
	public void handleEvent(Event event) {
		if (event instanceof ButtonEvent) {
			ButtonEvent buttonEvent = (ButtonEvent) event;
			Button b = buttonEvent.getButton();
			if (buttonEvent.getAction() == Action.PRESSED) {
				if (preset && b.isDigit()) {
					if (b.digitValue() == 0) {
						timer.setTime(30);
					} else {
						timer.setTime(b.digitValue() * 60);
					}
					buttonEvent.consume();
				}
				if (b == Button.SOFTKEY_1) {
					if (timer.getDirection() == Direction.UP) {
						timer.setDirection(Direction.DOWN);
						setLine2("Down  Preset   ~");
						getButtonColorState().setButtonColor(Button.SOFTKEY_1, HSVColor.RED);
					} else {
						timer.setDirection(Direction.UP);
						setLine2("Up    Preset   ~");
						getButtonColorState().setButtonColor(Button.SOFTKEY_1, HSVColor.GREEN);
					}
					buttonEvent.consume();
					Main.hardware.getRenderPipeline().draw();
				}
				if (b == Button.SOFTKEY_2) {
					preset = true;
					getButtonColorState().setKeypadColor(HSVColor.BLUE_DIM);
					buttonEvent.consume();
					Main.hardware.getRenderPipeline().draw();
				}
				if (b == Button.SOFTKEY_3) {
					getMenuController().nextMenu();
					buttonEvent.consume();
				}
			}
			if (buttonEvent.getAction() == Action.RELEASED && b == Button.SOFTKEY_2) {
				preset = false;
				getButtonColorState().setKeypadColor(HSVColor.TRANSPARENT);
				buttonEvent.consume();
				Main.hardware.getRenderPipeline().draw();
			}
		}
		
	}
	
	@Override
	public void inactive() {
		preset = false;
		getButtonColorState().setKeypadColor(HSVColor.TRANSPARENT);
	}

}
