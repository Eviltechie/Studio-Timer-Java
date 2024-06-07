package to.joe.timer.hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.fazecast.jSerialComm.SerialPort;

public class SerialReader extends Thread {
	
	private BufferedReader in;
	
	public SerialReader(SerialPort port) {
		in = new BufferedReader(new InputStreamReader(port.getInputStream()));
	}
	
	@Override
	public void run() {
		while (!isInterrupted()) {
			try {
				System.out.println(in.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
