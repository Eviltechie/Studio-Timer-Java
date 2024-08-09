package to.joe.timer.renderpipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import to.joe.timer.color.Color;
import to.joe.timer.color.HSVColor;
import to.joe.timer.hardware.Button;
import to.joe.timer.hardware.commands.Command;
import to.joe.timer.hardware.commands.LED;

public class ButtonColorState {
	
	private Map<Button, Color> buttonColors = new HashMap<Button, Color>();
	
	public ButtonColorState() {
		for (Button b : Button.values()) {
			buttonColors.put(b, HSVColor.TRANSPARENT);
		}
	}
	
	public void setButtonColor(Button button, Color color) {
		buttonColors.put(button, color);
	}
	
	public void setKeypadColor(Color color) {
		setButtonColor(Button.DIGIT_0, color);
		setButtonColor(Button.DIGIT_1, color);
		setButtonColor(Button.DIGIT_2, color);
		setButtonColor(Button.DIGIT_3, color);
		setButtonColor(Button.DIGIT_4, color);
		setButtonColor(Button.DIGIT_5, color);
		setButtonColor(Button.DIGIT_6, color);
		setButtonColor(Button.DIGIT_7, color);
		setButtonColor(Button.DIGIT_8, color);
		setButtonColor(Button.DIGIT_9, color);
	}
	
	public List<Command> getCommands() {
		List<Command> commands = new ArrayList<Command>();
		for (Entry<Button, Color> entry : buttonColors.entrySet()) {
			if (entry.getValue().isTransparent()) {
				commands.add(LED.switchColor(entry.getKey(), HSVColor.BLACK));
			} else {
				commands.add(LED.switchColor(entry.getKey(), entry.getValue()));
			}
		}
		return commands;
	}
	
	public static ButtonColorState over(List<ButtonColorState> states) {
		ButtonColorState finalState = new ButtonColorState();
		for (ButtonColorState buttonColorState : states) {
			for (Entry<Button, Color> entry : buttonColorState.buttonColors.entrySet()) {
				if (!entry.getValue().isTransparent()) {
					finalState.buttonColors.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return finalState;
	}

}
