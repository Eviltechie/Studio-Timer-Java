package to.joe.timer.hardware;

import com.fazecast.jSerialComm.SerialPort;

public class Hardware {
	
	private SerialPort serialPort;
	private SerialReader serialReader;
	private SerialWriter serialWriter;
	
	public Hardware(String portName) {
		serialPort = SerialPort.getCommPort(portName);
		
		serialPort.setBaudRate(115200);
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		serialPort.openPort();
		
		serialReader = new SerialReader(serialPort);
		serialReader.start();
		
		serialWriter = new SerialWriter(serialPort);
		serialWriter.start();
		serialWriter.reset();
	}

	public SerialWriter getSerialWriter() {
		return serialWriter;
	}

}
