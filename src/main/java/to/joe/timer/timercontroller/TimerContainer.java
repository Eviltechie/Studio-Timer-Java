package to.joe.timer.timercontroller;

import to.joe.timer.color.HSVColor;
import to.joe.timer.hardware.Button;
import to.joe.timer.main.TimerApplication;
import to.joe.timer.renderpipeline.ButtonColorState;
import to.joe.timer.timer.Timer;

public class TimerContainer {
	
	private Timer timer;
	private ButtonColorState buttonColorState = new ButtonColorState();
	private String timerName;
	private HSVColor color;
	
	public TimerContainer(TimerApplication timerApplication, String timerName, HSVColor color) {
		this.timer = new Timer(timerApplication);
		this.timerName = timerName;
		this.color = color;
		buttonColorState.setButtonColor(Button.START_STOP, HSVColor.RED);
		buttonColorState.setKeypadColor(this.color);
		buttonColorState.setButtonColor(Button.RESET, this.color);
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public ButtonColorState getButtonColorState() {
		return buttonColorState;
	}
	
	public String getTimerName() {
		return timerName;
	}
	
	public HSVColor getColor() {
		return color;
	}

}
