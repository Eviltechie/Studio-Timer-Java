package to.joe.timer.tsl;

public enum TallyBit {
	
	OFF((short) Integer.toUnsignedLong(0)),
	RED((short) Integer.toUnsignedLong(1)),
	GREEN((short) Integer.toUnsignedLong(2)),
	AMBER((short) Integer.toUnsignedLong(3));
	
	private final short tallyBit;
	
	private TallyBit(short tallyBit) {
		this.tallyBit = tallyBit;
	}
	
	public short getTallyBit() {
		return tallyBit;
	}

}
