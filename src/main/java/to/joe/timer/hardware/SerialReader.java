package to.joe.timer.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fazecast.jSerialComm.SerialPort;

import to.joe.timer.Main;
import to.joe.timer.events.ButtonEvent;
import to.joe.timer.events.ButtonEvent.Action;

public class SerialReader extends Thread {
	
	private BufferedReader in;
	
	public SerialReader(SerialPort port) {
		in = new BufferedReader(new InputStreamReader(port.getInputStream()));
	}
	
	@Override
	public void run() {
		Pattern p = Pattern.compile("^Button_(.*)_.*$");
		while (!isInterrupted()) {
			try {
				String line = in.readLine();
				if (line.equals("raw REPL; CTRL-B to exit")) {
					
				}
				Matcher m = p.matcher(line);
				if (m.matches()) {
					ButtonEvent event;
					if (line.endsWith("press")) {
						event = new ButtonEvent(Button.valueOf(m.group(1).toUpperCase()), Action.PRESSED);
					} else {
						event = new ButtonEvent(Button.valueOf(m.group(1).toUpperCase()), Action.RELEASED);
					}
					System.out.println(event);
					Main.menuController.handleEvent(event);
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
