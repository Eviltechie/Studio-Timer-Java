package to.joe.timer.hardware;

import to.joe.timer.color.Color;
import to.joe.timer.color.HSVColor;
import to.joe.timer.color.RGBColor;

public class LED {
	
	public static Command switchColor(int switchNumber, Color color) {
		return new Command() {
			@Override
			public String getData() {
				RGBColor rgbColor = color.toRGB();
				return String.format("led.sw_rgb(%s,%s,%s,%s)", switchNumber, rgbColor.red(), rgbColor.green(), rgbColor.blue());
			}
		};
	}
	
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
	
	public static Command sevenSegment(int timeSeconds) {
		return new Command() {
			@Override
			public String getData() {
				int hours = timeSeconds / 3600;
				int remainder = timeSeconds % 3600;
				int minutes = remainder / 60;
				int seconds = remainder % 60;
				String timeString = String.format("%02d%02d%02d", hours, minutes, seconds);
				return String.format("led.digits(\"%s\")", timeString); //colons(a, b, c, d)
			}
		};
	}
	
	public static Command colons(int a, int b, int c, int d) {
		return new Command() {
			@Override
			public String getData() {
				return String.format("led.colons(%s,%s,%s,%s)", a, b, c, d);
			}
		};
	}

}
