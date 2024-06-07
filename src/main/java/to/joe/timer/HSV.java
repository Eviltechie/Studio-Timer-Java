package to.joe.timer;

public class HSV {
	
	private int scaleToRange(int oldValue, int oldMinimum, int oldMax, int newMinimum, int newMax) {
		return (((oldValue - oldMinimum) * (newMax - newMinimum)) / (oldMax - oldMinimum)) + newMinimum;
	}
	
	public RGBColor hsvToRGB(int hue, int saturation, int value) {
		int r = 0;
		int g = 0;
		int b = 0;
		
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
		
		// TODO Round?
		
		return new RGBColor(r, g, b);
	}

}
