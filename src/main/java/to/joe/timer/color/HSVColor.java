package to.joe.timer.color;

/**
 * Hue, saturation, and value are 0-255 full range.
 * <br><br>
 * Attempts to keep equal brightness no matter the hue.
 */
public record HSVColor(int hue, int saturation, int value) implements Color {
	
	public HSVColor {
		if (hue > 255 || saturation > 255 || value > 255) {
			throw new IllegalArgumentException("Value out of range");
		}
		if (hue == -1 && saturation == -1 && value == -1) {
			// Pass
		} else if (hue <= -1 || saturation <= -1 || value <= -1) {
			throw new IllegalArgumentException("Value out of range"); 
		}
	}
	
	private static float scaleToRange(float oldValue, float oldMinimum, float oldMax, float newMinimum, float newMax) {
		return (((oldValue - oldMinimum) * (newMax - newMinimum)) / (oldMax - oldMinimum)) + newMinimum;
	}
	
	@Override
	public RGBColor toRGB() {
		if (isTransparent()) {
			return new RGBColor(-1, -1, -1);
		}
		
		float r = 0;
		float g = 0;
		float b = 0;
		
		// Red
		if (0 <= hue && hue < 32) { // red to orange
			r = scaleToRange(hue, 0, 32, 255, 170);
		} else if (32 <= hue && hue < 64) { // orange to yellow
			r = 170;
		} else if (64 <= hue && hue < 96) { // yellow to green
			r = scaleToRange(hue, 64, 96, 170, 0);
		} else if (160 <= hue && hue <= 255) { // blue to red
			r = scaleToRange(hue, 160, 255, 0, 255);
		}
		
		// Green
		if (0 <= hue && hue < 96) { //red to green
			g = scaleToRange(hue, 0, 96, 0, 255);
		} else if (96 <= hue && hue < 128) { // green to aqua
			g = scaleToRange(hue, 96, 128, 255, 170);
		} else if (128 <= hue && hue < 160) { // aqua to blue
			g = scaleToRange(hue, 128, 160, 170, 0);
		}
		
		// Blue
		if (96 <= hue && hue < 128) { // green to aqua
			b = scaleToRange(hue, 96, 128, 0, 85);
		} else if (128 <= hue && hue < 160) { // aqua to blue
			b = scaleToRange(hue, 128, 160, 85, 255);
		} else if (160 <= hue && hue <= 255) { // blue to red
			b = scaleToRange(hue, 160, 255, 255, 0);
		}
		
		r = scaleToRange(saturation, 0, 255, 255, r);
		g = scaleToRange(saturation, 0, 255, 255, g);
		b = scaleToRange(saturation, 0, 255, 255, b);
		
		r = scaleToRange(value, 0, 255, 0, r);
		g = scaleToRange(value, 0, 255, 0, g);
		b = scaleToRange(value, 0, 255, 0, b);
		
		return new RGBColor(Math.round(r), Math.round(g), Math.round(b));
	}
	
	@Override
	public boolean isTransparent() {
		if (hue == -1 && saturation == -1 && value == -1) {
			return true;
		} else {
			return false;
		}
	}
	
	public static HSVColor BLACK = new HSVColor(0, 0, 0);
	public static HSVColor TRANSPARENT = new HSVColor(-1, -1, -1);
	
	public static HSVColor WHITE = new HSVColor(0, 0, 255);
	public static HSVColor RED = new HSVColor(0, 255, 255);
	public static HSVColor ORANGE = new HSVColor(32, 255, 255);
	public static HSVColor YELLOW = new HSVColor(64, 255, 255);
	public static HSVColor GREEN = new HSVColor(96, 255, 255);
	public static HSVColor AQUA = new HSVColor(128, 255, 255);
	public static HSVColor BLUE = new HSVColor(160, 255, 255);
	public static HSVColor PURPLE = new HSVColor(192, 255, 255);
	public static HSVColor PINK = new HSVColor(224, 255, 255);
	
	public static HSVColor WHITE_DIM = new HSVColor(0, 0, 128);
	public static HSVColor RED_DIM = new HSVColor(0, 255, 128);
	public static HSVColor ORANGE_DIM = new HSVColor(32, 255, 128);
	public static HSVColor YELLOW_DIM = new HSVColor(64, 255, 128);
	public static HSVColor GREEN_DIM = new HSVColor(96, 255, 128);
	public static HSVColor AQUA_DIM = new HSVColor(128, 255, 128);
	public static HSVColor BLUE_DIM = new HSVColor(160, 255, 128);
	public static HSVColor PURPLE_DIM = new HSVColor(192, 255, 128);
	public static HSVColor PINK_DIM = new HSVColor(224, 255, 128);

}
