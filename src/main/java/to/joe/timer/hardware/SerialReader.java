package to.joe.timer.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fazecast.jSerialComm.SerialPort;

import to.joe.timer.events.Event;
import to.joe.timer.hardware.events.ButtonEvent;
import to.joe.timer.hardware.events.ButtonEvent.Action;
import to.joe.timer.main.TimerApplication;

public class SerialReader extends Thread {
	
	private BufferedReader in;
	private TimerApplication timerApplication;
	
	public SerialReader(SerialPort port, TimerApplication timerApplication) {
		this.timerApplication = timerApplication;
		in = new BufferedReader(new InputStreamReader(port.getInputStream()));
	}
	
	@Override
	public void run() {
		Pattern p = Pattern.compile("^Button_(.*)_.*$");
		while (!isInterrupted()) {
			try {
				String line = in.readLine();
				Matcher m = p.matcher(line);
				if (m.matches()) {
					Event event;
					if (line.endsWith("press")) {
						event = new ButtonEvent(Button.valueOf(m.group(1).toUpperCase()), Action.PRESSED);
					} else {
						event = new ButtonEvent(Button.valueOf(m.group(1).toUpperCase()), Action.RELEASED);
					}
					timerApplication.add(event);
				} else {
					System.out.println(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
