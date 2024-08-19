package to.joe.timer.tsl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class TSL {
	
	private short pbc;
	private byte ver = 0;
	private byte flags = 0;
	private short screen;
	
	private short index;
	private short control;
	private short length;
	private String text;
	
	public TSL(int display, String umd, TallyBit leftTally, TallyBit textTally, TallyBit rightTally) {
		this(0, display, umd, leftTally, textTally, rightTally, 3);
	}
	
	public TSL(int screen, int display, String umd, TallyBit leftTally, TallyBit textTally, TallyBit rightTally, int brightness) {
		this.text = umd;
		pbc = (short) Integer.toUnsignedLong(10 + text.length());
		this.screen = (short) Integer.toUnsignedLong(screen);
		
		this.index = (short) Integer.toUnsignedLong(display);
		brightness = (int) Integer.toUnsignedLong(brightness);
		this.control = (short) (rightTally.getTallyBit() << 0 | textTally.getTallyBit() << 2 | leftTally.getTallyBit() << 4 | brightness << 6);
		this.length = (short) Integer.toUnsignedLong(text.length());
	}
	
	public byte[] toBytes() {
		ByteBuffer buffer = ByteBuffer.allocate(12 + text.length());
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		buffer.putShort(pbc);
		buffer.put(ver);
		buffer.put(flags);
		buffer.putShort(screen);
		buffer.putShort(index);
		buffer.putShort(control);
		buffer.putShort(length);
		buffer.put(text.getBytes(Charset.forName("ASCII")));
		return buffer.array();
	}

}
