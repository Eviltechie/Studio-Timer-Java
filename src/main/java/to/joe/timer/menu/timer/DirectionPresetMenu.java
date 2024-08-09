package to.joe.timer.menu.timer;

import to.joe.timer.color.HSVColor;
import to.joe.timer.events.Event;
import to.joe.timer.hardware.Button;
import to.joe.timer.hardware.events.ButtonEvent;
import to.joe.timer.hardware.events.ButtonEvent.Action;
import to.joe.timer.main.TimerApplication;
import to.joe.timer.menu.Menu;
import to.joe.timer.timer.Direction;
import to.joe.timer.timercontroller.TimerContainer;

public class DirectionPresetMenu extends Menu {
	
	private TimerContainer timerContainer;
	private boolean preset = false;
	
	public DirectionPresetMenu(TimerApplication timerApplication) {
		super(timerApplication);
		getButtonColorState().setButtonColor(Button.SOFTKEY_2, HSVColor.BLUE_DIM);
		getButtonColorState().setButtonColor(Button.SOFTKEY_3, HSVColor.WHITE_DIM);
	}
	
	@Override
	public void handleEvent(Event event) {
		if (event instanceof ButtonEvent) {
			ButtonEvent buttonEvent = (ButtonEvent) event;
			Button b = buttonEvent.getButton();
			if (buttonEvent.getAction() == Action.PRESSED) {
				if (preset && b.isDigit()) {
					if (b.digitValue() == 0) {
						timerContainer.getTimer().setTime(30);
					} else {
						timerContainer.getTimer().setTime(b.digitValue() * 60);
					}
					buttonEvent.consume();
				}
				if (b == Button.SOFTKEY_1) {
					if (timerContainer.getTimer().getDirection() == Direction.UP) {
						timerContainer.getTimer().setDirection(Direction.DOWN);
						setLine2("Down  Preset   ~");
						getButtonColorState().setButtonColor(Button.SOFTKEY_1, HSVColor.RED);
					} else {
						timerContainer.getTimer().setDirection(Direction.UP);
						setLine2("Up    Preset   ~");
						getButtonColorState().setButtonColor(Button.SOFTKEY_1, HSVColor.GREEN);
					}
					buttonEvent.consume();
					getTimerApplication().getRenderPipeline().redraw();
				}
				if (b == Button.SOFTKEY_2) {
					preset = true;
					getButtonColorState().setKeypadColor(HSVColor.BLUE_DIM);
					buttonEvent.consume();
					getTimerApplication().getRenderPipeline().redraw();
				}
				if (b == Button.SOFTKEY_3) {
					getTimerApplication().getMenuController().nextMenu();
					buttonEvent.consume();
				}
			}
			if (buttonEvent.getAction() == Action.RELEASED && b == Button.SOFTKEY_2) {
				preset = false;
				getButtonColorState().setKeypadColor(HSVColor.TRANSPARENT);
				buttonEvent.consume();
				getTimerApplication().getRenderPipeline().redraw();
			}
		}
		
	}
	
	@Override
	public void active() {
		timerContainer = getTimerApplication().getTimerController().getActiveTimer();
		setLine1(String.format("%s", timerContainer.getTimerName()));
		setLCDColor(timerContainer.getColor());
		if (timerContainer.getTimer().getDirection() == Direction.UP) {
			getButtonColorState().setButtonColor(Button.SOFTKEY_1, HSVColor.GREEN);
			setLine2("Up    Preset   ~");
		} else {
			getButtonColorState().setButtonColor(Button.SOFTKEY_1, HSVColor.RED);
			setLine2("Down  Preset   ~");
		}
	}
	
	@Override
	public void inactive() {
		preset = false;
		timerContainer = null;
		getButtonColorState().setKeypadColor(HSVColor.TRANSPARENT);
	}

}
