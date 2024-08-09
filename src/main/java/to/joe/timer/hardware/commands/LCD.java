package to.joe.timer.hardware.commands;

import to.joe.timer.color.Color;
import to.joe.timer.color.RGBColor;

public class LCD {
	
	public static Command clearScreen() {
		return new Command() {
			@Override
			public String getData() {
				return "lcd.clear_screen()";
			}
		};
	}
	
	public static Command setPosition(int line, int column) {
		return new Command() {
			@Override
			public String getData() {
				return String.format("lcd.set_position(%s,%s)", line, column);
			}
		};
	}
	
	public static Command write(String text) {
		return new Command() {
			@Override
			public String getData() {
				return String.format("lcd.write(\"%s\")", text);
			}
		};
	}
	
	public static Command color(Color color) {
		return new Command() {
			@Override
			public String getData() {
				RGBColor rgbColor = color.toRGB();
				return String.format("led.lcd_rgb(%s,%s,%s)", rgbColor.red(), rgbColor.green(), rgbColor.blue());
			}
		};
	}

}
