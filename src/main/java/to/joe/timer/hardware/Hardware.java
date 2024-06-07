package to.joe.timer.hardware;

import com.fazecast.jSerialComm.SerialPort;

import to.joe.timer.RGBColor;
import to.joe.timer.logic.ButtonMenu;

public class Hardware {
	
	public static void main(String[] args) {
		SerialPort serialPort = SerialPort.getCommPort("COM7");
		serialPort.setBaudRate(115200);
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		serialPort.openPort();
		
		SerialReader r = new SerialReader(serialPort);
		r.start();
		
		SerialWriter w = new SerialWriter(serialPort);
		w.start();
		w.reset();
		
		/*out.print(LCD.color(0, 0, 255) + END_OF_COMMAND);
		out.print(LCD.clearScreen() + END_OF_COMMAND);
		out.print(LCD.write("Hello, world!") + END_OF_COMMAND);*/
		//Menu m = new Menu("Hello, xxx", "Leftx | Rightx", new RGBColor(0, 255, 0));
		ButtonMenu b = new ButtonMenu("Hello, world!", "Up | Down | Next", new RGBColor(255, 255, 255), new RGBColor(255, 0, 0), new RGBColor(0, 255, 0), new RGBColor(0, 0, 255));
		w.add(b.display());
		//w.reset();
		//System.out.println(m.display());
	}

}
