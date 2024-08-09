package to.joe.timer.hardware;

import com.fazecast.jSerialComm.SerialPort;

import to.joe.timer.hardware.commands.Command;
import to.joe.timer.main.TimerApplication;

public class Hardware {
	
	private SerialPort serialPort;
	private SerialReader serialReader;
	private SerialWriter serialWriter;
	
	public Hardware(String portName, TimerApplication timerApplication) {
		serialPort = SerialPort.getCommPort(portName);
		
		serialPort.setBaudRate(115200);
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		serialPort.openPort();
		
		serialReader = new SerialReader(serialPort, timerApplication);
		serialReader.start();
		
		serialWriter = new SerialWriter(serialPort);
		serialWriter.start();
		reset();
		
	}

	/**
	 * Add a single command to execute on the control surface.
	 * @param c The command to add.
	 */
	public void add(Command c) {
		serialWriter.getCommandQueue().add(c);
	}
	
	/**
	 * Add multiple commands to execute on the control surface.
	 * @param commands {@link Iterable} of commands to add.
	 */
	public void add(Iterable<Command> commands) {
		for (Command c : commands) {
			serialWriter.getCommandQueue().add(c);
		}
	}
	
	/**
	 * Stops and starts execution of main.py on the control surface.
	 */
	public void reset() {
		serialWriter.getCommandQueue().add(new Command() {
			@Override
			public String getData() {
				return Character.toString((char)2) + Character.toString((char)3) + Character.toString((char)4); //Control + B, Control + C, Control + D
			}
		});
	}
}
