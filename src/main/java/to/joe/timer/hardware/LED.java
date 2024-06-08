package to.joe.timer.hardware;

import to.joe.timer.HSVColor;
import to.joe.timer.RGBColor;

public class LED {
	
	public static Command switchRGB(Button button, RGBColor color) {
		return new Command() {
			@Override
			public String getData() {
				return String.format("led.sw_rgb(%s,%s,%s,%s)", button.getSwitchNumber(), color.red(), color.green(), color.blue());
			}
		};
	}
	
	public static Command switchHSV(Button button, HSVColor color) {
		return new Command() {
			@Override
			public String getData() {
				return String.format("led.sw_hsv(%s,%s,%s,%s)", button.getSwitchNumber(), color.hue(), color.saturation(), color.value());
			}
		};
	}

}
