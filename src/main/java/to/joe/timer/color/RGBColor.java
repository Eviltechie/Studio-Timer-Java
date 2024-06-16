package to.joe.timer.color;

/**
 * Red, green, and blue are 0-255 full range.
 */
public record RGBColor(int red, int green, int blue) implements Color {

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
