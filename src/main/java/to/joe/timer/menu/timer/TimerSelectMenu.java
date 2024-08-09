package to.joe.timer.menu.timer;

import to.joe.timer.color.HSVColor;
import to.joe.timer.events.Event;
import to.joe.timer.hardware.Button;
import to.joe.timer.hardware.events.ButtonEvent;
import to.joe.timer.hardware.events.ButtonEvent.Action;
import to.joe.timer.main.TimerApplication;
import to.joe.timer.menu.Menu;
import to.joe.timer.timercontroller.TimerContainer;

public class TimerSelectMenu extends Menu {
	
	private TimerContainer timerContainer;
	private boolean select = false;
	
	public TimerSelectMenu(TimerApplication timerApplication) {
		super(timerApplication);
		setLine2("Select         ~");
		getButtonColorState().setButtonColor(Button.SOFTKEY_1, HSVColor.WHITE_DIM);
		getButtonColorState().setButtonColor(Button.SOFTKEY_3, HSVColor.WHITE_DIM);
	}
	
	@Override
	public void handleEvent(Event event) {
		if (event instanceof ButtonEvent) {
			ButtonEvent buttonEvent = (ButtonEvent) event;
			Button b = buttonEvent.getButton();
			if (buttonEvent.getAction() == Action.PRESSED) {
				if (select && b.isDigit()) {
					switch (b.digitValue()) { // TODO This is gross
					case 1:
						getTimerApplication().getTimerController().setActiveTimer("TimerA");
						break;
					case 2:
						getTimerApplication().getTimerController().setActiveTimer("TimerB");
						break;
					case 3:
						getTimerApplication().getTimerController().setActiveTimer("TimerC");
						break;
					case 4:
						getTimerApplication().getTimerController().setActiveTimer("TimerD");
						break;
					case 5:
						getTimerApplication().getTimerController().setActiveTimer("TimerE");
						break;
					case 6:
						getTimerApplication().getTimerController().setActiveTimer("TimerF");
						break;
					}
					buttonEvent.consume();
					active();
					getTimerApplication().getRenderPipeline().redraw();
				}
				
				if (b == Button.SOFTKEY_1) {
					select = true;
					getButtonColorState().setKeypadColor(HSVColor.BLACK);
					getButtonColorState().setButtonColor(Button.DIGIT_1, getTimerApplication().getTimerController().getTimerContainer("TimerA").getColor()); // TODO This is gross
					getButtonColorState().setButtonColor(Button.DIGIT_2, getTimerApplication().getTimerController().getTimerContainer("TimerB").getColor());
					getButtonColorState().setButtonColor(Button.DIGIT_3, getTimerApplication().getTimerController().getTimerContainer("TimerC").getColor());
					getButtonColorState().setButtonColor(Button.DIGIT_4, getTimerApplication().getTimerController().getTimerContainer("TimerD").getColor());
					getButtonColorState().setButtonColor(Button.DIGIT_5, getTimerApplication().getTimerController().getTimerContainer("TimerE").getColor());
					getButtonColorState().setButtonColor(Button.DIGIT_6, getTimerApplication().getTimerController().getTimerContainer("TimerF").getColor());
					buttonEvent.consume();
					getTimerApplication().getRenderPipeline().redraw();
				}
				
				if (b == Button.SOFTKEY_3) {
					buttonEvent.consume();
					getTimerApplication().getMenuController().nextMenu();
				}
			}
			if (buttonEvent.getAction() == Action.RELEASED && b == Button.SOFTKEY_1) {
				select = false;
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
	}
	
	@Override
	public void inactive() {
		select = false;
		timerContainer = null;
	}

}
