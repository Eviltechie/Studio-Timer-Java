package to.joe.timer.hardware;

import to.joe.timer.color.Color;
import to.joe.timer.color.HSVColor;
import to.joe.timer.color.RGBColor;

public class LED {
	
	public static Command switchColor(Button button, Color color) {
		return new Command() {
			@Override
			public String getData() {
				RGBColor rgbColor = color.toRGB();
				return String.format("led.sw_rgb(%s,%s,%s,%s)", button.getSwitchNumber(), rgbColor.red(), rgbColor.green(), rgbColor.blue());
			}
		};
	}
	
	public static Command keypadColor(HSVColor color) {
		return new Command() {
			@Override
			public String getData() {
				return String.format("led.keypad_color(%s,%s,%s)", color.hue(), color.saturation(), color.value());
			}
		};
	}

}
