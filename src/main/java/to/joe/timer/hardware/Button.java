package to.joe.timer.hardware;

public enum Button {
	
	DIGIT_1(11, true, 1),
	DIGIT_2(12, true, 2),
	DIGIT_3(13, true, 3),
	DIGIT_4(6, true, 4),
	DIGIT_5(7, true, 5),
	DIGIT_6(8, true, 6),
	DIGIT_7(1, true, 7),
	DIGIT_8(2, true, 8),
	DIGIT_9(3, true, 9),
	DIGIT_0(5, true, 0),
	SOFTKEY_1(4, false, -1),
	SOFTKEY_2(9, false, -1),
	SOFTKEY_3(14, false, -1),
	START_STOP(10, false, -1),
	RESET(15, false, -1);
	
	private final int switchNumber;
	private final boolean isDigit;
	private final int digitValue;

	private Button(int switchNumber, boolean isDigit, int digitValue) {
		this.switchNumber = switchNumber;
		this.isDigit = isDigit;
		this.digitValue = digitValue;
	}
	
	public int getSwitchNumber() {
		return switchNumber;
	}
	
	public boolean isDigit() {
		return isDigit;
	}
	
	/**
	 * Gets the value of the digit pressed.
	 * @return The value of the digit pressed, or -1 if a non digit.
	 */
	public int digitValue() {
		return digitValue;
	}

}
