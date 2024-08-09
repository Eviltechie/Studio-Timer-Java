package to.joe.timer.hardware;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.fazecast.jSerialComm.SerialPort;

import to.joe.timer.hardware.commands.Command;

public class SerialWriter extends Thread {
	
	private static final String END_OF_COMMAND = "`";
	
	private BlockingQueue<Command> queue = new LinkedBlockingQueue<Command>();
	private PrintWriter out;
	
	public SerialWriter(SerialPort port) {
		out = new PrintWriter(port.getOutputStream());
	}
	
	public BlockingQueue<Command> getCommandQueue() {
		return queue;
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
