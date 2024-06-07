package to.joe.timer.hardware;

public enum Button {
	
	DIGIT_1(11),
	DIGIT_2(12),
	DIGIT_3(13),
	DIGIT_4(6),
	DIGIT_5(7),
	DIGIT_6(8),
	DIGIT_7(1),
	DIGIT_8(2),
	DIGIT_9(3),
	DIGIT_0(5),
	SOFTKEY_1(4),
	SOFTKEY_2(9),
	SOFTKEY_3(14),
	START_STOP(10),
	RESET(15);
	
	private final int switchNumber;

	private Button(int switchNumber) {
		this.switchNumber = switchNumber;
	}
	
	public int getValue() {
		return switchNumber;
	}

}
