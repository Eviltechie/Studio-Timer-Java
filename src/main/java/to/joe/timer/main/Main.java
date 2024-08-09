package to.joe.timer.main;

public class Main {
	
	public static void main(String[] args) {
		new TimerApplication("COM7");
		
		/*int timeSeconds = -5;
		int hours = timeSeconds / 3600;
		int remainder = timeSeconds % 3600;
		int minutes = remainder / 60;
		int seconds = remainder % 60;
		String timeString = String.format("%02d%02d%02d", hours, minutes, seconds);
		if (timeSeconds < 0) {
			timeString = timeString.replace("-", "0");
			timeString = timeString.replaceFirst("0", "-");
		}
		System.out.println(timeString);*/
	}

}
