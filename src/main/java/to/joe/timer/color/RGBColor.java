package to.joe.timer.color;

/**
 * Red, green, and blue are 0-255 full range.
 */
public record RGBColor(int red, int green, int blue) implements Color {
	
	public RGBColor {
		if (red > 255 || green > 255 || blue > 255) {
			throw new IllegalArgumentException("Value out of range");
		}
		if (red == -1 && green == -1 && blue == -1) {
			// Pass
		} else if (red <= -1 || green <= -1 || blue <= -1) {
			throw new IllegalArgumentException("Value out of range"); 
		}
	}

	@Override
	public RGBColor toRGB() {
		return this;
	}
	
	@Override
	public boolean isTransparent() {
		if (red == -1 && green == -1 && blue == -1) {
			return true;
		} else {
			return false;
		}
	}

}
