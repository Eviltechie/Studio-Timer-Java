package to.joe.timer.hardware;

import java.util.ArrayList;
import java.util.List;

import to.joe.timer.color.RGBColor;

public class test {

	public static void main(String[] args) {
		ButtonColorState state1 = new ButtonColorState();
		ButtonColorState state2 = new ButtonColorState();
		ButtonColorState state3 = new ButtonColorState();
		
		state1.switchColor(Button.SOFTKEY_1, new RGBColor(255, 0, 0));
		state1.switchColor(Button.SOFTKEY_2, new RGBColor(255, 0, 0));
		state1.switchColor(Button.SOFTKEY_3, new RGBColor(255, 0, 0));
		
		state2.switchColor(Button.SOFTKEY_2, new RGBColor(0, 255, 0));
		state2.switchColor(Button.SOFTKEY_3, new RGBColor(0, 255, 0));
		
		state3.switchColor(Button.SOFTKEY_3, new RGBColor(0, 0, 255));
		
		List<ButtonColorState> bcs = new ArrayList<ButtonColorState>();
		bcs.add(state1);
		bcs.add(state2);
		bcs.add(state3);
		
		ButtonColorState fin = ButtonColorState.over(bcs);
		
		List<Command> cmds = fin.draw();
		for (Command command : cmds) {
			System.out.println(command.getData());
		}
	}

}