package to.joe.timer.hardware;

import java.util.ArrayList;
import java.util.List;

import to.joe.timer.color.Color;
import to.joe.timer.color.HSVColor;

public class ButtonColorState { //TODO should probably redo this whole thing with a map instead of an array
	
	private Color buttonColors[] = new Color[Button.values().length];
	
	public ButtonColorState() {
		for (int i = 0; i < buttonColors.length; i++) {
			buttonColors[i] = HSVColor.TRANSPARENT;
		}
	}
	
	public void switchColor(Button button, Color color) {
		buttonColors[button.getSwitchNumber() - 1] = color;
	}
	
	public List<Command> draw() {
		List<Command> commands = new ArrayList<Command>();
		for (int i = 0; i < buttonColors.length; i++) {
			if (buttonColors[i].isTransparent()) {
				commands.add(LED.switchColor(i + 1, HSVColor.BLACK));
			} else {
				commands.add(LED.switchColor(i + 1, buttonColors[i]));
			}
		}
		return commands;
	}
	
	public static ButtonColorState over(List<ButtonColorState> states) {
		ButtonColorState finalState = new ButtonColorState();
		for (ButtonColorState buttonColorState : states) {
			for (int i = 0; i < buttonColorState.buttonColors.length; i++) {
				if (!buttonColorState.buttonColors[i].isTransparent()) {
					finalState.buttonColors[i] = buttonColorState.buttonColors[i];
				}
			}
		}
		return finalState;
	}

}
