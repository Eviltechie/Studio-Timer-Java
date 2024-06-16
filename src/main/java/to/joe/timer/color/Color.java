package to.joe.timer.color;

/**
 * {@link HSVColor} can be {@link RGBColor}, but not the other way around.
 * <br><br>
 * Use {@link Color} when either {@link HSVColor} or {@link RGBColor} are okay. Otherwise use {@link HSVColor}.
 */
public interface Color {
	
	public RGBColor toRGB();
	public boolean isTransparent();

}
