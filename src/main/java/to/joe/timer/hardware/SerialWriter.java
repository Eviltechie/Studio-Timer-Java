package to.joe.timer.hardware;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.fazecast.jSerialComm.SerialPort;

public class SerialWriter extends Thread {
	
	private static final String END_OF_COMMAND = "`";
	
	private BlockingQueue<Command> queue = new LinkedBlockingQueue<Command>();
	private PrintWriter out;
	
	public SerialWriter(SerialPort port) {
		out = new PrintWriter(port.getOutputStream());
	}
	
	/**
	 * Add a single command to execute on the control surface.
	 * @param c The command to add.
	 */
	public void add(Command c) {
		queue.add(c);
	}
	
	/**
	 * Add multiple commands to execute on the control surface.
	 * @param commands {@link Iterable} of commands to add.
	 */
	public void add(Iterable<Command> commands) {
		for (Command c : commands) {
			queue.add(c);
		}
	}
	
	/**
	 * Stops and starts execution of main.py on the control surface.
	 */
	public void reset() {
		queue.add(new Command() {
			@Override
			public String getData() {
				return Character.toString((char)2) + Character.toString((char)3) + Character.toString((char)4); //Control + B, Control + C, Control + D
			}
		});
	}
	
	@Override
	public void run() {
		while (!isInterrupted()) {
			try {
				out.print(queue.take().getData() + END_OF_COMMAND);
				out.flush();
			} catch (InterruptedException e) {
				interrupt();
			}
		}
	}

}
